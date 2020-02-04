package com.example.otusfirstapp

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class PosterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val button = itemView.findViewById<Button>(R.id.button)
        val setLike = itemView.findViewById<ImageView>(R.id.setLike)



        fun bind(item: Film) {
            textView.text = item.name
            button.setOnClickListener {
                Log.i("poster", "click")
            }
            imageView.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.posterId))
            if (item.like) {
                setLike.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_24px))
            } else {
                setLike.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_border_24px))
            }

        }



}