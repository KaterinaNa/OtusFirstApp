package com.example.otusfirstapp

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

class FavoritesFragment : Fragment() {

    var listener: OnNewsClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
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
        toolbar.title = "Избранное"

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
        var likedFilms = ArrayList<Film>(items.filter { it.like })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val likeListener = { id: Int ->
            Log.i(TAG, "Like clicked $id")
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(id)
        }
        val detailsListener = { id: Int ->
            Log.i(TAG, "Details clicked $id")
            listener?.openNewsDetailed(id)
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PosterAdapter(LayoutInflater.from(context), likedFilms, likeListener, detailsListener)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)
    }

    companion object {
        const val TAG = "FavoritesFragment"
    }
}
