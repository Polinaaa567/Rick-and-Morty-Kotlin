package com.example.ramk.core.repository

import android.content.Context
import com.example.ramk.core.local.dao.CharacterDao
import com.example.ramk.core.local.database.DatabaseProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import com.example.ramk.core.mapper.toDomain
import com.example.ramk.core.remote.api.ApiService
import com.example.ramk.core.remote.network.ApiResult
import com.example.ramk.core.remote.network.RetrofitInstance
import com.example.ramk.domain.model.CharacterDomain
import com.example.ramk.domain.repository.ICharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class CharacterRepository(
    private val apiService: ApiService,
    private val characterDao: CharacterDao
) : ICharacterRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override suspend fun getCharacters(page: Int): Flow<ApiResult<CharacterDomain?>> = flow {
        emit(value = ApiResult.Loading)

        try {
            val response = apiService.getCharacters(page)

            if (response.isSuccessful) {
                val body = response.body()
                if (body?.results != null) {
                    emit(value = ApiResult.Success(data = body.toDomain()))
                } else {
                    emit(value = ApiResult.Error(message = "Герои не найдены"))
                }
            } else {
                emit(
                    value = ApiResult.Error(
                        message = "Error",
                        code = response.code()
                    )
                )
            }
        } catch (e: Exception) {
            emit(value = ApiResult.Error(message = "Ошибка сети: нет подключения к сети Интернет : ${e.message}"))
        }
    }

//    override fun addFavourite
}