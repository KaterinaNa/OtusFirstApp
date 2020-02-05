package com.example.otusfirstapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class LikeActivity : AppCompatActivity() {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addPosterButton = findViewById<Button>(R.id.addPoster)
        addPosterButton.setVisibility(View.VISIBLE)
        val delPosterButton = findViewById<Button>(R.id.delPoster)
        delPosterButton.setVisibility(View.VISIBLE)
        initRecyclerLike()
    }

    fun initRecyclerLike() {
        var likedFilms = ArrayList<Film>(items.filter { it.like })
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val inflater = LayoutInflater.from(this)
        recyclerView.adapter = PosterAdapter(inflater, likedFilms)


        findViewById<View>(R.id.addPoster).setOnClickListener {
            likedFilms.add(Film("Фильм 4", R.drawable.lalaland, "Детали", true))
            recyclerView.adapter?.notifyItemInserted(likedFilms.size)
        }

        findViewById<View>(R.id.delPoster).setOnClickListener() {
            likedFilms.removeAt(2)
            recyclerView.adapter?.notifyItemRemoved(2)
        }


/*        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == items.size - 1) {
                    items.add(Film("Фильм 5", R.drawable.genius, "Детали", true))
                    items.add(Film("Фильм 6", R.drawable.lalaland, "Детали", true))
                    items.add(Film("Фильм 7", R.drawable.genius, "Детали", true))
                    recyclerView.adapter?.notifyItemRangeInserted(items.size - 5, 5)
                }

            }

        })*/
    }
}


