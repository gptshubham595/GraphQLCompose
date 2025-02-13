package com.graphql.compose.domain.usecases

import com.graphql.compose.domain.NetworkResult
import com.graphql.compose.domain.repository.CountryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import src.main.graphql.ContinentFetchingQuery
import javax.inject.Inject

class FetchContinentsUseCase @Inject constructor(
    private val countryRepo: CountryRepo
) {
    operator fun invoke() = flow<NetworkResult<ContinentFetchingQuery.Data>> {
        emit(NetworkResult.Loading)
        val result = countryRepo.getContinents()
        when {
            result.isSuccess -> emit(NetworkResult.Success(result.getOrThrow()))
            result.isFailure -> emit(NetworkResult.Error(result.exceptionOrNull()))
        }
    }.catch { e ->
        emit(NetworkResult.Error(e))
    }.flowOn(Dispatchers.IO)
}