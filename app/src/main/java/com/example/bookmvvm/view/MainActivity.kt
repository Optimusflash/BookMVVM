package com.example.bookmvvm.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmvvm.R
import com.example.bookmvvm.data.model.Book
import com.example.bookmvvm.databinding.ActivityMainBinding
import com.example.bookmvvm.view.adapters.BookAdapter
import com.example.bookmvvm.viewmodel.MainViewModel
import com.facebook.stetho.Stetho

class MainActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Stetho.initializeWithDefaults(applicationContext)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.apply {
            bookRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL)
            bookRecyclerView.addItemDecoration(decoration)
            bookAdapter = BookAdapter()
            bookRecyclerView.adapter = bookAdapter
            viewModel = mainViewModel
        }

        mainViewModel.loadBooksFromRepository()

        mainViewModel.booksList.observe(this, Observer<List<Book>> {
            bookAdapter.setupData(it as ArrayList<Book>)
        })

    }


}
