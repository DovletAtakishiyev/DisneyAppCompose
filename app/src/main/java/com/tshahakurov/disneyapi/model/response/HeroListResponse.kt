package com.tshahakurov.disneyapi.model.response

import com.google.gson.annotations.SerializedName

data class HeroListResponse(
    @SerializedName("data")
    val data: ArrayList<HeroResponse.Data>
)