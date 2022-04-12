package com.developersancho.rortycompose.presentation.welcome.onboarding

import androidx.annotation.DrawableRes
import com.developersancho.rortycompose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.intro_1,
        title = "Meeting",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Second : OnBoardingPage(
        image = R.drawable.intro_2,
        title = "Coordination",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )

    object Third : OnBoardingPage(
        image = R.drawable.intro_3,
        title = "Dialogue",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."
    )
}