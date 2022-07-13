package com.adyen.android.assignment.di

import com.adyen.android.assignment.data.repositories.AstronomyPictureRepositoryImpl
import com.adyen.android.assignment.domain.repositories.AstronomyPictureRepository
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