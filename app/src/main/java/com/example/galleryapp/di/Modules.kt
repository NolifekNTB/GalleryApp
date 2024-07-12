package com.example.galleryapp.di

import com.example.galleryapp.data.PagingRepository
import com.example.galleryapp.data.remote.ResponseService
import com.example.galleryapp.presentation.PagingViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelRepository = module {
    viewModel { PagingViewModel(get()) }
}

val repositoryHttp = module {
    single { PagingRepository(get()) }
}

val networkModule = module {
    single { ResponseService(get()) }

    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    useAlternativeNames = false
                })
            }
        }
    }
}
