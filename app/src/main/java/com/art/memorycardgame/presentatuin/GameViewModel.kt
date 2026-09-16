package com.art.memorycardgame.presentatuin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.art.memorycardgame.domain.CreateGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val createGameUseCase: CreateGameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        GameContract.State()
    )

    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<GameContract.Effect>()

    val effect = _effect.asSharedFlow()

    private var firstSelectedCard: Int? = null
    private var isCheckingPair = false

    init {
        startNewGame()
    }

    fun onIntent(intent: GameContract.Intent) {

        when (intent) {

            is GameContract.Intent.OnCardClicked -> {
                onCardClicked(intent.cardId)
            }

            GameContract.Intent.RestartGame -> {
                startNewGame()
            }
        }
    }

    private fun startNewGame() {

        firstSelectedCard = null
        isCheckingPair = false

        _state.value = GameContract.State(
            cards = createGameUseCase()
        )
    }

    private fun onCardClicked(cardId: Int) {

        if (isCheckingPair) return

        val currentCards = _state.value.cards

        val selectedCard = currentCards
            .find { it.id == cardId }
            ?: return

        // Don't allow clicking an already flipped/matched card
        if (selectedCard.isFlipped || selectedCard.isMatched) {
            return
        }

        // Flip the selected card
        val updatedCards = currentCards.map { card ->
            if (card.id == cardId) {
                card.copy(isFlipped = true)
            } else {
                card
            }
        }

        _state.update {
            it.copy(cards = updatedCards)
        }

        val firstCardId = firstSelectedCard

        if (firstCardId == null) {

            firstSelectedCard = cardId

        } else {

            firstSelectedCard = null

            checkPair(
                firstCardId = firstCardId,
                secondCardId = cardId
            )
        }
    }

    private fun checkPair(
        firstCardId: Int,
        secondCardId: Int
    ) {

        isCheckingPair = true

        viewModelScope.launch {

            val cards = _state.value.cards

            val firstCard = cards.first {
                it.id == firstCardId
            }

            val secondCard = cards.first {
                it.id == secondCardId
            }

            val isMatch = firstCard.value == secondCard.value

            _state.update {
                it.copy(
                    moves = it.moves + 1
                )
            }

            delay(700)

            if (isMatch) {

                handleMatch(
                    firstCardId,
                    secondCardId
                )

            } else {

                hideCards(
                    firstCardId,
                    secondCardId
                )
            }

            isCheckingPair = false
        }
    }

    private fun handleMatch(
        firstCardId: Int,
        secondCardId: Int
    ) {

        val updatedCards = _state.value.cards.map { card ->

            if (
                card.id == firstCardId ||
                card.id == secondCardId
            ) {
                card.copy(
                    isMatched = true,
                    isFlipped = true
                )
            } else {
                card
            }
        }

        val matchedPairs =
            updatedCards.count { it.isMatched } / 2

        val isCompleted =
            updatedCards.all { it.isMatched }

        _state.update {
            it.copy(
                cards = updatedCards,
                matchedPairs = matchedPairs,
                isGameCompleted = isCompleted
            )
        }

        if (isCompleted) {

            viewModelScope.launch {
                _effect.emit(
                    GameContract.Effect.GameCompleted
                )
            }
        }
    }

    private fun hideCards(
        firstCardId: Int,
        secondCardId: Int
    ) {

        val updatedCards = _state.value.cards.map { card ->

            if (
                card.id == firstCardId ||
                card.id == secondCardId
            ) {
                card.copy(
                    isFlipped = false
                )
            } else {
                card
            }
        }

        _state.update {
            it.copy(cards = updatedCards)
        }
    }
}
