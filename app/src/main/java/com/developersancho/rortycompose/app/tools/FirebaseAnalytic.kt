package com.developersancho.rortycompose.app.tools

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.developersancho.framework.extension.deviceId
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

@Composable
fun rememberAnalytics(): FirebaseAnalytics {
    val context = LocalContext.current
    val analytics: FirebaseAnalytics = remember {
        FirebaseAnalytics.getInstance(context)
    }
    SideEffect {
        analytics.setUserId(context.deviceId())
    }
    return analytics
}

@Composable
fun TrackScreenView(screenName: String, className: String) {
    val analytics = rememberAnalytics()
    analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
        param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        param(FirebaseAnalytics.Param.SCREEN_CLASS, className)
    }
}