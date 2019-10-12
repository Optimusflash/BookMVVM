package com.example.bookmvvm.data.model

import com.google.gson.annotations.SerializedName

data class BookInfo(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageLinks")
    val bookImage: BookImage) {
}