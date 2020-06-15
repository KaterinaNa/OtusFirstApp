package com.example.otusfirstapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.presentation.viewmodel.FilmsViewModel

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.film_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmName = view.findViewById<TextView>(R.id.filmName)
        val filmImage = view.findViewById<ImageView>(R.id.filmImage)
        val filmDescr = view.findViewById<TextView>(R.id.filmDescr)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)


        val viewModel = ViewModelProvider(activity!!).get(FilmsViewModel::class.java)

        fun render(film: Film) {
            toolbar.title = film.name
            filmDescr.text = film.detail
            Glide
                .with(context!!)
                .load(film.poster())
                .into(filmImage)
        }

        val selectedFilmObserval = viewModel.selectedFilmSubject.subscribe({
            render(it)
        }){
            Log.e(TAG, it.toString())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated")
    }

    companion object {
        const val TAG = "DetailFragment"
        const val EXTRA_ID = "EXTRA_ID"

        fun newInstance(filmId: Int) : DetailsFragment {
            val fragment =
                DetailsFragment()
            val bundle = Bundle()

            bundle.putInt(EXTRA_ID, filmId)
            fragment.arguments = bundle
            return fragment
        }
    }
}
