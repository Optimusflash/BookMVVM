package com.example.bookmvvm.data.client

import com.example.bookmvvm.data.model.BookItems
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    //https://www.googleapis.com/books/v1/volumes?q=kotlin

    @GET("books/v1/volumes")
    //fun getAllBooks(): Observable<List<Book>>
    fun getAllBooks(@Query("q")searchString: String): Observable<BookItems>
}