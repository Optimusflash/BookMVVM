package com.example.bookmvvm.data

import com.example.bookmvvm.App
import com.example.bookmvvm.data.client.BookService
import com.example.bookmvvm.data.model.Book
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BookRepository {

    private val bookDao = App.appDataBaseInstance().getBookDao()
    private val retrofitClient = App.retrofitClientInstance().create(BookService::class.java)

    fun getBooksFromDB(): Single<List<Book>> {
        val observable = retrofitClient.getAllBooks()
        return observable
            .flatMap {
                bookDao.insertAllBooks(it)
                Single.just(bookDao.getAllBooks())
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}