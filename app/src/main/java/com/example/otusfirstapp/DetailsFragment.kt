package com.example.otusfirstapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
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

        var filmId = intent.getIntExtra("FilmId", 0)

        val filmName = view.findViewById<TextView>(R.id.filmName)
        val filmImage = view.findViewById<ImageView>(R.id.filmImage)
        val filmDescr = view.findViewById<TextView>(R.id.filmDescr)

        filmName.text = items[filmId].name
        filmImage.setImageDrawable(context.getDrawable(items[filmId].posterId))
        filmDescr.text = items[filmId].detail

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated")
    }

    companion object {
        const val TAG = "DetailsFragment"
    }




}