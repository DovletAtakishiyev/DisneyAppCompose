package com.tshahakurov.disneyapi.network_eji

import com.tshahakurov.disneyapi.model.response.HeroListResponse
import com.tshahakurov.disneyapi.model.response.HeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val DEFAULT_PAGE_SIZE = 149

interface SuitaApi {

    @GET("character/206")
    suspend fun getHero(): Response<HeroResponse>

    @GET("character/{id}")
    suspend fun getHeroById(@Path("id") id: String): Response<HeroResponse>

    @GET("character")
    suspend fun getHeroList(@Query("pageSize") count: Int = DEFAULT_PAGE_SIZE): Response<HeroListResponse>
}