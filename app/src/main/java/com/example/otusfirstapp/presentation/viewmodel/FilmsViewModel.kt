package com.example.otusfirstapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.OtusFirstApp
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.domain.GetTopFilmsCallback
import com.example.otusfirstapp.presentation.view.API_KEY

class FilmsViewModel : ViewModel() {
    private val filmsLiveData = MutableLiveData<ArrayList<Film>>()
    private val errorLiveData = MutableLiveData<String>()
    private val selectedFilmLiveData = MutableLiveData<Film>()
    private val favoriteFilmsLiveData = MutableLiveData<ArrayList<Film>>()

    private var currentPage = 0

    private val filmInteractor: FilmInteractor = OtusFirstApp.instance.filmInteractor

    val films: LiveData<ArrayList<Film>>
        get() = filmsLiveData

    val error: LiveData<String>
        get() = errorLiveData

    val selectedFilm: LiveData<Film>
        get() = selectedFilmLiveData

    val favoriteFilms: LiveData<ArrayList<Film>>
        get() = favoriteFilmsLiveData


    fun getTopFilms() {
        filmInteractor.getTopFilms(API_KEY, 1,
            object : GetTopFilmsCallback {
                override fun onSuccess(films: ArrayList<Film>) {
                    filmsLiveData.postValue(films)
                }

                override fun onError(error: String) {
                    errorLiveData.postValue(error)
                }
            })
    }

    fun getFavoriteFilms() {
        val films = filmInteractor.getFilms()
        val likedFilms = films.filter { it.like } as ArrayList<Film>
        favoriteFilmsLiveData.postValue(likedFilms)
    }


    fun openDetails(film: Film) {
        selectedFilmLiveData.postValue(film)
    }

    fun likeFilm(film: Film) : Boolean {
        return film.like()
    }


}

