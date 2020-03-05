package com.example.otusfirstapp.Present.view.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.Entity.Film
import com.example.otusfirstapp.Present.view.OtusFirstApp

class FilmLostViewModel: ViewModel(){
    private val filmLiveData = MutableListData<List<Film>>()
    private val errorLiveData = MutableListData<String>()
    private val selectedFimlUrlLiveData = MutableLiveData<String>()

    private val filmInteractor = OtusFirstApp.instance!!.filmInteractor

    val film: LiveData<List<Film>>
        get() = filmLiveData

    var error: LiveData<String>
        get() = errorLiveData

    val select selectedFimlUrlLiveData: LiveData<String>
            get() = electedFimlUrlLiveData


    }

