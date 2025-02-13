package com.graphql.compose.presentation.main.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.graphql.compose.domain.NetworkResult
import com.graphql.compose.domain.usecases.FetchContinentsUseCase
import com.graphql.compose.presentation.main.viewModel.ContinentScreen.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import src.main.graphql.ContinentFetchingQuery
import javax.inject.Inject

@HiltViewModel
class ContinentsViewModel @Inject constructor(
    private val fetchContinentsUseCase: FetchContinentsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getContinents()
    }

    fun getContinents() = fetchContinentsUseCase.invoke()
        .onEach { result: NetworkResult<ContinentFetchingQuery.Data> ->
            when (result) {
                NetworkResult.Loading -> {
                    _uiState.update { UiState(isLoading = true) }
                }

                is NetworkResult.Success -> {
                    _uiState.update { UiState(data = result.data.continents) }
                }

                is NetworkResult.Error -> {
                    _uiState.update { UiState(error = result.exception?.message.toString()) }
                }
            }

        }.launchIn(viewModelScope)

}

