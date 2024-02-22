package com.tshahakurov.disneyapi.repository

import com.tshahakurov.disneyapi.network_eji.SuitaApi
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val suitaApi: SuitaApi
) {

    suspend fun getHeroList() = suitaApi.getHeroList()

    suspend fun findHeroById(id: Int) = suitaApi.getHeroById(id.toString())
}