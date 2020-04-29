package com.example.otusfirstapp.data

import android.util.Log
import com.example.otusfirstapp.App
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.data.entity.FilmRaw

class FilmRepository {
    private val cachedFilms: ArrayList<Film>
        get() = App.instance.db.getFilmRawDao().getAll() as ArrayList<Film>
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
                "/oqhuGSl6gp22rIRo52IouPhV4hl.jpg",
                0,
                true,
                0
            )
        )
        fakeFilms.add(
            Film(
                1,
                "Головоломка",
                "Райли — обычная 11-летняя школьница, и, как у каждого из нас, ее поведение определяют пять базовых эмоций: Радость, Печаль, Страх, Гнев и Брезгливость. Эмоции живут в сознании девочки и каждый день помогают ей справляться с проблемами, руководя всеми ее поступками. До поры до времени эмоции живут дружно, но вдруг оказывается, что Райли и ее родителям предстоит переезд из небольшого уютного городка в шумный и людный мегаполис. Каждая из эмоций считает, что именно она лучше прочих знает, что нужно делать в этой непростой ситуации, и в голове у девочки наступает полная неразбериха. Чтобы наладить жизнь в большом городе, освоиться в новой школе и подружиться с одноклассниками, эмоциям Райли предстоит снова научиться работать сообща.",
                "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                1,
                true,
                0
            )
        )
        fakeFilms.add(
            Film(
                2,
                "Малыш и Карлсон",
                "Всем известная история Малыша (который очень хотел собаку) и его друга, Карлсона, который живет на крыше.",
                "/sOHqdY1RnSn6kcfAHKu28jvTebE.jpg",
                2,
                false,
                0
            )
        )
    }

    fun addToCache(films: ArrayList<FilmRaw>) {
        Log.i(TAG, "Add to cache")
        App.instance.db.getFilmRawDao().insertFilms(films)
    }

    fun clearCache() {
        Log.i(TAG, "Clear cache")
        App.instance.db.getFilmRawDao().deleteAll()
    }

    companion object {
        const val TAG = "FilmRepository"
    }
}
