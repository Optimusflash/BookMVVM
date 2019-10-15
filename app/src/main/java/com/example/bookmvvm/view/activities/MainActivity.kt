package com.example.bookmvvm.view.activities

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        with(binding) {
            val layoutManager =LinearLayoutManager(this@MainActivity)
            bookRecyclerView.layoutManager = layoutManager
            val decoration = DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL)
            bookRecyclerView.addItemDecoration(decoration)
            bookAdapter = BookAdapter()
            bookRecyclerView.adapter = bookAdapter

            viewModel = mainViewModel

            bookRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount =layoutManager.childCount
                    val totalItemCount =layoutManager.itemCount
                    val firsVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    Log.e("Main", "visibleItemCount $visibleItemCount totalItemCount $totalItemCount firsVisibleItemPosition $firsVisibleItemPosition")


                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
        }

        mainViewModel.loadBooksFromRepository()

        mainViewModel.booksList.observe(this, Observer {
            bookAdapter.setupData(it as ArrayList<Book>)
        })

    }


}
