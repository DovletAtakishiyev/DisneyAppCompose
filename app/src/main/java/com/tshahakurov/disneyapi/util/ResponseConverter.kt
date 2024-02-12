package com.tshahakurov.disneyapi.util

import com.tshahakurov.disneyapi.model.Hero
import com.tshahakurov.disneyapi.model.response.HeroListResponse
import com.tshahakurov.disneyapi.model.response.HeroResponse



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
                    Pair(Hero.FILMS_FIELD, films),
                    Pair(Hero.SHORT_FILMS_FIELD, shortFilms),
                    Pair(Hero.TV_SHOWS_FIELD, tvShows),
                    Pair(Hero.VIDEO_GAMES_FIELD, videoGames),
                    Pair(Hero.PARK_ATTRACTIONS_FIELD, parkAttractions),
                    Pair(Hero.ALLIES_FIELD, allies),
                    Pair(Hero.ENEMIES_FIELD, enemies),
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
            Pair(Hero.FILMS_FIELD, films),
            Pair(Hero.SHORT_FILMS_FIELD, shortFilms),
            Pair(Hero.TV_SHOWS_FIELD, tvShows),
            Pair(Hero.VIDEO_GAMES_FIELD, videoGames),
            Pair(Hero.PARK_ATTRACTIONS_FIELD, parkAttractions),
            Pair(Hero.ALLIES_FIELD, allies),
            Pair(Hero.ENEMIES_FIELD, enemies),
        )
    )
}