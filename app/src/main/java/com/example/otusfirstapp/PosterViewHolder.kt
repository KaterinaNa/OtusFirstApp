package com.example.otusfirstapp

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PosterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<TextView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val button = itemView.findViewById<ImageView>(R.id.button)

        fun bind(item: Film) {
            textView.text = item.name
            button.setOnClickListener {
                Log.i("poster", "click")
            }
            imageView.setBackgroundColor(item.posterId)

        }



}