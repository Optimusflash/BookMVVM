package com.example.bookmvvm

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {

    companion object {

        //   private var appDataBaseInstance:  BookDataBase? = null
        private lateinit var appInstance: App
        private const val DATABASE_NAME = "BookDataBase"
        //https://www.googleapis.com/books/v1/volumes?q=kotlin
        // private const val BASE_URL = "https://api.myjson.com/"
        private const val BASE_URL = "https://www.googleapis.com/"
        private var retrofitClientInstance: Retrofit? = null

        /*  fun appDataBaseInstance(): BookDataBase {
              if (appDataBaseInstance == null){
                  appDataBaseInstance = Room.databaseBuilder(appInstance, BookDataBase::class.java, DATABASE_NAME).build()
              }
              return appDataBaseInstance!!
          }*/

        fun retrofitClientInstance(): Retrofit{
            if (retrofitClientInstance==null){
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
                retrofitClientInstance = retrofit
            }
            return retrofitClientInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this

    }




}