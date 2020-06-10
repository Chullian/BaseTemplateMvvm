package com.chullian.app.di.components

import android.content.Context
import com.chullian.app.activities.MainActivity
import com.chullian.app.activities.SecondActivity
import com.chullian.app.di.modules.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class]
)
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SecondActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

}