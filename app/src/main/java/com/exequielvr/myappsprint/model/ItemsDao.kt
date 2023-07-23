package com.exequielvr.myappsprint.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllItems(listCourses: List<ItemsEntity>)


    @Query("SELECT * FROM items_list_table  ORDER BY id ASC")
    fun getAllItems(): LiveData<List<ItemsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemDetail(item: ItemsDetailEntity)

    @Query("SELECT * FROM items_details_table  WHERE id=:id")
    fun  getItemDetailByID(id:String): LiveData<ItemsDetailEntity?>

}