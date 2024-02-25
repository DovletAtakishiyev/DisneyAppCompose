package com.tshahakurov.disneyapi.util

import com.tshahakurov.disneyapi.logic.model.Hero
import com.tshahakurov.disneyapi.logic.model.response.HeroListResponse
import com.tshahakurov.disneyapi.logic.model.response.HeroResponse



fun HeroListResponse.toHeroList(): ArrayList<Hero> {
    val list = arrayListOf<Hero>()
    data.forEach {
        list.add(
            it.toHero()
        )
    }
    return list
}

fun HeroResponse.toHero(): Hero? {
    return data?.let {
        with(data) {
            Hero(
                id = id,
                name = name,
                imageUrl = imageUrl,
                characteristics = arrayListOf(
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.FILMS_FIELD, films),
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.SHORT_FILMS_FIELD, shortFilms),
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.TV_SHOWS_FIELD, tvShows),
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.VIDEO_GAMES_FIELD, videoGames),
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.PARK_ATTRACTIONS_FIELD, parkAttractions),
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.ALLIES_FIELD, allies),
                    Pair(com.tshahakurov.disneyapi.logic.model.Hero.ENEMIES_FIELD, enemies),
                )
            )
        }
    }
}

fun HeroResponse.Data.toHero(): Hero {
    return Hero(
        id = id,
        name = name,
        imageUrl = imageUrl,
        characteristics = arrayListOf(
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.FILMS_FIELD, films),
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.SHORT_FILMS_FIELD, shortFilms),
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.TV_SHOWS_FIELD, tvShows),
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.VIDEO_GAMES_FIELD, videoGames),
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.PARK_ATTRACTIONS_FIELD, parkAttractions),
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.ALLIES_FIELD, allies),
            Pair(com.tshahakurov.disneyapi.logic.model.Hero.ENEMIES_FIELD, enemies),
        )
    )
}