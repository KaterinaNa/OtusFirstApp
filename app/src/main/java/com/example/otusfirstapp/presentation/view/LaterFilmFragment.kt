package com.example.otusfirstapp.presentation.view

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.otusfirstapp.App
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.presentation.viewmodel.FilmsViewModel
import java.util.*
import kotlin.collections.ArrayList

class LaterFilmFragment : Fragment() {
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
        toolbar.title = getString(R.string.later_title)

        initRecycler(view)
        initViewModel(view)

        viewModel!!.getLaterFilms()
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

        viewModel!!.laterFilms.observe(
            viewLifecycleOwner,
            Observer<ArrayList<Film>> { films ->
                adapter!!.setItems(films)
            })
    }

    private fun initSwipe(view: View) {
        val swipeLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeLayout.setOnRefreshListener {
            Log.i(LaterFilmFragment.TAG, "Swipe activated")
        }
    }

    fun initRecycler(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val likeListener = { film: Film ->
            viewModel!!.likeFilm(film)
            adapter?.notifyItemChanged(film)
        }
        val detailsListener = { film: Film ->
            Log.i(TAG, "Details clicked $film")
            viewModel!!.openDetails(film)
            listener?.openFilmDetailed()
        }

        val laterListener = { film: Film ->
            val newCalendar = Calendar.getInstance()

            if(film.showTime > 0) {
                newCalendar.timeInMillis = film.showTime
            }

            val saveShowData = DatePickerDialog.OnDateSetListener { dateView,
                                                                    year,
                                                                    monthOfYear,
                                                                    dayOfMonth ->

                val saveShowTime = TimePickerDialog.OnTimeSetListener { timeView,
                                                                        hourOfDay,
                                                                        minute ->
                    newCalendar.set(year, monthOfYear, dayOfMonth, hourOfDay, minute)
                    viewModel!!.laterFilm(film, newCalendar.time.time)

                    val intent = Intent(context, LaterIntentService::class.java)
                    intent.setExtrasClassLoader(Film::class.java.classLoader)
                    val bundle = Bundle()
                    bundle.putParcelable("film", film)
                    intent.putExtra("bundle", bundle)
                    val requestCode = 42
                    val pendingIntent = PendingIntent.getService(
                        context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT
                    )
                    val alarmManager = App.instance.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    alarmManager.set(AlarmManager.RTC, newCalendar.time.time, pendingIntent)

                    adapter?.notifyItemChanged(film)
                }
                val startTime = TimePickerDialog(
                    context,
                    saveShowTime,
                    newCalendar[Calendar.HOUR_OF_DAY],
                    newCalendar[Calendar.MINUTE],
                    true
                )
                startTime.show()

            }
            val startData = DatePickerDialog(
                context!!,
                saveShowData,
                newCalendar[Calendar.YEAR],
                newCalendar[Calendar.MONTH],
                newCalendar[Calendar.DAY_OF_MONTH]
            )
            startData.show()
            adapter?.notifyItemChanged(film)
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        adapter =
            PosterAdapter(
                LayoutInflater.from(context),
                likeListener,
                detailsListener,
                laterListener
            )
        recyclerView!!.adapter = adapter

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(context?.getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)
    }

    companion object {
        const val TAG = "LaterFragment"
    }
}

