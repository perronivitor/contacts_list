package com.example.contacts_list_app.di

import android.app.Application
import android.content.res.Resources
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import com.example.contacts_list_app.R
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)

            modules(
                listOf(
                    mainModule,
                    contactDataBase
                )

            )
        }
    }
}
