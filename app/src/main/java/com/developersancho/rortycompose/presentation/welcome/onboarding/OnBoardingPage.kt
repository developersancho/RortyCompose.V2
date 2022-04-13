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
        title = "Characters",
        description = "You can access the list of characters and details."
    )

    object Second : OnBoardingPage(
        image = R.drawable.intro_2,
        title = "Episodes",
        description = "You can access the list of episodes and details."
    )

    object Third : OnBoardingPage(
        image = R.drawable.intro_3,
        title = "Locations",
        description = "You can access the list of locations and details."
    )
}