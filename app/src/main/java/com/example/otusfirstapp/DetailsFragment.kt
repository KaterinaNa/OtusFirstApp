package com.example.otusfirstapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

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

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)



        val filmId = arguments?.getInt(EXTRA_ID, 0)?:0

        val filmName = view.findViewById<TextView>(R.id.filmName)
        val filmImage = view.findViewById<ImageView>(R.id.filmImage)
        val filmDescr = view.findViewById<TextView>(R.id.filmDescr)

        toolbar.title = items[filmId].name
//        filmImage.setImageDrawable(context?.getDrawable(items[filmId].posterId))
        filmDescr.text = items[filmId].detail

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d(TAG, "onActivityCreated")
    }

    companion object {
        const val TAG = "DetailFragment"

        const val EXTRA_ID = "EXTRA_ID"

        fun newInstance(filmId: Int) : DetailsFragment {
            val fragment = DetailsFragment()

            val bundle = Bundle()
            bundle.putInt(EXTRA_ID, filmId)

            fragment.arguments = bundle

            return fragment
        }
    }



}
