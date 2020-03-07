package com.example.otusfirstapp.presentation.view

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film

class PosterViewHolder(itemView: View,
                       val likeListener: (Int) -> Unit?,
                       val detailslistener: (Int)-> Unit?): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.filmName)
        val openDescr = itemView.findViewById<Button>(R.id.openDescr)
        val setLike = itemView.findViewById<ImageView>(R.id.setLike)

        fun bind(item: Film) {
            textView.text = item.name
            openDescr.setOnClickListener {
                detailslistener(getAdapterPosition())
            }
            setLike.setOnClickListener {
                likeListener(getAdapterPosition())
            }

            Glide
                .with(itemView)
                .load(item.poster())
                .into(imageView)

            setLike.setImageDrawable(ContextCompat.getDrawable(itemView.context,
                if (item.like)
                    R.drawable.ic_favorite_24px
                else
                    R.drawable.ic_favorite_border_24px
            ))
        }
}
