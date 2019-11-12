package com.example.lolhelperapp

class Champion {
    var Id: String? = null
    var name: String? = null
    var role: String? = null
    var items = arrayListOf<Item>()
    var winRate: Double = 0.0
    var abilities = arrayListOf<Ability>()
    var keystoneRune: KeystoneRune? = null
    var secondaryRunes = arrayListOf<BasicRune>()
    var imageUrl: String? = null



}