package com.chullian.template.persistance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chullian.template.model.models.Item

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase()