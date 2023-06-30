package com.liviutimar.astroviewer.di

import com.liviutimar.astroviewer.data.sources.PictureDbDataSource
import com.liviutimar.astroviewer.data.sources.PictureApiDataSource
import com.liviutimar.astroviewer.domain.sources.PictureLocalDataSource
import com.liviutimar.astroviewer.domain.sources.PictureRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindPictureRemoteDataSource(
        remoteDataSource: PictureApiDataSource
    ): PictureRemoteDataSource

    @Binds
    abstract fun bindPictureLocalDataSource(
        localDataSource: PictureDbDataSource
    ): PictureLocalDataSource
}