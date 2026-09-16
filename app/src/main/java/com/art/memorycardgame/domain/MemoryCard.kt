package com.art.memorycardgame.domain


data class MemoryCard(
    val id: Int,
    val value: String,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)
