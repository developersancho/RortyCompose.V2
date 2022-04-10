package com.developersancho.rortycompose.presentation.characters.detail

import com.developersancho.rortycompose.data.model.dto.character.CharacterDto

data class CharacterDetailViewState(
    val character: CharacterDto? = null
)

sealed class CharacterDetailEvent {
    data class LoadDetail(val id: Int) : CharacterDetailEvent()
}