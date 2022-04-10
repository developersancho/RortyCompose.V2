package com.developersancho.rortycompose.presentation.episodes.detail

import com.developersancho.rortycompose.data.model.dto.episode.EpisodeDto

data class EpisodeDetailState(
    val episode: EpisodeDto? = null
)

sealed class EpisodeDetailEvent {
    data class LoadDetail(val id: Int) : EpisodeDetailEvent()
}