package com.example.rik.myapplication.network

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet

class Api private constructor() {

    private object Holder {
        val INSTANCE = Api()
    }

    init {
        FuelManager.instance.basePath = "http://10.0.2.2:5000/api"
    }

    fun checkUsername(name: String): Request =
        "User/CheckUsername/$name"
            .httpGet()

    fun getUser(name: String): Request =
        "User/GetUser/$name"
            .httpGet()

    companion object {
        val instance: Api by lazy { Holder.INSTANCE }
    }
}