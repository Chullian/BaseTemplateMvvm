package com.chullian.app

import android.app.Application
import com.chullian.app.di.components.AppComponent
import com.chullian.app.di.components.DaggerAppComponent

class App : Application() {


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}