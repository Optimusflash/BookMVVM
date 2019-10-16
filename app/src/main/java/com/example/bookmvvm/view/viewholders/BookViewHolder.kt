package com.example.bookmvvm.view.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmvvm.R
import com.example.bookmvvm.data.model.Book
import com.squareup.picasso.Picasso

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val bookImageView: ImageView = itemView.findViewById(R.id.rv_image_book)
    private val textTitle: TextView = itemView.findViewById(R.id.rv_txt_title)
    private val textDescription: TextView = itemView.findViewById(R.id.rv_txt_description)

    fun bind(book: Book) {
        Picasso.get()
            .load(book.bookInfo?.bookImage?.imageUrl)
            .placeholder(R.drawable.ic_image_black_24dp)
            .into(bookImageView)
        textTitle.text = book.bookInfo?.title
        textDescription.text = book.bookInfo?.description
    }
}