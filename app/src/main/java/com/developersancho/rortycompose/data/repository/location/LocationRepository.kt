package com.developersancho.rortycompose.data.repository.location

import androidx.annotation.VisibleForTesting
import com.developersancho.rortycompose.data.local.dao.LocationFavoriteDao
import com.developersancho.rortycompose.data.model.local.location.LocationEntity
import com.developersancho.rortycompose.data.remote.service.LocationService
import javax.inject.Inject

class LocationRepository
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val service: LocationService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dao: LocationFavoriteDao
) {
    suspend fun getLocationList(
        page: Int,
        options: Map<String, String>
    ) = service.getLocationList(page, options)

    suspend fun getLocation(
        locationId: Int
    ) = service.getLocation(locationId)

    suspend fun getLocation(
        url: String
    ) = service.getLocation(url)

    suspend fun getFavoriteList() = dao.getFavoriteList()

    suspend fun getFavorite(favoriteId: Int) = dao.getFavorite(favoriteId)

    suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    suspend fun saveFavorite(entity: LocationEntity) = dao.insert(entity)

    suspend fun saveFavoriteList(entityList: List<LocationEntity>) = dao.insert(entityList)
}