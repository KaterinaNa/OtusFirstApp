package com.example.otusfirstapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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

        val addPosterButton = view.findViewById<Button>(R.id.addPoster)
        addPosterButton.setVisibility(View.VISIBLE)
        val delPosterButton = view.findViewById<Button>(R.id.delPoster)
        delPosterButton.setVisibility(View.VISIBLE)

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
            Unit
//            items[id].like = !items[id].like
//            recyclerView.adapter?.notifyItemChanged(id)
        }
        val detailsListener = { id: Int ->
            Log.i(TAG, "Details clicked $id")
            listener?.openNewsDetailed(id)
            Unit
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PosterAdapter(LayoutInflater.from(context), likedFilms, likeListener, detailsListener)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)

        view.findViewById<View>(R.id.addPoster).setOnClickListener {
            likedFilms.add(
                Film(
                    "Ла ла ленд",
                    R.drawable.lalaland,
                    "Это история любви старлетки, которая между прослушиваниями подает кофе состоявшимся кинозвездам, и фанатичного джазового музыканта, вынужденного подрабатывать в заштатных барах. Но пришедший к влюбленным успех начинает подтачивать их отношения.",
                    true
                )
            )
            recyclerView.adapter?.notifyItemInserted(likedFilms.size)
        }

        view.findViewById<View>(R.id.delPoster).setOnClickListener() {
            if (likedFilms.size > 0) {
                val position = likedFilms.size - 1
                likedFilms.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }
        }

    }

    companion object {
        const val TAG = "FavoritesFragment"
    }

}
