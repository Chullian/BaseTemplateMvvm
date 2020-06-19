package com.chullian.template.tools

import android.app.Application
import com.chullian.template.di.components.AppComponent
import com.chullian.template.di.components.DaggerAppComponent

class App : Application() {


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

}