package com.developersancho.rortycompose.domain.usecase.episode.favorite

import androidx.annotation.VisibleForTesting
import com.developersancho.framework.usecase.LocalUseCase
import com.developersancho.rortycompose.data.model.dto.episode.EpisodeDto
import com.developersancho.rortycompose.data.model.dto.episode.toEpisodeEntity
import com.developersancho.rortycompose.data.repository.episode.EpisodeRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AddEpisodeFavorite @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val repository: EpisodeRepository
) : LocalUseCase<AddEpisodeFavorite.Params, Unit>() {

    data class Params(
        val episode: EpisodeDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.episode
        repository.saveFavorite(dto.toEpisodeEntity())
        emit(Unit)
    }
}