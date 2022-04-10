package com.developersancho.rortycompose.presentation.locations.detail

import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.rortycompose.domain.usecase.location.GetLocationDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val getLocationDetail: GetLocationDetail
) : MviViewModel<BaseViewState<LocationDetailState>, LocationDetailEvent>() {

    override fun onTriggerEvent(eventType: LocationDetailEvent) {
        when (eventType) {
            is LocationDetailEvent.LoadDetail -> onLoadDetail(eventType.id)
        }
    }

    private fun onLoadDetail(id: Int) = safeLaunch {
        val params = GetLocationDetail.Params(detailId = id)
        execute(getLocationDetail(params)) { dto ->
            setState(BaseViewState.Data(LocationDetailState(dto)))
        }
    }
}