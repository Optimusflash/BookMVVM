package com.example.bookmvvm.data.model

import com.google.gson.annotations.SerializedName

data class BookItems(
    @SerializedName("items")
    val bookItems: List<Book>) {
}

data class Book (
    // @PrimaryKey
    @SerializedName("id")
    val id: String?,

    @SerializedName("volumeInfo")
    val bookInfo: BookInfo?
){
    constructor():this(null,null)
}

data class BookInfo(
    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageLinks")
    val bookImage: BookImage?) {
}

data class BookImage(
    @SerializedName("smallThumbnail")
    val imageUrl: String)