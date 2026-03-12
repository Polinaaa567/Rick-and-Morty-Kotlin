package com.example.ramk.domain.model

data class CharacterDomain(
    val info: InfoDomain? = null,
    val results: List<ResultDomain>? = emptyList()
)