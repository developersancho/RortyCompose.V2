package com.developersancho.rortycompose.domain.usecase.character.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.rortycompose.data.model.dto.character.CharacterDto
import com.developersancho.rortycompose.data.model.dto.character.toFavoriteDtoList
import com.developersancho.rortycompose.data.repository.character.CharacterRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetCharacterFavorites @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: CharacterRepository
) : LocalUseCase<Unit, List<CharacterDto>>() {

    override suspend fun FlowCollector<List<CharacterDto>>.execute(params: Unit) {
        val favors = repository.getFavoriteList()
        emit(favors.toFavoriteDtoList())
    }
}