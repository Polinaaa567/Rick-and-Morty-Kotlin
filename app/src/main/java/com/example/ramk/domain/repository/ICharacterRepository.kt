package com.example.ramk.domain.repository

import kotlinx.coroutines.flow.Flow

import com.example.ramk.core.remote.network.ApiResult
import com.example.ramk.domain.model.CharacterDomain


interface ICharacterRepository {
    suspend fun getCharacters(page: Int): Flow<ApiResult<CharacterDomain?>>
}