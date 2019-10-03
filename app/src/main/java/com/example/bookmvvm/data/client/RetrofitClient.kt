package com.example.bookmvvm.data.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val BASE_URL = "https://api.myjson.com/"
        private var INSTANCE: RetrofitClient? = null
        private lateinit var bookService: BookService

        fun getRetrofitInstance(): BookService {
            if (INSTANCE == null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()

                bookService = retrofit.create(BookService::
                class.java)
            }
            return bookService
        }

    }

//    fun getAllBooks(): Single<List<Book>>{
//        return bookService.getAllBooks()
//    }

}