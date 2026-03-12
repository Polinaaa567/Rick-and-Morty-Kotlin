package com.example.ramk.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Character(
    @SerialName(value = "info")
    val info: Info? = null,

    @SerialName(value = "results")
    val results: List<Results>? = emptyList()
)
