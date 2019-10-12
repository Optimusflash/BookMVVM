package com.example.bookmvvm.data.model

import com.google.gson.annotations.SerializedName

data class BookImage(
    @SerializedName("smallThumbnail")
    val imageUrl: String)