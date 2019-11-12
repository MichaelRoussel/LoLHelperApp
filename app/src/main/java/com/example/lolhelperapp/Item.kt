package com.example.lolhelperapp

class Item {

    //declaring params
    var id: String? = null
    var name: String? = null
    var imageUrl: String? = null

    //empty constructor
    constructor() {
    }

    //overloaded constructor with all params
    constructor(id: String, name: String, imageUrl: String){
        this.id = id
        this.name = name
        this.imageUrl = imageUrl
    }
}