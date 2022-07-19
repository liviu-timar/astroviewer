package com.adyen.android.assignment.di

import com.adyen.android.assignment.data.remote.PlanetaryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun providePlanetaryService() = PlanetaryService.instance
}