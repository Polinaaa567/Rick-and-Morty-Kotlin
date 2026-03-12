package com.example.ramk.core.mapper

import com.example.ramk.core.model.Character
import com.example.ramk.core.model.Info
import com.example.ramk.core.model.Location
import com.example.ramk.core.model.Origin
import com.example.ramk.core.model.Results
import com.example.ramk.domain.model.CharacterDomain
import com.example.ramk.domain.model.InfoDomain
import com.example.ramk.domain.model.LocationDomain
import com.example.ramk.domain.model.OriginDomain
import com.example.ramk.domain.model.ResultDomain


fun Origin.toDomain(): OriginDomain = OriginDomain(name = name, url = url)
fun Location.toDomain(): LocationDomain = LocationDomain(name = name, url = url)

fun Results.toDomain(isFavourite: Boolean = false): ResultDomain {
    return ResultDomain(
        id = this.id,
        name = this.name,
        status = this.status,
        species = this.species,
        type = this.type,
        url = this.url,
        episode = this.episode,
        gender = this.gender,
        origin = this.origin?.toDomain(),
        location = this.location?.toDomain(),
        image = this.image,
        created = this.created,
        isFavourite = isFavourite
    )
}

fun Info.toDomain(): InfoDomain {
    return InfoDomain(
        count = this.count,
        pages = this.pages,
        next = this.next,
        prev = this.prev
    )
}

fun Character.toDomain(): CharacterDomain {
    return CharacterDomain(
        info = this.info?.toDomain(),
        results = this.results?.map { it.toDomain() }
    )
}