package com.graphql.compose.domain.repository

import src.main.graphql.ContinentFetchingQuery

interface CountryRepo {
    suspend fun getContinents(): Result<ContinentFetchingQuery.Data>
}