package com.example.bookmvvm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "books")
data class Book (

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String
)

