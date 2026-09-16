package com.art.memorycardgame.presentatuin

import com.art.memorycardgame.domain.MemoryCard

object GameContract {

    data class State(
        val cards: List<MemoryCard> = emptyList(),
        val moves: Int = 0,
        val matchedPairs: Int = 0,
        val isGameCompleted: Boolean = false
    )

    sealed interface Intent {

        data class OnCardClicked(
            val cardId: Int
        ) : Intent

        data object RestartGame : Intent
    }

    sealed interface Effect {

        data object GameCompleted : Effect
    }
}