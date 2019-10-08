package com.example.bookmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmvvm.data.BookRepository
import com.example.bookmvvm.data.model.Book
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val bookRepository = BookRepository()
    var booksList: MutableLiveData<ArrayList<Book>> = MutableLiveData()
    var disposeBag = CompositeDisposable()
    //lateinit var callBackToView: (list: List<Book> )->Unit

    fun loadBooksFromRepository() {
        val disposable = bookRepository.getBooksFromDB()
            .subscribe({
                Log.e("From VM ", booksList.toString())
                booksList.postValue(it as ArrayList<Book>)

            }, {

            })
        disposeBag.add(disposable)
    }

    fun clearResources(){
        disposeBag.dispose()
    }


}