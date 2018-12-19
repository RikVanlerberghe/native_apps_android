package com.example.rik.myapplication.domain.models

class Card {
    var id: String = ""
    var numberOfDrinksLeft: Int = 10

    constructor(id: String, numberOfDrinksLeft: Int){
        this.id = id
        this.numberOfDrinksLeft = numberOfDrinksLeft
    }

    constructor(){}

}