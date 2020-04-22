package com.example.otusfirstapp.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.otusfirstapp.*
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.presentation.viewmodel.FilmsViewModel
import com.google.android.material.snackbar.Snackbar

class FilmsListFragment : Fragment() {
    private var listener: OnFilmClickListener? = null
    private var viewModel: FilmsViewModel? = null
    private var recycler: RecyclerView? = null
    private var adapter: PosterAdapter? = null
    private var swipeLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycle.addObserver(OtusFirstApp.instance.filmsUpdater)
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
        toolbar.title = getString(R.string.filmslist_title)

        initRecycler(view)
        initSwipe(view)
        initViewModel(view)

        if(adapter!!.itemCount == 0) {
            viewModel!!.startGetTopFilms()
            Log.i(TAG, "page  = 1")
        }
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

    fun snackBarError(error: String) {
        val snackbar = Snackbar.make(view!!, "Network error: $error", Snackbar.LENGTH_LONG)
        snackbar.setAction("Retry") {
            Log.i(TAG, "snackbar retry tapped")
            viewModel!!.retryTopFilms()
        }
        snackbar.show()

    }

    private fun initViewModel(view: View) {
        viewModel = ViewModelProvider(activity!!).get(FilmsViewModel::class.java)

        viewModel!!.films.observe(
            viewLifecycleOwner,
            Observer<ArrayList<Film>> { films ->
                adapter!!.setItems(films)
                Log.i(TAG, "films update ${films.size}")
                if (films.size > 0) swipeLayout?.isRefreshing = false
            })
        viewModel!!.error.observe(
            viewLifecycleOwner,
            Observer {
                it.getContentIfNotHandled().let {
                    if (it != null) snackBarError(it)
                }
            }
        )
    }

    private fun initSwipe(view: View) {
        swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeLayout?.setOnRefreshListener {
            Log.i(TAG, "Swipe activated")
            viewModel!!.resetCache()
            viewModel!!.startGetTopFilms()
        }
    }

    private fun initRecycler(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val likeListener = { film: Film ->
            viewModel!!.likeFilm(film)
            adapter?.notifyItemChanged(film)
           /* if(item.like) {
                Toast.makeText(view.context, getString(R.string.added_to_favorites), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, getString(R.string.deleted_from_favorites), Toast.LENGTH_SHORT).show()
            }*/
        }
        val detailsListener = { film: Film ->
            Log.i(TAG, "Details clicked $film")
            viewModel!!.openDetails(film)
            listener?.openFilmDetailed()
        }

        val layoutManager = GridLayoutManager(context, 2)
        recycler!!.layoutManager = layoutManager

        adapter =
            PosterAdapter(
                LayoutInflater.from(context),
                likeListener, detailsListener
            )
        recycler!!.adapter = adapter

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recycler!!.addItemDecoration(itemDecor)

        val itemDecor2 = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        itemDecor2.setDrawable(context?.getDrawable(R.drawable.myline2)!!)
        recycler!!.addItemDecoration(itemDecor2)

        recycler!!.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            val gridLayoutManager = recycler!!.layoutManager as GridLayoutManager

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastItemIndex = adapter!!.itemCount - 1

                if(gridLayoutManager.findLastVisibleItemPosition() == lastItemIndex) {
                    Log.i(TAG, "Bottom of recycler")
                    viewModel!!.getTopFilmsNextPage()
                }
            }
        })
    }

    companion object {
        const val TAG = "FilmsListFragment"
    }
}
