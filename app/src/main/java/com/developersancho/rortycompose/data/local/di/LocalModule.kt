package com.developersancho.rortycompose.data.local.di

import android.content.Context
import androidx.room.Room
import com.developersancho.rortycompose.BuildConfig
import com.developersancho.rortycompose.data.local.dao.CharacterFavoriteDao
import com.developersancho.rortycompose.data.local.dao.EpisodeFavoriteDao
import com.developersancho.rortycompose.data.local.dao.LocationFavoriteDao
import com.developersancho.rortycompose.data.local.db.RortyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

private const val DB_NAME = "db_name"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    @Named(value = DB_NAME)
    fun provideDatabaseName(): String {
        return BuildConfig.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @Named(value = DB_NAME) dbname: String,
        @ApplicationContext context: Context
    ): RortyDatabase {
        return Room.databaseBuilder(context, RortyDatabase::class.java, dbname)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterFavoriteDao(appDatabase: RortyDatabase): CharacterFavoriteDao {
        return appDatabase.characterFavoriteDao()
    }

    @Provides
    @Singleton
    fun provideEpisodeFavoriteDao(appDatabase: RortyDatabase): EpisodeFavoriteDao {
        return appDatabase.episodeFavoriteDao()
    }

    @Provides
    @Singleton
    fun provideLocationFavoriteDao(appDatabase: RortyDatabase): LocationFavoriteDao {
        return appDatabase.locationFavoriteDao()
    }
}