package com.art.memorycardgame.domain

interface MemoryGameRepository {

    fun createCards(): List<MemoryCard>
}