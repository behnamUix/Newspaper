package com.behnamuix.newspaper

import android.app.Application
import com.behnamuix.newspaper.koin.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewspaperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewspaperApp)
            modules(dbModule)


        }
    }
}