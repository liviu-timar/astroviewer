package com.liviutimar.astroviewer.di

import com.liviutimar.astroviewer.data.sources.AstronomyPictureDbDataSource
import com.liviutimar.astroviewer.data.sources.AstronomyPictureApiDataSource
import com.liviutimar.astroviewer.domain.sources.AstronomyPictureLocalDataSource
import com.liviutimar.astroviewer.domain.sources.AstronomyPictureRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAstronomyPictureRemoteDataSource(
        remoteDataSource: AstronomyPictureApiDataSource
    ): AstronomyPictureRemoteDataSource

    @Binds
    abstract fun bindAstronomyPictureLocalDataSource(
        localDataSource: AstronomyPictureDbDataSource
    ): AstronomyPictureLocalDataSource
}