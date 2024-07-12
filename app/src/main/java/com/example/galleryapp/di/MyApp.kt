package com.example.galleryapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initializeKoin()
    }

    private fun initializeKoin(){
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(networkModule)
            modules(repositoryHttp)
            modules(viewModelRepository)
        }
    }
}

