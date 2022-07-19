package com.adyen.android.assignment.di

import android.content.Context
import androidx.room.Room
import com.adyen.android.assignment.data.db.AstronomyDatabase
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
    fun provideAstronomyDatabase(@ApplicationContext context: Context): AstronomyDatabase =
        Room
            .databaseBuilder(context, AstronomyDatabase::class.java, "AstronomyDatabase")
            .fallbackToDestructiveMigration() // Used for simplicity. In a real app migrations should be provided.
            .build()

    @Provides
    fun provideAstronomyPictureDao(database: AstronomyDatabase) = database.astronomyPictureDao()
}