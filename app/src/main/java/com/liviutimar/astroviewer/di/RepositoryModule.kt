package com.liviutimar.astroviewer.di

import com.liviutimar.astroviewer.data.repositories.PictureRepositoryImpl
import com.liviutimar.astroviewer.domain.repositories.PictureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPictureRepository(repositoryImpl: PictureRepositoryImpl): PictureRepository
}