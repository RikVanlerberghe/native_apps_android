package com.example.rik.myapplication.network

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet

class Api private constructor() {

    private object Holder {
        val INSTANCE = Api()
    }

    init {
        FuelManager.instance.basePath = "http://10.0.2.2:5001/api"
    }

    fun checkUsername(groupname: String): Request =
        "User/CheckUsername/$groupname"
            .httpGet()

    companion object {
        val instance: Api by lazy { Holder.INSTANCE }
    }
}