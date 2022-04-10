package com.developersancho.rortycompose.domain.di

import android.annotation.SuppressLint
import com.developersancho.rortycompose.data.repository.langauge.LanguageRepository
import com.developersancho.rortycompose.domain.usecase.language.GetLanguage
import com.developersancho.rortycompose.domain.usecase.language.SaveLanguage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@SuppressLint("VisibleForTests")
@Module
@InstallIn(SingletonComponent::class)
class LanguageModule {

    @Singleton
    @Provides
    fun provideSaveLanguage(repository: LanguageRepository): SaveLanguage {
        return SaveLanguage(repository)
    }

    @Singleton
    @Provides
    fun provideGetLanguage(repository: LanguageRepository): GetLanguage {
        return GetLanguage(repository)
    }
}