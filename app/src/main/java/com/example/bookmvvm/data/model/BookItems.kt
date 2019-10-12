package com.example.bookmvvm.data.model

import com.google.gson.annotations.SerializedName

data class BookItems(
    @SerializedName("items")
    val bookItems: List<Book>) {
}