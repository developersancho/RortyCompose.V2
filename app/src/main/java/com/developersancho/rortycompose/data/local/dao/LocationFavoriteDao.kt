package com.developersancho.rortycompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.developersancho.framework.room.dao.BaseDao
import com.developersancho.rortycompose.data.model.local.location.LocationEntity

@Dao
interface LocationFavoriteDao : BaseDao<LocationEntity> {
    @Query("SELECT * FROM ${LocationEntity.TABLE_NAME}")
    suspend fun getFavoriteList(): List<LocationEntity>

    @Query("SELECT * FROM ${LocationEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun getFavorite(favoriteId: Int): LocationEntity?

    @Query("DELETE FROM ${LocationEntity.TABLE_NAME}")
    suspend fun deleteFavoriteList()

    @Query("DELETE FROM ${LocationEntity.TABLE_NAME} WHERE id = :favoriteId")
    suspend fun deleteFavoriteById(favoriteId: Int)
}