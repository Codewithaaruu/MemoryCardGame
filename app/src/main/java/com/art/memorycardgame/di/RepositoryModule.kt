package com.art.memorycardgame.di

import com.art.memorycardgame.data.repository.MemoryGameRepositoryImpl
import com.art.memorycardgame.domain.MemoryGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMemoryGameRepository(
        impl: MemoryGameRepositoryImpl
    ): MemoryGameRepository
}
