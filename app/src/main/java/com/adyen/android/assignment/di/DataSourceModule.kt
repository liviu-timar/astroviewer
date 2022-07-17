package com.adyen.android.assignment.di

import com.adyen.android.assignment.data.sources.AstronomyPictureLocalDataSourceImpl
import com.adyen.android.assignment.data.sources.AstronomyPictureRemoteDataSourceImpl
import com.adyen.android.assignment.domain.sources.AstronomyPictureLocalDataSource
import com.adyen.android.assignment.domain.sources.AstronomyPictureRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAstronomyPictureRemoteDataSource(
        remoteDataSource: AstronomyPictureRemoteDataSourceImpl
    ): AstronomyPictureRemoteDataSource

    @Binds
    abstract fun bindAstronomyPictureLocalDataSource(
        remoteDataSource: AstronomyPictureLocalDataSourceImpl
    ): AstronomyPictureLocalDataSource
}