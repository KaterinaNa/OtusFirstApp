package com.example.otusfirstapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.domain.FilmInteractor
import com.example.otusfirstapp.data.entity.FilmsResponse
import com.example.otusfirstapp.presentation.view.API_KEY
import com.example.otusfirstapp.OtusFirstApp
import retrofit2.Call
import retrofit2.Response

class FilmViewModel: ViewModel(){
    private val filmLiveData = MutableLiveData<Film>()
    private val errorLiveData = MutableLiveData<String>()
    private val selectedFimlUrlLiveData = MutableLiveData<String>()

    private val filmInteractor = OtusFirstApp.instance!!.filmInteractor

    val film: LiveData<Film>
        get() = filmLiveData

    val error: LiveData<String>
        get() = errorLiveData

    val selectedFimlUrl: LiveData<String>
        get() = selectedFimlUrlLiveData


    fun OnClick (call: Call<FilmsResponse>,
                 response: Response<FilmsResponse>
    ) {
        val res = response.body()?.results
        filmInteractor.getTopFilms(film.id,
            API_KEY, object: FilmInteractor.GetFilmCallback {
            override fun onSuccess(film: Film) {
                filmLiveData.postValue(film)
            }

            override fun onError(error: String) {
                errorLiveData.postValue(error)
            }
        })
    }


    }

