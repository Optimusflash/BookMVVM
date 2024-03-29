package com.example.bookmvvm.data

import com.example.bookmvvm.App
import com.example.bookmvvm.data.client.BookService
import com.example.bookmvvm.data.model.Book
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BookRepository {

    //private val bookDao = App.appDataBaseInstance().getBookDao()
    private val bookService = App.retrofitClientInstance().create(BookService::class.java)

    fun getBooksFromApi(): Observable<List<Book>> {
        val observable = bookService.getAllBooks("Warcraft", 0, 10)
        return observable.flatMap {
            Observable.just(it.bookItems)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPagBooksFromApi(page: Int): Observable<List<Book>> {
        val observable =
            bookService.getAllBooks("Warcraft", pagStartIndex = page, pagMaxResults = 10)
        return observable.flatMap {
            Observable.just(it.bookItems)
        }
//            .flatMap {
//                bookDao.insertAllBooks(it.bookItems)
//                Observable.fromArray(bookDao.getAllBooks())
//            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

