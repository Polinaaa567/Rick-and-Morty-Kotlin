package com.example.ramk.domain.usecase

import kotlinx.coroutines.flow.Flow

import com.example.ramk.core.remote.network.ApiResult
import com.example.ramk.core.repository.CharacterRepository
import com.example.ramk.domain.model.CharacterDomain


class GetCharacterUseCase {
    private val repository = CharacterRepository()
    suspend operator fun invoke(page: Int): Flow<ApiResult<CharacterDomain?>> {
        return repository.getCharacters(page = page);
    }
}