package com.example.otusfirstapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.R
import com.example.otusfirstapp.presentation.viewmodel.FilmsViewModel

class FavoritesFragment : Fragment() {
    private var listener: OnFilmClickListener? = null
    private var viewModel: FilmsViewModel? = null
    private var recycler: RecyclerView? = null
    private var adapter: PosterAdapter? = null

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
        initViewModel(view)

        viewModel!!.getTopFilms()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity is OnFilmClickListener) {
            listener = activity as OnFilmClickListener
        } else {
            throw Exception("Activity must implement OnNewsClickListener")
        }
        Log.d(TAG, "onActivityCreated")
    }

    private fun initViewModel(view: View) {
        viewModel = ViewModelProvider(activity!!).get(FilmsViewModel::class.java)

        viewModel!!.films.observe(
            viewLifecycleOwner,
            Observer<ArrayList<Film>> { films ->
                adapter!!.setItems(films)
                Log.i(FilmsListFragment.TAG, "films update")
            })
    }

    private fun initSwipe(view: View) {
        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeLayout.setOnRefreshListener {
            Log.i(FilmsListFragment.TAG, "Swipe activated")
        }
    }

    fun initRecycler(view: View) {
        var realIndex: ArrayList<Int> = arrayListOf()
        var likedFilms = ArrayList<Film>(film.filterIndexed { idx: Int, it: Film ->
            if(it.like) realIndex.add(idx)
            it.like
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val likeListener = { favId: Int ->
/*            val id = realIndex[favId]
            Log.i(TAG, "Like clicked $id")
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(favId)*/
        }
        val detailsListener = { favId: Int ->
            val id = realIndex[favId]
            Log.i(TAG, "Details clicked $id")
            listener?.openFilmDetailed()
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =
            PosterAdapter(
                LayoutInflater.from(context),
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
