package com.developersancho.rortycompose.data.repository.character

import androidx.annotation.VisibleForTesting
import com.developersancho.rortycompose.data.local.dao.CharacterFavoriteDao
import com.developersancho.rortycompose.data.model.local.character.CharacterEntity
import com.developersancho.rortycompose.data.remote.service.CharacterService
import javax.inject.Inject

class CharacterRepository
@Inject
constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val service: CharacterService,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val dao: CharacterFavoriteDao
) {
    suspend fun getCharacterList(
        page: Int,
        options: Map<String, String>
    ) = service.getCharacterList(page, options)

    suspend fun getCharacter(
        characterId: Int
    ) = service.getCharacter(characterId)

    suspend fun getCharacter(
        url: String
    ) = service.getCharacter(url)

    suspend fun getFavoriteList() = dao.getFavoriteList()

    suspend fun getFavorite(favoriteId: Int) = dao.getFavorite(favoriteId)

    suspend fun deleteFavoriteById(favoriteId: Int) = dao.deleteFavoriteById(favoriteId)

    suspend fun saveFavorite(entity: CharacterEntity) = dao.insert(entity)

    suspend fun saveFavoriteList(entityList: List<CharacterEntity>) = dao.insert(entityList)
}