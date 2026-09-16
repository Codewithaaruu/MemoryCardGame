package com.art.memorycardgame.data.repository

import com.art.memorycardgame.domain.MemoryCard
import com.art.memorycardgame.domain.MemoryGameRepository
import javax.inject.Inject

class MemoryGameRepositoryImpl @Inject constructor() : MemoryGameRepository {
    override fun createCards(): List<MemoryCard> {
        val values = listOf(
            "🍎",
            "🍌",
            "🍇",
            "🍊",
            "🍉",
            "🥝"
        )
        return values
            .flatMap { value -> listOf(value, value) }
            .shuffled()
            .mapIndexed { index, value ->
                MemoryCard(
                    id = index,
                    value = value
                )
            }
    }
}