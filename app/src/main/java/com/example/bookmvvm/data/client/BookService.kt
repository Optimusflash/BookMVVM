package com.example.bookmvvm.data.client

import com.example.bookmvvm.data.model.Book
import io.reactivex.Single
import retrofit2.http.GET

interface BookService {

    @GET("bins/cckd0")
    fun getAllBooks(): Single<List<Book>>
}