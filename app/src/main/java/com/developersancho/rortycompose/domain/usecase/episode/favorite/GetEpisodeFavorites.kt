package com.developersancho.rortycompose.domain.usecase.episode.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.rortycompose.data.model.dto.episode.EpisodeDto
import com.developersancho.rortycompose.data.model.dto.episode.toFavoriteDtoList
import com.developersancho.rortycompose.data.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetEpisodeFavorites @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: EpisodeRepository
) : LocalUseCase<Unit, List<EpisodeDto>>() {

    override suspend fun FlowCollector<List<EpisodeDto>>.execute(params: Unit) {
        val favors = repository.getFavoriteList()
        emit(favors.toFavoriteDtoList())
    }
}