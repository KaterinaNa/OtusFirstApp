package com.example.otusfirstapp.presentation.view;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film

class PosterAdapter(
    val inflater: LayoutInflater,
    val likeListener: (Film) -> Unit?,
    val detalislistener: (Film) -> Unit?,
    val laterlistener: (Film) -> Unit?
) :
    RecyclerView.Adapter<PosterViewHolder>() {
    private var items = ArrayList<Film>()

    fun setItems(films: ArrayList<Film>) {
        items.clear()
        items.addAll(films)
        notifyDataSetChanged()
    }

    fun addItems(films: ArrayList<Film>) {
        val itemCountBeforeAdd = itemCount
        items.addAll(films)
        notifyItemRangeInserted(itemCountBeforeAdd, films.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        return PosterViewHolder(
            inflater.inflate(
                R.layout.poster,
                parent,
                false
            ), likeListener, detalislistener, laterlistener
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun notifyItemChanged(film : Film) {
        val index = items.indexOf(film)
        notifyItemChanged(index)
    }
}
