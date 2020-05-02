package com.example.otusfirstapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.App
import com.example.otusfirstapp.data.Db
import com.example.otusfirstapp.data.entity.Event
import com.example.otusfirstapp.data.entity.Fav
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.data.entity.Later
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.domain.GetTopFilmsCallback
import com.example.otusfirstapp.presentation.view.API_KEY
import java.util.*
import kotlin.collections.ArrayList

class FilmsViewModel : ViewModel() {
    private val filmsLiveData = MutableLiveData<ArrayList<Film>>()
    private val errorLiveData = MutableLiveData<Event<String>>()
    private val selectedFilmLiveData = MutableLiveData<Film>()
    private val favoriteFilmsLiveData = MutableLiveData<ArrayList<Film>>()

    private var currentPage = 1

    init {
        Log.i(TAG, "ViewModel created $currentPage")
    }

    private val filmInteractor: FilmInteractor = App.instance.filmInteractor

    val films: LiveData<ArrayList<Film>>
        get() = filmsLiveData

    val error: LiveData<Event<String>>
        get() = errorLiveData

    val selectedFilm: LiveData<Film>
        get() = selectedFilmLiveData

    val favoriteFilms: LiveData<ArrayList<Film>>
        get() = favoriteFilmsLiveData

    private fun getTopFilms(page: Int) {
        filmInteractor.getTopFilms(API_KEY, page,
        object : GetTopFilmsCallback {
            override fun onSuccess(films: ArrayList<Film>) {
                filmsLiveData.postValue(films)
            }

            override fun onError(error: String) {
                errorLiveData.postValue(Event("${error} "))
            }
        })
    }

    fun startGetTopFilms() {
        currentPage = 1
        Log.i(TAG, "startOverGetTopFilms $currentPage")
        return getTopFilms(currentPage)

    }

    fun resetCache() {
        filmInteractor.clearFilms()
        filmsLiveData.postValue(arrayListOf())
    }

    fun getTopFilmsNextPage() {
        Log.i(TAG, "getTopFilmsNextPage $currentPage")
        return getTopFilms(++currentPage)
    }

    fun retryTopFilms() {
        Log.i(TAG, "retryTopFilms $currentPage")
        return getTopFilms(currentPage)
    }

    fun getFavoriteFilms() {
        val films = filmInteractor.getFilms()
        val likedFilms = films.filter { it.fav } as ArrayList<Film>
        favoriteFilmsLiveData.postValue(likedFilms)
    }


    fun openDetails(film: Film) {
        selectedFilmLiveData.postValue(film)
    }

    fun openDetailsById(filmId: Int) {
        val film = filmInteractor.getFilmById(filmId)
        selectedFilmLiveData.postValue(film)
    }

    fun likeFilm(film: Film) : Boolean {
        film.fav()
        val fav = Fav(film.id, film.fav)
        Db.getInstance()?.getFavDao()?.insert(fav)
        return film.fav
    }

    fun deleteLaterFilm(film: Film){
        val later = Later(film.id, 0)
        Db.getInstance()?.getLaterDao()?.deleteLater(later)
    }


    fun laterFilm(film: Film, time: Long) : Long {
        film.showTime = time
        val later = Later(film.id, film.showTime)
        Db.getInstance()?.getLaterDao()?.insert(later)
        return film.showTime
    }

    fun updateError () {

    }

    companion object {
        const val TAG = "FilmsViewModel"
    }
}

