package com.example.lolhelperapp

class User {
    var id: String? = null
    var email: String? = null
    var preferredChampOne: String? = null
    var preferredChampTwo: String? = null
    var preferredChampThree: String? = null
    var preferredChampFour: String? = null

    constructor() {

    }


    constructor(id: String, email: String, preferredChampOne: String, preferredChampTwo: String?, preferredChampThree: String?, preferredChampFour: String?) {
        this.email = email
        this.id = id
        this.preferredChampOne = preferredChampOne
        this.preferredChampTwo = preferredChampTwo
        this.preferredChampThree = preferredChampThree
        this.preferredChampFour = preferredChampFour

    }
//    constructor(id: String, userName: String) {
//        this.userName = userName
//        this.id = id
//    }

}
