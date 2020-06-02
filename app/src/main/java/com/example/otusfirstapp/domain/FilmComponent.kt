package com.example.otusfirstapp.domain

import dagger.Component

@Component(modules = [FilmModule::class])
interface FilmComponent {
    fun injectsFilmInteractor(filmInteractor: FilmInteractor)
}