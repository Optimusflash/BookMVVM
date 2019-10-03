package com.example.bookmvvm.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookmvvm.data.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(bookList: List<Book>)

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>
}