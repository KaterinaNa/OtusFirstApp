package com.example.otusfirstapp

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView



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
//            imageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.posterId))

            if (item.like) {
                setLike.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_24px))
            } else {
                setLike.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_border_24px))
            }

        }



}