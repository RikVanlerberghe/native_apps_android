package com.example.rik.myapplication.domain.models

class User {
    //User class
    var username: String = ""
    var password: String = ""
    var balance: Int = 0

    constructor(username: String, password: String, balance: Int){
        this.username = username
        this.password = password
        this.balance = balance
    }

    constructor(){}
}