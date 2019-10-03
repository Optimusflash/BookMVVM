package com.example.bookmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookmvvm.data.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class BookDataBase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    /*companion object {
        private var INSTANCE: BookDataBase? = null
        private const val DATABASE_NAME = "BookDataBase"
        fun getInstance(context: Context): BookDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, BookDataBase::class.java, DATABASE_NAME)
                    .build()
            }
            return INSTANCE!!
        }
    }*/
}