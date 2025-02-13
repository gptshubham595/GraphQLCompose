package com.graphql.compose.domain

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Throwable?) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}