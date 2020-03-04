package com.example.otusfirstapp.Entity

import java.util.ArrayList

class FilmRepository {
    private val cachedRepos = ArrayList<Film>()
    private val fakeRepos = ArrayList<Film>()

    val cachedOrFakeRepos: List<Film>
        get() = if (cachedRepos.size > 0)
            cachedRepos
        else
            fakeRepos

    init {
        fakeRepos.add(Film("добавть мок"))
        fakeRepos.add(Film("из "))
        fakeRepos.add(Film("предыдущей "))
        fakeRepos.add(Film("ветки"))
    }

    fun addToCache(repos: List<Film>) {
        this.cachedRepos.addAll(repos)
    }
}
