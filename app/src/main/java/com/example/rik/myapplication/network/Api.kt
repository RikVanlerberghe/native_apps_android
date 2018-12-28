package com.example.rik.myapplication.network

import com.example.rik.myapplication.domain.models.User
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

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

    fun createUser(user: User): Request =
        "User/CreateUser"
            .httpPost().body(Gson().toJson(user)).header(mapOf("Content-Type" to "application/json"))

    fun login(username: String, password: String): Request =
            "User/Login"
                .httpPost().body("{'Username' : '$username', 'Password' : '$password'}").header(mapOf("Content-Type" to "application/json"))

    fun updateUser(user: User): Request =
            "User/UpdateUser"
                .httpPost().body(Gson().toJson(user)).header(mapOf("Content-Type" to "application/json"))

    fun getUser(name: String): Request =
        "User/GetUser/$name"
            .httpGet()

    companion object {
        val instance: Api by lazy { Holder.INSTANCE }
    }
}