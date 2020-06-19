package com.chullian.template.persistance.database

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE


@Dao
interface BaseDao<T> {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = IGNORE)
    suspend fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = IGNORE)
    @JvmSuppressWildcards
    suspend fun insertAll(obj: List<T>)

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    suspend fun update(obj: T)

    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: T)


    /**
     * Insert or update an object in the database
     *
     * @param obj the object to be inserted or updated
     */
    @Transaction
    suspend fun upsert(obj: T) {
        val id: Long = insert(obj)
        if (id == -1L) {
            update(obj)
        }
    }

}