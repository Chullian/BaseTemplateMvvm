package com.chullian.app.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chullian.app.model.Item

@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    //    abstract val chatDao: ChatDAO
    abstract val itemDao: ItemDao

}