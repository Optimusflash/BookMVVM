package com.example.bookmvvm.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmvvm.R
import com.example.bookmvvm.data.model.Book
import com.example.bookmvvm.view.adapters.BookAdapter
import com.example.bookmvvm.viewmodel.MainViewModel
import com.facebook.stetho.Stetho

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var bookAdapter: BookAdapter
    lateinit var mainViewModel : MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Stetho.initializeWithDefaults(applicationContext)
        recyclerView = findViewById(R.id.book_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(applicationContext, LinearLayout.VERTICAL )
        recyclerView.addItemDecoration(decoration)
        bookAdapter = BookAdapter()
        recyclerView.adapter = bookAdapter

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.loadBooksFromRepository()

        mainViewModel.booksList.observe(this, Observer<ArrayList<Book>>{
            bookAdapter.setupData(it)
        })


    }



}
