package com.developersancho.rortycompose.app

import com.developersancho.framework.base.app.NetworkConfig
import com.developersancho.rortycompose.BuildConfig

class JetNetworkConfig : NetworkConfig() {
    override fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    override fun timeOut(): Long {
        return 30L
    }

    override fun isDev(): Boolean {
        return BuildConfig.DEBUG
    }
}