package com.developersancho.rortycompose.domain.di

import android.annotation.SuppressLint
import com.developersancho.rortycompose.data.repository.character.CharacterRepository
import com.developersancho.rortycompose.data.repository.location.LocationRepository
import com.developersancho.rortycompose.domain.usecase.location.GetLocationDetail
import com.developersancho.rortycompose.domain.usecase.location.GetLocations
import com.developersancho.rortycompose.domain.usecase.location.favorite.AddLocationFavorite
import com.developersancho.rortycompose.domain.usecase.location.favorite.DeleteLocationFavorite
import com.developersancho.rortycompose.domain.usecase.location.favorite.GetLocationFavorites
import com.developersancho.rortycompose.domain.usecase.location.favorite.UpdateLocationFavorite
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class LocationDomainModule {

    @Singleton
    @Provides
    fun provideGetLocations(repository: LocationRepository): GetLocations {
        return GetLocations(repository)
    }

    @Singleton
    @Provides
    fun provideGetLocationDetail(
        locRepo: LocationRepository,
        charRepo: CharacterRepository
    ): GetLocationDetail {
        return GetLocationDetail(locRepo, charRepo)
    }

    @Singleton
    @Provides
    fun provideAddLocationFavorite(repository: LocationRepository): AddLocationFavorite {
        return AddLocationFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteLocationFavorite(repository: LocationRepository): DeleteLocationFavorite {
        return DeleteLocationFavorite(repository)
    }

    @Singleton
    @Provides
    fun provideGetLocationFavorites(repository: LocationRepository): GetLocationFavorites {
        return GetLocationFavorites(repository)
    }

    @Singleton
    @Provides
    fun provideUpdateLocationFavorite(repository: LocationRepository): UpdateLocationFavorite {
        return UpdateLocationFavorite(repository)
    }
}