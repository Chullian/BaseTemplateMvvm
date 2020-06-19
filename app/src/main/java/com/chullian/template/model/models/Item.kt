package com.chullian.template.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey
    var itemId: Int,
    var itemName: String,
    var isLocked: Boolean
)