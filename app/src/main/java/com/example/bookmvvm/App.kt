package com.example.bookmvvm

import android.app.Application
import androidx.room.Room
import com.example.bookmvvm.data.database.BookDataBase

class App: Application() {

    lateinit var appDataBaseInstance:  BookDataBase

    companion object {
        lateinit var appInstance: App
        private const val DATABASE_NAME = "BookDataBase"
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        initAppDataBase()
    }

    private fun initAppDataBase() {
        appDataBaseInstance = Room.databaseBuilder(this, BookDataBase::class.java, DATABASE_NAME).build()
    }


    fun getDataBaseInstance(): BookDataBase{
        return appDataBaseInstance
    }
}