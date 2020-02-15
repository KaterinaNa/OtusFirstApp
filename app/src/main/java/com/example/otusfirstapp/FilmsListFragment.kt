package com.example.otusfirstapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FilmsListFragment : Fragment() {

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

        initRecycler(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated")
    }


    fun initRecycler(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val likeListener = { id: Int ->
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(id)
        }
        val detailsListener = { id: Int ->
            Log.i(TAG, "Details clicked $id")
            Unit
            /*val openDetailsIntent = Intent(this, DetailActivity::class.java)
            openDetailsIntent.putExtra("FilmId", id)
            startActivityForResult(openDetailsIntent, REQUEST_CODE)*/
        }

        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PosterAdapter(LayoutInflater.from(context), items, likeListener, detailsListener)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)

        val itemDecor2 = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        itemDecor2.setDrawable(context?.getDrawable(R.drawable.myline2)!!)
        recyclerView.addItemDecoration(itemDecor2)


    }

    companion object {
        const val TAG = "FilmsListFragment"
    }

}
