package com.developersancho.rortycompose.data.repository.di

import android.annotation.SuppressLint
import android.content.Context
import com.developersancho.rortycompose.data.local.dao.CharacterFavoriteDao
import com.developersancho.rortycompose.data.local.dao.EpisodeFavoriteDao
import com.developersancho.rortycompose.data.local.dao.LocationFavoriteDao
import com.developersancho.rortycompose.data.remote.service.CharacterService
import com.developersancho.rortycompose.data.remote.service.EpisodeService
import com.developersancho.rortycompose.data.remote.service.LocationService
import com.developersancho.rortycompose.data.repository.character.CharacterRepository
import com.developersancho.rortycompose.data.repository.episode.EpisodeRepository
import com.developersancho.rortycompose.data.repository.langauge.LanguageRepository
import com.developersancho.rortycompose.data.repository.location.LocationRepository
import com.developersancho.rortycompose.data.repository.welcome.WelcomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCharacterRepository(
        service: CharacterService,
        dao: CharacterFavoriteDao
    ) = CharacterRepository(service, dao)

    @Singleton
    @Provides
    fun provideEpisodeRepository(
        service: EpisodeService,
        dao: EpisodeFavoriteDao
    ) = EpisodeRepository(service, dao)

    @Singleton
    @Provides
    fun provideLocationRepository(
        service: LocationService,
        dao: LocationFavoriteDao
    ) = LocationRepository(service, dao)

    @Singleton
    @Provides
    fun provideWelcomeRepository(
        @ApplicationContext context: Context
    ) = WelcomeRepository(context)

    @Singleton
    @Provides
    fun provideLanguageRepository(
        @ApplicationContext context: Context
    ) = LanguageRepository(context)
}