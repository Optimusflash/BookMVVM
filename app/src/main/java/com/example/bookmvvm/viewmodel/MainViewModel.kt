package com.example.bookmvvm.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmvvm.data.BookRepository
import com.example.bookmvvm.data.model.Book
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val bookRepository = BookRepository()

    val isLoading: ObservableField<Boolean> = ObservableField(false)

    var booksList: MutableLiveData<List<Book>> = MutableLiveData()

    private var disposeBag = CompositeDisposable()

    fun loadBooksFromRepository() {
        isLoading.set(true)
        val disposable = bookRepository.getBooksFromDB()
            .subscribe({
                booksList.postValue(it)

            }, {

            },{
                isLoading.set(false)
            })
        disposeBag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }




}