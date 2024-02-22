package com.tshahakurov.disneyapi.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val imageUrl: String?
)