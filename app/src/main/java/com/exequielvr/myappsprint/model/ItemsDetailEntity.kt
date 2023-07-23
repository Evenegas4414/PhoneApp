package com.exequielvr.myappsprint.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_details_table")
data class ItemsDetailEntity(

    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Int,
    val image: String
)