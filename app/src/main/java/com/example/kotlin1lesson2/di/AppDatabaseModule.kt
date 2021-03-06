package com.example.kotlin1lesson2.di

import android.content.Context
import com.example.kotlin1lesson2.data.local.db.AppDatabase
import com.example.kotlin1lesson2.data.local.db.RoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) =
        RoomClient().provideCreateAppDatabase(context)


    @Singleton
    @Provides
    fun provideCharacterDao(characterAppDatabase: AppDatabase) =
        RoomClient().provideCharacterDao(characterAppDatabase)


    @Singleton
    @Provides
    fun provideLocationDao(locationAppDatabase: AppDatabase) =
        RoomClient().provideLocationDao(locationAppDatabase)

    @Singleton
    @Provides
    fun provideEpisodesDao(episodesAppDatabase: AppDatabase) =
        RoomClient().provideEpisodesDao(episodesAppDatabase)

}