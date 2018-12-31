package com.example.rik.myapplication.network

import com.example.rik.myapplication.domain.models.User
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

class Api private constructor() {
    //network connection
    private object Holder {
        val INSTANCE = Api()
    }

    init {
        //only local for now, this has to be changed if backend runs online
        FuelManager.instance.basePath = "http://scoutsappbackend.westeurope.cloudapp.azure.com/api"
        //"http://10.0.2.2:5000/api"
    }

    //functions do as the names imply

    fun checkUsername(name: String): Request =
        "User/CheckUsername/$name"
            .httpGet()

    fun createUser(user: User): Request =
        "User/CreateUser"
            .httpPost().body(Gson().toJson(user)).header(mapOf("Content-Type" to "application/json"))

    fun login(username: String, password: String): Request =
            "User/Login"
                .httpPost().body("{'Username' : '$username', 'Password' : '$password'}").header(mapOf("Content-Type" to "application/json"))

    fun updateUser(username: String, balance: String): Request =
            "User/UpdateUser"
                .httpPost().body("{'Username' : '$username', 'Balance' : '$balance'}").header(mapOf("Content-Type" to "application/json"))

    fun getUser(name: String): Request =
        "User/GetUser/$name"
            .httpGet()

    fun getBarCashRegister(name: String): Request =
        "User/GetBarcashRegister/$name"
            .httpGet()

    fun updateBarCashRegister(name: String, budget: Int): Request =
        "User/UpdateBarCashRegister"
            .httpPost().body("{'Name' : '$name', 'Balance' : '$budget'}").header(mapOf("Content-Type" to "application/json"))

    companion object {
        //instance to use in the classes
        val instance: Api by lazy { Holder.INSTANCE }
    }
}