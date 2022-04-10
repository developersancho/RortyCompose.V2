package com.developersancho.rortycompose.presentation.locations.detail

import com.developersancho.rortycompose.data.model.dto.location.LocationDto

data class LocationDetailState(
    val location: LocationDto? = null
)

sealed class LocationDetailEvent {
    data class LoadDetail(val id: Int) : LocationDetailEvent()
}