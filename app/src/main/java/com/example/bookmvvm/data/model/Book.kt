package com.example.bookmvvm.data.model

import com.google.gson.annotations.SerializedName


//@Entity(tableName = "books")
data class Book (
    // @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("volumeInfo")
    val bookInfo: BookInfo
)

