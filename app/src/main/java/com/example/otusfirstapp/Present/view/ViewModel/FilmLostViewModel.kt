package com.example.otusfirstapp.Present.view.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otusfirstapp.Entity.ApiClient

class FilmLostViewModel: ViewModel(){
    private val reposLiveData = MutableListData<List<Film>>()
    private val errorLiveData = MutableListData<String>()
    private val selectedFimlUrlLiveData = MutableLiveData<String>()

    private val FilmInterator = ApiClient.instance!!.FilmInteraror

    val film: LiveData<List<Film>>
        get() = filmLiveData

    var error: LiveData<String>
        get() = errorLiveData

    val select electedFimlUrlLiveData: LiveData<String>
            get() = electedFimlUrlLiveData


    }

