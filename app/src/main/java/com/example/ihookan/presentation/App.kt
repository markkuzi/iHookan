package com.example.ihookan.presentation

import android.app.Application
import com.example.ihookan.presentation.di.moduleBasket
import com.example.ihookan.presentation.di.moduleOrder
import com.example.ihookan.presentation.di.moduleOrderApi
import com.example.ihookan.presentation.di.moduleProducts
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@App)

            modules(moduleProducts, moduleBasket, moduleOrder, moduleOrderApi)

        }

    }


}