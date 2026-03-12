package com.example.ramk.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName(value = "count")
    val count: Int? = null,

    @SerialName(value = "pages")
    val pages: Int? = null,

    @SerialName(value = "next")
    val next: String? = null,

    @SerialName(value = "prev")
    val prev: String? = null
)
