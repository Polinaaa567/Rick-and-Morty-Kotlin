package com.example.ramk.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.asStateFlow

import com.example.ramk.core.remote.network.ApiResult
import com.example.ramk.domain.model.CharacterDomain
import com.example.ramk.domain.model.ResultDomain
import com.example.ramk.domain.usecase.GetCharacterUseCase


data class CharacterUiState(
    val result: CharacterDomain? = null,
    val page: Int = 1,
    val favouriteRickAndMortyList: List<ResultDomain>? = emptyList(),
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val isNightMode: Boolean = false,
    val isLoading: Boolean = false,
    val countFavourite: Int = 0,
)


@HiltViewModel
class CharacterViewModel : ViewModel() {
    private val getCharacterUseCase = GetCharacterUseCase()
    private val _uiState = MutableStateFlow(value = CharacterUiState())
    val uiState: StateFlow<CharacterUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        _uiState.update {
            it.copy(
                isLoading = true, error = null
            )
        }

        viewModelScope.launch {
            if (_uiState.value.page != _uiState.value.result?.info?.pages) {
                getCharacterUseCase(
                    page = _uiState.value.page
                ).collectLatest { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            println("error: ${result.message}")
                            _uiState.update { it.copy(isLoading = false, error = result.message); }
                        }

                        is ApiResult.Loading -> {}

                        is ApiResult.Success -> {
                            _uiState.update {
                                it.copy(
                                    result = result.data,
                                    page = 2,
                                    isLoading = false,
                                    isLoadingMore = result.data?.info?.pages != _uiState.value.page,
                                    error = null,
                                )
                            }

                        }

                        else -> Unit
                    }
                }
            }
        }
    }

    fun loadNextPage() {
        if (!_uiState.value.isLoadingMore) return

        val currentPage = _uiState.value.page
        val totalPages = _uiState.value.result?.info?.pages ?: 1

        if (currentPage > totalPages) return

        _uiState.update {
            it.copy(
                isLoading = true, error = null
            )
        }

        viewModelScope.launch {
            getCharacterUseCase(page = currentPage).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = result.message); }
                    }

                    is ApiResult.Loading -> {}

                    is ApiResult.Success -> {
                        val newData = result.data
                        if (newData != null) {


                            val currentResults = _uiState.value.result?.results ?: emptyList()
                            val mergedResults = currentResults + (newData.results ?: emptyList())

                            val mergedPagination = _uiState.value.result?.copy(
                                results = mergedResults, info = newData.info
                            ) ?: newData.copy(results = newData.results)

                            val nextPage =
                                if (newData.info?.next != null) currentPage + 1 else currentPage

                            _uiState.update {
                                it.copy(
                                    result = mergedPagination,
                                    page = nextPage,
                                    isLoading = false,
                                    isLoadingMore = result.data.info?.pages != _uiState.value.page,
                                    error = null,
                                )
                            }
                        }
                    }

                    else -> Unit
                }
            }
        }
    }

    fun refresh() {
        _uiState.update {
            it.copy(
                result = null,
                page = 1,
            )
        }
        loadCharacters()
    }

    fun changeMode() {
        _uiState.update {
            it.copy(
                isNightMode = !_uiState.value.isNightMode
            )
        }
    }

    fun addFavourite(character: ResultDomain?) {
        if (character == null) return

        val currentFavourites = _uiState.value.favouriteRickAndMortyList ?: emptyList()

        val newFavourites = if (character.isFavourite) {
            currentFavourites.filterNot { it.id == character.id }
        } else {
            val favouriteCopy = character.copy(isFavourite = true)
            currentFavourites + favouriteCopy
        }

        val sortedFavourites = newFavourites.sortedBy { it.name }

        updateCharacterInMainList(characterId = character.id, isFavourite = !character.isFavourite)

        _uiState.update {
            it.copy(
                favouriteRickAndMortyList = sortedFavourites,
                countFavourite = sortedFavourites.size
            )
        }
    }

    private fun updateCharacterInMainList(characterId: Int?, isFavourite: Boolean) {
        val currentResult = _uiState.value.result
        if (currentResult?.results != null) {
            val updatedResults = currentResult.results.map { result ->
                if (result.id == characterId) {
                    result.copy(isFavourite = isFavourite)
                } else {
                    result
                }
            }
            val updatedPagination = currentResult.copy(results = updatedResults)
            _uiState.update {
                it.copy(result = updatedPagination)
            }
        }
    }
}
