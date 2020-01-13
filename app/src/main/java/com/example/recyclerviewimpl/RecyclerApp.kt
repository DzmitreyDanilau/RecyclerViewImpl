package com.example.recyclerviewimpl

import android.app.Application
import com.example.recyclerviewimpl.di.AppComponent
import com.example.recyclerviewimpl.di.DaggerAppComponent
import timber.log.Timber

class RecyclerApp : Application() {

    val appComponent: AppComponent by lazy {
        initComponent()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

    private fun initComponent(): AppComponent {

    return DaggerAppComponent.factory().create(applicationContext)
    }
}