package com.example.lolhelperapp

class User {
    var id: String? = null
    var userName: String? = null
    var perfeeredChampOne: String? = null
    var perfeeredChampTwo: String? = null
    var perfeeredChampThree: String? = null
    var perfeeredChampFour: String? = null

    constructor() {

    }


    constructor(id: String, userName: String, perfeeredChampOne: String, perfeeredChampTwo: String, perfeeredChampThree: String, perfeeredChampFour: String) {
        this.userName = userName
        this.id = id
        this.perfeeredChampOne = perfeeredChampOne
        this.perfeeredChampTwo = perfeeredChampTwo
        this.perfeeredChampThree = perfeeredChampThree
        this.perfeeredChampFour = perfeeredChampFour

    }

}
