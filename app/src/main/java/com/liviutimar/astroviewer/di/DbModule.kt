package com.liviutimar.astroviewer.di

import android.content.Context
import androidx.room.Room
import com.liviutimar.astroviewer.data.db.PictureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun providePictureDatabase(@ApplicationContext context: Context): PictureDatabase =
        Room
            .databaseBuilder(context, PictureDatabase::class.java, "AstroviewerDatabase")
            .fallbackToDestructiveMigration() // Used for simplicity. In a real app migrations should be provided.
            .build()

    @Provides
    fun providePictureDao(database: PictureDatabase) = database.pictureDao()
}