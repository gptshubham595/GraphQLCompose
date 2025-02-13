package com.graphql.compose.data.repository

import com.apollographql.apollo.ApolloClient
import com.graphql.compose.data.di.ApolloClientQualifier
import com.graphql.compose.domain.repository.CountryRepo
import src.main.graphql.ContinentFetchingQuery
import javax.inject.Inject

class CountryRepoImpl @Inject constructor(@ApolloClientQualifier private val apolloClient: ApolloClient) :
    CountryRepo {
    override suspend fun getContinents(): Result<ContinentFetchingQuery.Data> {
        return try {
            val response = apolloClient.query(ContinentFetchingQuery()).execute()
            response.data?.let {
                Result.success(it)
            } ?: Result.failure(Exception("No data found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}