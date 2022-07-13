package com.adyen.android.assignment.di

import com.adyen.android.assignment.data.sources.AstronomyPictureRemoteDataSource
import com.adyen.android.assignment.domain.sources.AstronomyPictureDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAstronomyPictureDataSource(remoteDataSource: AstronomyPictureRemoteDataSource): AstronomyPictureDataSource
}