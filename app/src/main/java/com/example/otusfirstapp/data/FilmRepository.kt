package com.example.otusfirstapp.data

import android.util.Log
import com.example.otusfirstapp.data.entity.Film

class FilmRepository {
    private val cachedFilms = ArrayList<Film>()
    private val fakeFilms = ArrayList<Film>()

    val cachedOrFakeFilms: ArrayList<Film>
        get() = if (cachedFilms.size > 0)
            cachedFilms
        else
            fakeFilms

    init {
        fakeFilms.add(
            Film(
                0,
                "Клуб любителей книг и пирогов с картофельными очистками",
                "В послевоенном Лондоне молодая писательница Джулиет должна написать статью о книжном клубе. На помощь приходит случай — в виде письма одного свиновода с острова Гернси. Оказывается, даже свинари любят почитать, и неведомый Доуси, к которому в руки попала книга, некогда принадлежавшая Джулиет, просит ее посоветовать хорошую книжную лавку. Дело в том, что на Гернси с книгами сейчас туго, поскольку остров, в годы войны оккупированный немцами, только-только возрождается к жизни. Письмо переворачивает жизнь Джулиет. История книжного клуба, ставшего прикрытием для запрещенных встреч жителей деревни, увлекает и затягивает ее.",
                "https://api.themoviedb.org/3/123456",
                true
            )
        )
        fakeFilms.add(
            Film(
                1,
                "Головоломка",
                "Райли — обычная 11-летняя школьница, и, как у каждого из нас, ее поведение определяют пять базовых эмоций: Радость, Печаль, Страх, Гнев и Брезгливость. Эмоции живут в сознании девочки и каждый день помогают ей справляться с проблемами, руководя всеми ее поступками. До поры до времени эмоции живут дружно, но вдруг оказывается, что Райли и ее родителям предстоит переезд из небольшого уютного городка в шумный и людный мегаполис. Каждая из эмоций считает, что именно она лучше прочих знает, что нужно делать в этой непростой ситуации, и в голове у девочки наступает полная неразбериха. Чтобы наладить жизнь в большом городе, освоиться в новой школе и подружиться с одноклассниками, эмоциям Райли предстоит снова научиться работать сообща.",
                "https://api.themoviedb.org/3/123456",
                true
            )
        )
        fakeFilms.add(
            Film(
                2,
                "Малыш и Карлсон",
                "Всем известная история Малыша (который очень хотел собаку) и его друга, Карлсона, который живет на крыше.",
                "https://api.themoviedb.org/3/123456",
                false
            )
        )
    }

    fun addToCache(films: ArrayList<Film>) {
        Log.i("Repository", "Add to cache")
        cachedFilms.addAll(films)
    }

    fun getFavoriteFilms(): ArrayList<Film> {
        val realIndex: ArrayList<Int> = arrayListOf()
        val likedFilms = cachedOrFakeFilms.filterIndexed { idx: Int, it: Film ->
            if(it.like) realIndex.add(idx)
            it.like
        }
        return ArrayList(likedFilms)
    }

    fun getFilmById(id: Int): Film {
        return cachedOrFakeFilms[id]
    }
}
