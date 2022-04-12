package com.developersancho.rortycompose.presentation.characters.detail

import com.developersancho.framework.base.mvi.BaseViewState
import com.developersancho.framework.base.mvi.MviViewModel
import com.developersancho.rortycompose.app.tools.Analytics
import com.developersancho.rortycompose.domain.usecase.character.GetCharacterDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetail: GetCharacterDetail,
    private val analytics: Analytics
) : MviViewModel<BaseViewState<CharacterDetailViewState>, CharacterDetailEvent>() {

    override fun onTriggerEvent(eventType: CharacterDetailEvent) {
        when (eventType) {
            is CharacterDetailEvent.LoadDetail -> onLoadDetail(eventType.id)
        }
    }

    private fun onLoadDetail(id: Int) = safeLaunch {
        analytics.trackScreenView(
            screenName = "CharacterDetail->onLoadDetail",
            className = "CharacterDetailScreen"
        )
        val params = GetCharacterDetail.Params(detailId = id)
        execute(getCharacterDetail(params)) { dto ->
            setState(BaseViewState.Data(CharacterDetailViewState(character = dto)))
        }
    }
}