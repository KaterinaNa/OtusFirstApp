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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.otusfirstapp.*
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.presentation.viewmodel.FilmsViewModel

class FilmsListFragment : Fragment() {
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
        toolbar.title = getString(R.string.filmslist_title)

        initRecycler(view)
        initSwipe(view)
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
                Log.i(TAG, "films update")
            })
        viewModel!!.error.observe(
            viewLifecycleOwner,
            Observer<String> { error -> Toast.makeText(context, error, Toast.LENGTH_SHORT).show() })
    }

    private fun initSwipe(view: View) {
        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeLayout.setOnRefreshListener {
            Log.i(TAG, "Swipe activated")
        }
    }

    private fun initRecycler(view: View) {
        recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val likeListener = { film: Film ->
            viewModel!!.likeFilm(film)
            adapter?.notifyItemChanged(film)
            /*if(item.like) {
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
/*                val lastItemIndex = items.size - 1
                if(gridLayoutManager.findLastVisibleItemPosition() == lastItemIndex) {
                    Log.i(TAG, "Bottom of recycler")
                    val apiService = OtusFirstApp.instance?.service
                    apiService?.getTopRatedMovies(API_KEY, ++currentPage)?.enqueue(object :
                        Callback<FilmsResponse> {
                        override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                            Log.e(TAG, t.toString())
                        }
                        override fun onResponse(
                            call: Call<FilmsResponse>,
                            response: Response<FilmsResponse>
                        ) {
                            val res = response.body()?.results
                            if (res == null) return

                            items.addAll(res)
                            recyclerView.adapter?.notifyItemRangeInserted(lastItemIndex,res.size)
                        }
                    })
                }*/
            }
        })
    }

    companion object {
        const val TAG = "FilmsListFragment"
    }
}
