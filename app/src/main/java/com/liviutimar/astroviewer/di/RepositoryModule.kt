package com.liviutimar.astroviewer.di

import com.liviutimar.astroviewer.data.repositories.AstronomyPictureRepositoryImpl
import com.liviutimar.astroviewer.domain.repositories.AstronomyPictureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAstronomyPictureRepository(repositoryImpl: AstronomyPictureRepositoryImpl): AstronomyPictureRepository
}