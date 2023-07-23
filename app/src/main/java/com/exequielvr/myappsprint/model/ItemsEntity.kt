package com.exequielvr.myappsprint.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_list_table")
data class ItemsEntity(

    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String
)