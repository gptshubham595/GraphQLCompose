package com.graphql.compose.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.graphql.compose.presentation.main.viewModel.ContinentsViewModel
import src.main.graphql.ContinentFetchingQuery

@Composable
fun ContinentScreen(
    modifier: Modifier = Modifier,
    viewModel: ContinentsViewModel
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when {
        uiState.value.isLoading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // Loading
                CircularProgressIndicator()
            }
        }

        uiState.value.error.isNotEmpty() -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                // Error
                Text(text = uiState.value.error)
            }
        }

        else -> {
            // Success
            ContinentList(continentList = uiState.value.data.orEmpty())
        }
    }
}

@Composable
fun ContinentList(continentList: List<ContinentFetchingQuery.Continent>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(continentList) { continent ->
            Card(modifier = Modifier.fillMaxSize()) {
                ContinentItem(continent = continent)
            }
        }

    }
}

@Composable
fun ContinentItem(continent: ContinentFetchingQuery.Continent) {
    Text(
        text = continent.name,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(12.dp)
    )
}

@Preview
@Composable
fun ContinentListPreview() {
    val continentList = listOf(
        ContinentFetchingQuery.Continent(
            name = "Asia",
            code = "AS",
            countries = emptyList()
        )
    )
    ContinentList(continentList = continentList)
}