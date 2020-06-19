package com.chullian.template.di.components

import android.content.Context
import com.chullian.template.di.modules.DatabaseModule
import com.chullian.template.ui.activities.MainActivity
import com.chullian.template.ui.activities.SecondActivity
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