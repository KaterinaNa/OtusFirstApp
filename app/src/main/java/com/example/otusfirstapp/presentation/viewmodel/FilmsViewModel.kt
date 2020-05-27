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
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import kotlin.collections.ArrayList

class FilmsViewModel : ViewModel() {
    val filmsSubject = PublishSubject.create<ArrayList<Film>>()
    val errorSubject = PublishSubject.create<Event<String>>()
    val selectedFilmSubject = BehaviorSubject.create<Film>()
    val favoriteFilmsSubject = PublishSubject.create<ArrayList<Film>>()
    val laterFilmsSubject = PublishSubject.create<ArrayList<Film>>()

    private var currentPage = 1

    init {
        Log.i(TAG, "ViewModel created $currentPage")
    }

    private val filmInteractor: FilmInteractor = App.instance.filmInteractor


    private fun getTopFilms(page: Int) {
        filmInteractor.getTopFilms(API_KEY, page,
        object : GetTopFilmsCallback {
            override fun onSuccess(films: ArrayList<Film>) {
//                filmsObservable.doOnNext(films)
                filmsSubject.onNext(films)
            }

            override fun onError(error: String) {
                errorSubject.onNext(Event("${error} "))
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
        filmsSubject.onNext(arrayListOf())
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
        favoriteFilmsSubject.onNext(likedFilms)
    }


    fun openDetails(film: Film) {
        selectedFilmSubject.onNext(film)
    }

    fun openDetailsById(filmId: Int) {
        val film = filmInteractor.getFilmById(filmId)
        selectedFilmSubject.onNext(film!!)
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

    fun getLaterFilms() {
        val films = filmInteractor.getFilms()
        val laterFilms = films.filter { it.showTime > 0} as ArrayList<Film>
        laterFilmsSubject.onNext(laterFilms)
    }

    fun updateError () {

    }

    companion object {
        const val TAG = "FilmsViewModel"
    }
}

