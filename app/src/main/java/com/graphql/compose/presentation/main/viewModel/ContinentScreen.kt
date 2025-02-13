package com.graphql.compose.presentation.main.viewModel

import src.main.graphql.ContinentFetchingQuery

object ContinentScreen {
    data class UiState(
        val data: List<ContinentFetchingQuery.Continent>? = null,
        val isLoading: Boolean = false,
        val error: String = ""
    )
}