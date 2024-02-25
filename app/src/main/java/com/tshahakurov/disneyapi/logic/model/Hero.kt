package com.tshahakurov.disneyapi.logic.model

data class Hero(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val characteristics: ArrayList<Pair<String, ArrayList<String>>>,
    var favorite: Boolean = false
){
    companion object {
        const val FILMS_FIELD = "Films"
        const val SHORT_FILMS_FIELD = "Short Films"
        const val TV_SHOWS_FIELD = "TV Shows"
        const val VIDEO_GAMES_FIELD = "Video Games"
        const val PARK_ATTRACTIONS_FIELD = "Amusement Parks"
        const val ALLIES_FIELD = "Allies"
        const val ENEMIES_FIELD = "Enemies"
    }
}

