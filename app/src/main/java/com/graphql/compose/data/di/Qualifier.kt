package com.graphql.compose.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitClientQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApolloClientQualifier