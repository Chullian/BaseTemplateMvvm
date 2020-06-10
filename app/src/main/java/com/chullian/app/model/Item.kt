package com.chullian.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
    var itemId: Int,
    var itemName: String,
    var isLocked: Boolean
)