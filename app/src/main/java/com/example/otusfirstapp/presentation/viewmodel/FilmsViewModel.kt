package com.example.otusfirstapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.OtusFirstApp
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.presentation.view.API_KEY

class FilmsViewModel : ViewModel() {
    private val filmsLiveData = MutableLiveData<ArrayList<Film>>()
    private val errorLiveData = MutableLiveData<String>()
    private val selectedFilmLiveData = MutableLiveData<Film>()
    private val favoriteFilmLiveData = MutableLiveData<Film>
    private var currentPage = 0

    private val filmInteractor = OtusFirstApp.instance.filmInteractor

    val films: LiveData<ArrayList<Film>>
        get() = filmsLiveData

    val error: LiveData<String>
        get() = errorLiveData

    val selectedFilm: LiveData<Film>
        get() = selectedFilmLiveData

    val favoriteFilmLiveData: LiveData<Film>
        get() favoriteFilmLiveData


    fun getTopFilms() {
        filmInteractor.getTopFilms(API_KEY, 1,
            object : FilmInteractor.GetTopFilmsCallback {
                override fun onSuccess(films: ArrayList<Film>) {
                    filmsLiveData.postValue(films)
                }

                override fun onError(error: String) {
                    errorLiveData.postValue(error)
                }
            })
    }

    fun favoriteFilmLiveData() {

    }


    fun openDetails(filmId: Int) {
        val film = filmInteractor.getFilmById(filmId)
        selectedFilmLiveData.postValue(film)
    }


}

