package com.example.bookmvvm.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmvvm.R
import com.example.bookmvvm.data.model.Book
import com.example.bookmvvm.view.viewholders.BookViewHolder
import com.example.bookmvvm.view.viewholders.FooterViewHolder

class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_FOOTER = 1
    }

    private var isLoadingAdded = false

    private var bookList: ArrayList<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_FOOTER -> {
                FooterViewHolder(layoutInflater.inflate(R.layout.footer_layout, parent, false))
            }
            else -> BookViewHolder(layoutInflater.inflate(R.layout.book_cell, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.bind(bookList[position])
        }

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == bookList.size - 1 && isLoadingAdded) {
            TYPE_FOOTER
        } else
            TYPE_ITEM
    }

    // Helpers
    fun addAll(list: List<Book>) {
        list.forEach {
            addItem(it)
        }
    }

    private fun addItem(book: Book) {
        bookList.add(book)
        val position = bookList.size - 1
        notifyItemInserted(position)
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        addItem(Book())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false
        if (!bookList.isNullOrEmpty()) {
            val position = bookList.size - 1
            bookList.remove(bookList[position])
            notifyItemRemoved(position)
        }
    }


}