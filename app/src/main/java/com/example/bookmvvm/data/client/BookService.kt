package com.example.bookmvvm.data.client

import com.example.bookmvvm.data.model.BookItems
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {



    @GET("books/v1/volumes")

    fun getAllBooks(
        @Query("q") searchString: String,
        @Query("startIndex") pagStartIndex: Int,
        @Query("maxResults") pagMaxResults: Int
    ): Observable<BookItems>
}