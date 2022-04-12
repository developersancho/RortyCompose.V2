package com.developersancho.rortycompose.app.di

import android.content.Context
import com.developersancho.framework.base.app.AppInitializer
import com.developersancho.framework.base.app.AppInitializerImpl
import com.developersancho.framework.base.app.NetworkConfig
import com.developersancho.framework.base.app.TimberInitializer
import com.developersancho.framework.pref.CacheManager
import com.developersancho.rortycompose.app.JetNetworkConfig
import com.developersancho.rortycompose.app.JetRortyApp
import com.developersancho.rortycompose.app.tools.Analytics
import com.developersancho.rortycompose.app.tools.RortyAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApplication(): JetRortyApp {
        return JetRortyApp()
    }

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return JetNetworkConfig()
    }

    @Provides
    @Singleton
    fun provideCacheManager(@ApplicationContext context: Context): CacheManager {
        return CacheManager(context)
    }

    @Provides
    @Singleton
    fun provideTimberInitializer(
        networkConfig: NetworkConfig
    ) = TimberInitializer(networkConfig.isDev())

    @Provides
    @Singleton
    fun provideAppInitializer(
        timberInitializer: TimberInitializer
    ): AppInitializer {
        return AppInitializerImpl(timberInitializer)
    }

    @Provides
    @Singleton
    fun provideRortyAnalytics(@ApplicationContext context: Context): Analytics {
        return RortyAnalytics(context)
    }
}