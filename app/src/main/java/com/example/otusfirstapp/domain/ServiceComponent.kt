package com.example.otusfirstapp.domain

import com.example.otusfirstapp.data.FilmService
import dagger.Component


@Component(modules = [ServiceModule::class])
interface ServiceComponent {
    fun injectsFilmService(filmService: FilmService)
}

