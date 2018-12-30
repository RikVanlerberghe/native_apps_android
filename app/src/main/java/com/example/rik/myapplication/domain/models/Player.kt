package com.example.rik.myapplication.domain.models

class Player {
    //Player class for in the database, and the game
    var name: String = ""
    var fifteen: Int = 0
    var sixteen: Int = 0
    var seventeen: Int = 0
    var eighteen: Int = 0
    var nineteen: Int = 0
    var twenty: Int = 0
    var bull: Int = 0
    var score: Int = 0

    constructor(name: String){
        this.name = name
    }

    constructor(){}
}