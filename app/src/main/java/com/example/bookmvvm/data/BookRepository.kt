package com.example.bookmvvm.data

import com.example.bookmvvm.App
import com.example.bookmvvm.data.client.RetrofitClient
import com.example.bookmvvm.data.model.Book
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BookRepository {

    private val bookDao = App.appInstance.getDataBaseInstance().getBookDao()
    private val retrofitClient = RetrofitClient.getRetrofitInstance()

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