package com.example.otusfirstapp.Entity

import com.example.otusfirstapp.R
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
        fakeRepos.add(Film(0, "Клуб любителей книг и пирогов с картофельными очистками"))
        fakeRepos.add(Film( 1, "Головоломка",
            R.drawable.breakinghead,
            "Райли — обычная 11-летняя школьница, и, как у каждого из нас, ее поведение определяют пять базовых эмоций: Радость, Печаль, Страх, Гнев и Брезгливость. Эмоции живут в сознании девочки и каждый день помогают ей справляться с проблемами, руководя всеми ее поступками. До поры до времени эмоции живут дружно, но вдруг оказывается, что Райли и ее родителям предстоит переезд из небольшого уютного городка в шумный и людный мегаполис. Каждая из эмоций считает, что именно она лучше прочих знает, что нужно делать в этой непростой ситуации, и в голове у девочки наступает полная неразбериха. Чтобы наладить жизнь в большом городе, освоиться в новой школе и подружиться с одноклассниками, эмоциям Райли предстоит снова научиться работать сообща.",
            true))
        fakeRepos.add(Film(2, "Малыш и Карлсон",
            R.drawable.litllebiy,
            "Всем известная история Малыша (который очень хотел собаку) и его друга, Карлсона, который живет на крыше.",
            false))
    }

    fun addToCache(repos: List<Film>) {
        this.cachedRepos.addAll(repos)
    }
}
