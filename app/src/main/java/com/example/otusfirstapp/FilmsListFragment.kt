package com.example.otusfirstapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsListFragment : Fragment() {
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
        toolbar.title = "Список фильмов"

        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

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
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val likeListener = { id: Int ->
            val liked = items[id].like
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(id)
            if(liked==false) {
                Toast.makeText(view.context, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(view.context, "Удалено из избранного", Toast.LENGTH_SHORT).show()
            }

        }
        val detailsListener = { id: Int ->
            Log.i(TAG, "Details clicked $id")
            listener?.openNewsDetailed(id)
            Unit
        }

        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter =
            PosterAdapter(LayoutInflater.from(context), items, likeListener, detailsListener)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)

        val itemDecor2 = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        itemDecor2.setDrawable(context?.getDrawable(R.drawable.myline2)!!)
        recyclerView.addItemDecoration(itemDecor2)

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastItemIndex = items.size - 1
                if(gridLayoutManager.findLastVisibleItemPosition() == lastItemIndex) {
                    Log.i(TAG, "Niz scrolla")
                    val apiService = OtusFirstApp.instance?.service
                    apiService?.getTopRatedMovies(API_KEY, 2)?.enqueue(object :
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
                }

            }
        })

    }

    companion object {
        const val TAG = "FilmsListFragment"
    }



}
