package com.example.lolhelperapp

class Ability {

    //declaring params
    var id: String? = null
    var name: String? = null
    var description: String? = null
    var imageUrl: String? = null

    //empty constructor
    constructor() {
    }

    //overloaded constructor with all params
    constructor(id: String, name: String, description: String, imageUrl: String){
        this.id = id
        this.name = name
        this.description = description
        this.imageUrl = imageUrl
    }
}
