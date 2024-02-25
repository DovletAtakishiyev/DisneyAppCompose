package com.tshahakurov.disneyapi.logic.repository

import com.tshahakurov.disneyapi.logic.network_eji.SuitaApi
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val suitaApi: SuitaApi
) {

    suspend fun getHeroList() = suitaApi.getHeroList()

    suspend fun findHeroById(id: Int) = suitaApi.getHeroById(id.toString())
}