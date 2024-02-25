package com.tshahakurov.disneyapi.logic.model.response

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    val data: Data?
) {
    data class Data(
        @SerializedName("_id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("imageUrl")
        val imageUrl: String?,
        @SerializedName("films")
        val films: ArrayList<String>,
        @SerializedName("shortFilms")
        val shortFilms: ArrayList<String>,
        @SerializedName("tvShows")
        val tvShows: ArrayList<String>,
        @SerializedName("videoGames")
        val videoGames: ArrayList<String>,
        @SerializedName("parkAttractions")
        val parkAttractions: ArrayList<String>,
        @SerializedName("allies")
        val allies: ArrayList<String>,
        @SerializedName("enemies")
        val enemies: ArrayList<String>,
    )
}