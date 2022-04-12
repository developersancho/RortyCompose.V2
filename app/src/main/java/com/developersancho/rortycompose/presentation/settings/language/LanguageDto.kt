package com.developersancho.rortycompose.presentation.settings.language

data class LanguageDto(
    val id: Int,
    val code: String,
    val name: String,
    var isSelected: Boolean = false
)