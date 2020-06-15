package com.example.otusfirstapp.domain

import com.example.otusfirstapp.data.FilmRepository
import dagger.Component

@Component(modules = [FilmModule::class])
interface FilmComponent {
    fun injectsFilmRepository (filmRepository: FilmRepository)
}