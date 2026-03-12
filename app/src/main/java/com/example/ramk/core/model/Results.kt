package com.example.ramk.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Results(
    @SerialName(value = "id")
    val id: Int? = null,

    @SerialName(value = "name")
    val name: String? = null,

    @SerialName(value = "status")
    val status: String? = null,

    @SerialName(value = "type")
    val type: String? = null,

    @SerialName(value = "gender")
    val gender: String? = null,

    @SerialName(value = "origin")
    val origin: Origin? = null,

    @SerialName(value = "location")
    val location: Location? = null,

    @SerialName(value = "image")
    val image: String? = null,

    @SerialName(value = "episode")
    val episode: List<String>? = emptyList(),

    @SerialName(value = "url")
    val url: String? = null,

    @SerialName(value = "created")
    val created: String? = null,

    @SerialName(value = "species")
    val species: String? = null,
)