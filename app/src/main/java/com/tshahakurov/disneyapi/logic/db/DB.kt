package com.tshahakurov.disneyapi.logic.db

import com.tshahakurov.disneyapi.logic.model.Hero

object DB {

    val defHero = Hero(
        id = 99,
        name = "Default",
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.championat.com%2Fboxing%2Farticle-4872117-hasbik-bloger-hasbulla-magomedov-priletel-na-vertolyote-v-bahrejn-na-turnir-brave-video.html&psig=AOvVaw2ieAkKP0D9HL28ZhSWtpBU&ust=1707423841664000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCIC7386HmoQDFQAAAAAdAAAAABAJ",
        arrayListOf(
            Pair("Char 1" , arrayListOf("item 1", "aaaaaaaa item 2", "i3", "itemkkkjkjkjkjkk 4")),
            Pair("Char 2" , arrayListOf("item 1", "aaaaaaaa item 2", "i3", "itemkkkjkjkjkjkk 4")),
            Pair("Char 3" , arrayListOf("item 1", "aaaaaaaa item 2", "i3", "itemkkkjkjkjkjkk 4")),
            Pair("Char 4" , arrayListOf("item 1", "aaaaaaaa item 2", "i3", "itemkkkjkjkjkjkk 4")),
        )
    )
}