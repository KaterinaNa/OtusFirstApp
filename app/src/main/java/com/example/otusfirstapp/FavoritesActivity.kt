package com.example.otusfirstapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FavoritesActivity : AppCompatActivity() {

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
        val detailsListener = { id: Int ->
            val openDetailsIntent = Intent(this, DetailActivity::class.java)
            openDetailsIntent.putExtra("FilmId", id)
            startActivityForResult(openDetailsIntent, REQUEST_CODE)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        val inflater = LayoutInflater.from(this)
        recyclerView.adapter = PosterAdapter(inflater, likedFilms, {}, detailsListener)

        val itemDecor = PosterItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)

        findViewById<View>(R.id.addPoster).setOnClickListener {
            likedFilms.add(Film("Ла ла ленд", R.drawable.lalaland, "Это история любви старлетки, которая между прослушиваниями подает кофе состоявшимся кинозвездам, и фанатичного джазового музыканта, вынужденного подрабатывать в заштатных барах. Но пришедший к влюбленным успех начинает подтачивать их отношения.", true))
            recyclerView.adapter?.notifyItemInserted(likedFilms.size)
        }

        findViewById<View>(R.id.delPoster).setOnClickListener() {
            if(likedFilms.size > 0) {
                val position = likedFilms.size - 1
                likedFilms.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }
        }



    }
}


