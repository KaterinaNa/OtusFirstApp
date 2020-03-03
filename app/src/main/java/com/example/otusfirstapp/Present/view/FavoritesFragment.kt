package com.example.otusfirstapp.Present.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.otusfirstapp.Entity.Film
import com.example.otusfirstapp.OnNewsClickListener
import com.example.otusfirstapp.PosterAdapter
import com.example.otusfirstapp.R

class FavoritesFragment : Fragment() {

    var listener: OnNewsClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.films_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = getString(R.string.favorites_title)

        initRecycler(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is OnNewsClickListener) {
            listener = activity as OnNewsClickListener
        } else {
            throw Exception("Activity must implement OnNewsClickListener")
        }
        Log.d(TAG, "onActivityCreated")
    }

    fun initRecycler(view: View) {
        var realIndex: ArrayList<Int> = arrayListOf()
        var likedFilms = ArrayList<Film>(items.filterIndexed { idx: Int, it: Film ->
            if(it.like) realIndex.add(idx)
            it.like
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val likeListener = { favId: Int ->
            val id = realIndex[favId]
            Log.i(TAG, "Like clicked $id")
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(favId)
        }
        val detailsListener = { favId: Int ->
            val id = realIndex[favId]
            Log.i(TAG, "Details clicked $id")
            listener?.openNewsDetailed(id)
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PosterAdapter(
            LayoutInflater.from(context),
            likedFilms,
            likeListener,
            detailsListener
        )

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)
    }

    companion object {
        const val TAG = "FavoritesFragment"
    }
}
