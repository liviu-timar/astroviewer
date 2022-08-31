package com.liviutimar.astroviewer.di

import com.liviutimar.astroviewer.data.api.PlanetaryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePlanetaryService() = PlanetaryService.instance
}