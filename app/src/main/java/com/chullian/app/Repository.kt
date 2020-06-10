package com.chullian.app

import com.chullian.app.model.Item
import com.chullian.app.persistance.AppDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class Repository @Inject constructor(
    private val db: AppDatabase
) {
    suspend fun insertItems(items: List<Item>) {
        db.itemDao.insertAll(items)
    }

    @InternalCoroutinesApi
    suspend fun getAllItems(
        result: (List<Item>) -> (Unit)
    ) {
        db.itemDao.getAllItems().collect {
            var items = mutableListOf<Item>()
            it.forEach { item ->
                items.add(item)
            }
            result(items)
        }
    }

    suspend fun updateLockStatus(itemId:Int){
        db.itemDao.updateLockStatus(false,itemId)
    }
}