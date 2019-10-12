package com.example.bookmvvm.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmvvm.R
import com.example.bookmvvm.data.model.Book
import com.squareup.picasso.Picasso

class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_FOOTER = 1
        private const val TYPE_ITEM = 2
    }

    private val bookList: ArrayList<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_HEADER -> {
                HeaderViewHolder(layoutInflater.inflate(R.layout.header_layout, parent, false))
            }
            TYPE_FOOTER -> {
                FooterViewHolder(layoutInflater.inflate(R.layout.footer_layout, parent, false))
            }
            else -> BookViewHolder(layoutInflater.inflate(R.layout.book_cell, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.bind(bookList[position-1])
        }

    }

    override fun getItemCount(): Int {
        return bookList.size+2
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        } else if (position == bookList.size + 1) {
            return TYPE_FOOTER
        }
        return TYPE_ITEM
    }

    fun setupData(list: ArrayList<Book>) {
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val bookImageView: ImageView = itemView.findViewById(R.id.rv_image_book)
        private val textTitle: TextView = itemView.findViewById(R.id.rv_txt_title)
        private val textDescription: TextView = itemView.findViewById(R.id.rv_txt_description)

        fun bind(book: Book) {
            Picasso.get().load(book.imageUrl).placeholder(R.drawable.ic_image_black_24dp).into(bookImageView)
            textTitle.text = book.title
            textDescription.text = book.description
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}