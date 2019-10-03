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

class BookAdapter: RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val bookList: ArrayList<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.book_cell, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }
    override fun getItemCount(): Int {
      return bookList.size
    }

    fun setupData(list: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val bookImageView: ImageView = itemView.findViewById(R.id.rv_image_book)
        private val textTitle: TextView = itemView.findViewById(R.id.rv_txt_title)
        private val textDescription: TextView = itemView.findViewById(R.id.rv_txt_description)

        fun bind(book: Book){
            Picasso.get().load(book.imageUrl).into(bookImageView)
            textTitle.text = book.title
            textDescription.text = book.description
        }
    }
}