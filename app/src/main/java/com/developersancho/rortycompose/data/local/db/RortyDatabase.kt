package com.developersancho.rortycompose.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.developersancho.rortycompose.data.local.converter.StringListConverter
import com.developersancho.rortycompose.data.local.dao.CharacterFavoriteDao
import com.developersancho.rortycompose.data.local.dao.EpisodeFavoriteDao
import com.developersancho.rortycompose.data.local.dao.LocationFavoriteDao
import com.developersancho.rortycompose.data.model.local.character.CharacterEntity
import com.developersancho.rortycompose.data.model.local.episode.EpisodeEntity
import com.developersancho.rortycompose.data.model.local.location.LocationEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class RortyDatabase : RoomDatabase() {
    abstract fun characterFavoriteDao(): CharacterFavoriteDao
    abstract fun episodeFavoriteDao(): EpisodeFavoriteDao
    abstract fun locationFavoriteDao(): LocationFavoriteDao
}