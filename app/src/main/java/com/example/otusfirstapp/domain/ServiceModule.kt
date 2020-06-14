package com.example.otusfirstapp.domain

import com.example.otusfirstapp.data.FilmService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideFilmService(): FilmService {
        return FilmService()
    }
}


