package com.example.otusfirstapp.Present.view.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.Entity.Film
import com.example.otusfirstapp.Entity.FilmInteractor
import com.example.otusfirstapp.Entity.FilmsResponse
import com.example.otusfirstapp.Present.view.OtusFirstApp
import retrofit2.Call
import retrofit2.Response

class FilmLostViewModel: ViewModel(){
    private val filmLiveData = MutableLiveData<List<Film>>()
    private val errorLiveData = MutableLiveData<String>()
    private val selectedFimlUrlLiveData = MutableLiveData<String>()

    private val filmInteractor = OtusFirstApp.instance!!.filmInteractor

    val film: LiveData<List<Film>>
        get() = filmLiveData

    val error: LiveData<String>
        get() = errorLiveData

    val selectedFimlUrl: LiveData<String>
        get() = selectedFimlUrlLiveData


    fun OnClick (call: Call<FilmsResponse>,
                          response: Response<FilmsResponse>
    ) {
        val res = response.body()?.results
        filmInteractor.getRepos(id, apiKey, FilmInteractor.GetFilmCallback {
            override fun onSuccesed(film: List<Film>)
            filmLiveData.postValue(response)
        }
        override fun onError(error: String) {
            errorLiveData.postValue(error)
        }
        )}
    }


    }

