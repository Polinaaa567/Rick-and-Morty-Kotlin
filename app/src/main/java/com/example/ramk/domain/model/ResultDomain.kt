package com.example.ramk.domain.model

data class ResultDomain(
    val id: Int?,
    val name: String?,
    val status: String?,
    val type: String?,
    val gender: String?,
    val origin: OriginDomain?,
    val location: LocationDomain?,
    val image: String?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
    val species: String?,
    var isFavourite: Boolean = false
)
