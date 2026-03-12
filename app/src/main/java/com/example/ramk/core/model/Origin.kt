package com.example.ramk.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    @SerialName(value = "name")
    val name: String? = null,

    @SerialName(value = "url")
    val url: String? = null
)