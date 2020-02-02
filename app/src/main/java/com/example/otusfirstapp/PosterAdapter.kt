package com.example.otusfirstapp;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PosterAdapter(val inflater: LayoutInflater, val items: List<Film>) :
    RecyclerView.Adapter<PosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(inflater.inflate(R.layout.poster, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bind(items[position - 1])
    }

}
