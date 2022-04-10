package com.developersancho.rortycompose.domain.di

import android.annotation.SuppressLint
import com.developersancho.rortycompose.data.repository.welcome.WelcomeRepository
import com.developersancho.rortycompose.domain.usecase.welcome.ReadOnBoarding
import com.developersancho.rortycompose.domain.usecase.welcome.SaveOnBoarding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class WelcomeModule {

    @Singleton
    @Provides
    fun provideSaveOnBoarding(repository: WelcomeRepository): SaveOnBoarding {
        return SaveOnBoarding(repository)
    }

    @Singleton
    @Provides
    fun provideReadOnBoarding(repository: WelcomeRepository): ReadOnBoarding {
        return ReadOnBoarding(repository)
    }
}