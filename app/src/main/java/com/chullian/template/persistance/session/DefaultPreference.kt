package com.chullian.template.persistance.session

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject

class DefaultPreference @Inject constructor(context: Context) {

    private val preferences = context.getSharedPreferences("DEFAULT_SESSION", Context.MODE_PRIVATE)

    var areItemsLoaded: Boolean
        get() = preferences.getBoolean(
            "areItemsLoaded", false
        )
        set(value) {
            preferences.edit(false) { putBoolean("areItemsLoaded", value) }
        }

    var token: String = ""

}
