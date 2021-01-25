package com.goofy.goober.vyasa

import android.app.Application
import com.facebook.stetho.Stetho
import com.goofy.goober.api.di.networkModule
import com.goofy.goober.data.di.dataModule
import com.goofy.goober.domain.di.domainModule
import com.goofy.goober.vyasa.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VyasaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        Stetho.initializeWithDefaults(this);
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@VyasaApplication)
            modules(
                listOf(
                    networkModule,
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }
}
