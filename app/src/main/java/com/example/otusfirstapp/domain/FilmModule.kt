package com.example.otusfirstapp.domain

import com.example.otusfirstapp.data.FilmRepository
import dagger.Module
import dagger.Provides

@Module
class FilmModule {
    @Provides
    fun provideFilmRepository(): FilmRepository {
        return FilmRepository()
    }
}