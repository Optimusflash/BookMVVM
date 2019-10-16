package com.example.bookmvvm.viewmodel

import android.util.Log
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
        val disposable = bookRepository.getBooksFromApi()
            .subscribe({
                booksList.postValue(it)

            }, {
                Log.e("ViewModel","Что-то пошло не так... ${it.localizedMessage}" )
            },{
                isLoading.set(false)
            })
        disposeBag.add(disposable)
    }


    fun loadPageBooksFromRepository(page: Int){
        val disposable = bookRepository.getPagBooksFromApi(page).subscribe ({
            booksList.postValue(it)
        },{
            Log.e("ViewModel","Что-то пошло не так... ${it.localizedMessage}" )
        },{

        })
        disposeBag.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }




}