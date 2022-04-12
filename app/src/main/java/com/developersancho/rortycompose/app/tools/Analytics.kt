package com.developersancho.rortycompose.app.tools

interface Analytics {
    fun trackScreenView(screenName: String, className: String)
}