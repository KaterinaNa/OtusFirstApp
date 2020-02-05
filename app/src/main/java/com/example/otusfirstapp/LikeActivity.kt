package com.example.otusfirstapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class LikeActivity : AppCompatActivity() {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerLike()
    }

    fun initRecyclerLike() {
        val likedFilms = items.filter { it.like }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val inflater = LayoutInflater.from(this)
        recyclerView.adapter = PosterAdapter(inflater, likedFilms)
    }

/*findViewById<View>(R.id.addPoster).setOnClickListener {
    items.add(2, poster("New item", "----", Color.MAGENTA))
    recyclerView.adapter?.notifyItemInserted(2)
}
findViewById<View>(R.id.delPoster).setOnClickListener() {
    items.removeAt(2)
    recyclerView.adapter?.notifyItemRemoved(2)
}

recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == items.size - 1) {
            items.add(Newitem("Pagination new item", "+++++", Color.BLACK))
            items.add(Newitem("Pagination new item", "+++++", Color.BLACK))
            items.add(Newitem("Pagination new item", "+++++", Color.BLACK))
            items.add(Newitem("Pagination new item", "+++++", Color.BLACK))
            items.add(Newitem("Pagination new item", "+++++", Color.BLACK))

            recyclerView.adapter?.notifyItemRangeInserted(items.size - 5, 5)
        }
    }
})*/
}
