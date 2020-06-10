package com.chullian.app.persistance

import androidx.room.Dao
import androidx.room.Query
import com.chullian.app.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao : BaseDao<Item> {
    @Query("SELECT * FROM Item")
    fun getAllItems(): Flow<List<Item>>

    @Query("UPDATE Item set isLocked  = :unlock WHERE itemId = :itemId")
    suspend fun updateLockStatus(unlock: Boolean, itemId: Int)

}