package com.example.lolhelperapp

class User {
    var id: String? = null
    var userName: String? = null
    var preferredChampOne: String? = null
    var preferredChampTwo: String? = null
    var preferredChampThree: String? = null
    var preferredChampFour: String? = null

    constructor() {

    }


    constructor(id: String, userName: String, perfeeredChampOne: String, perfeeredChampTwo: String, perfeeredChampThree: String, perfeeredChampFour: String) {
        this.userName = userName
        this.id = id
        this.preferredChampOne = perfeeredChampOne
        this.preferredChampTwo = perfeeredChampTwo
        this.preferredChampThree = perfeeredChampThree
        this.preferredChampFour = perfeeredChampFour

    }
    constructor(id: String, userName: String) {
        this.userName = userName
        this.id = id
    }

}
