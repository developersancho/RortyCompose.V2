package com.developersancho.rortycompose.data.repository.di

import com.developersancho.rortycompose.data.local.dao.CharacterFavoriteDao
import com.developersancho.rortycompose.data.local.dao.EpisodeFavoriteDao
import com.developersancho.rortycompose.data.local.dao.LocationFavoriteDao
import com.developersancho.rortycompose.data.remote.service.CharacterService
import com.developersancho.rortycompose.data.remote.service.EpisodeService
import com.developersancho.rortycompose.data.remote.service.LocationService
import com.developersancho.testutils.MockkUnitTest
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class RepositoryModuleTest : MockkUnitTest() {

    private lateinit var repositoryModule: RepositoryModule

    override fun onCreate() {
        super.onCreate()
        repositoryModule = RepositoryModule()
    }

    @Test
    fun verifyProvideCharacterRepository() {
        val service = mockk<CharacterService>()
        val dao = mockk<CharacterFavoriteDao>()
        val repository = repositoryModule.provideCharacterRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }

    @Test
    fun verifyProvideEpisodeRepository() {
        val service = mockk<EpisodeService>()
        val dao = mockk<EpisodeFavoriteDao>()
        val repository = repositoryModule.provideEpisodeRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }

    @Test
    fun verifyProvideLocationRepository() {
        val service = mockk<LocationService>()
        val dao = mockk<LocationFavoriteDao>()
        val repository = repositoryModule.provideLocationRepository(service, dao)

        Assert.assertEquals(service, repository.service)
        Assert.assertEquals(dao, repository.dao)
    }
}