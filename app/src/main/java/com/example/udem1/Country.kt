package com.example.udem1

import com.google.gson.annotations.SerializedName

data class Country(
    val name: Name,
    val capital: List<String>,
    val population: Long,
    val area: Double,
    val flags: Flag,
    val region: String
    )


data class Name (
        @SerializedName("common")
        val common: String)

data class Flag (val svg: String)
