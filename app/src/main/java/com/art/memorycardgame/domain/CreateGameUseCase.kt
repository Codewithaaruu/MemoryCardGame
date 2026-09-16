package com.art.memorycardgame.domain

import javax.inject.Inject

class CreateGameUseCase @Inject constructor(
    private val repository: MemoryGameRepository
) {
    operator fun invoke(): List<MemoryCard> {
        return repository.createCards()
    }
}
