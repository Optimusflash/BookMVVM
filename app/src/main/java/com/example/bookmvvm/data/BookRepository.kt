package com.example.bookmvvm.data

import com.example.bookmvvm.App
import com.example.bookmvvm.data.client.BookService
import com.example.bookmvvm.data.model.Book
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BookRepository {

    private val bookDao = App.appDataBaseInstance().getBookDao()
    private val retrofitClient = App.retrofitClientInstance().create(BookService::class.java)

    fun getBooksFromDB(): Observable<List<Book>> {
        val observable = retrofitClient.getAllBooks()
        return observable
            .flatMap {
                bookDao.insertAllBooks(it)
                Observable.just(bookDao.getAllBooks())
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}