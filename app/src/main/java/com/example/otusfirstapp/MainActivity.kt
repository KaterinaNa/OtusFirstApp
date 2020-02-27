package com.example.otusfirstapp

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val REQUEST_CODE = 42
const val ANSWER_CODE = "Answer"
const val API_KEY = "836cbf0813244b3c64888bc53e1975f8"
const val URL = "https://api.themoviedb.org/3"

var items = arrayListOf<Film>(
    Film(
        "Клуб любителей книг и пирогов с картофельными очистками",
        R.drawable.club_lovers,
        "Есть нечто такое в английских сказках, что позволяет мгновенно отличить их от американских — приправленных другими принципами и расхожими приёмами по очаровыванию зрителя. А новый фильм Майка Ньюэлла — сказка, конечно. Причём такая, где с первых же кадров чувствуешь себя в безопасности. Героям придётся пройти через испытания, пережить страхи, разочарования, горечь потерь, пересилить себя, сделать правильный и нелёгкий выбор — в этом нет сомнений. И всё же, как мы хорошо знаем и предчувствуем, доброта и справедливость безоговорочно восторжествуют здесь, любовь преодолеет все преграды, и каждый отыщет своё.\n" +
                "\n" +
                "Но это будет в конце. Вначале же молодая, но преуспевающая писательница Джулиет ищет себя в новом мире — мире, где всё ещё слышны отголоски войны. Она пребывает в растерянности, не зная, о чём писать и как жить этой новой жизнью, когда ещё только вчера стоял в собственной квартире, от которой не осталось и половины. К счастью, загадочный спаситель объявится мгновенно. В лице молодого фермера, живущего на острове Гернси, пережившем все ужасы немецкой оккупации. Мистер Адамс обратится к мисс Эштон с просьбой — и эта как будто бы незначительная мелочь положит начало большим переменам. Джулиет отправится на Гернси, оставив жениха, лучшего друга и всё, что она знала прежде. А всё ради того, чтобы побывать на собрании клуба, организованного некогда как прикрытие — теперь же ставшего местом встреч для тех, кого объединили однажды нужда и тяготы войны. И в путешествии этом Джулиет узнает много нового. Узнает, что такое семья и дружба, настоящая любовь — и настоящие потери. Узнает нечто такое, что заставит её изменить свои взгляды, понять, что светская жизнь с удобствами и преходящей славой — не самый лучший способ быть счастливой.\n" +
                "\n" +
                "Из сказанного выше нетрудно понять, что история эта — самая простая и наивная, не ищущая сложных мотивов и проблем. Однако в прелести, доброте и душевности здесь недостатка нет — а большего, кажется, и не требуется. И главная составляющая этой прелести — Лили Джеймс, одна из самых очаровательных британских актрис, заметно проявивших себя в последние годы. Ей посчастливилось сыграть с Гари Олдманом и не потеряться на его фоне, побыть подружкой гонщика-меломана, украсив собой бездарный фильм, примерить на себя судьбу и платье Золушки — и даже почувствовать себя Наташей Ростовой. Трудно сказать, есть ли у этой девушки талант и способна ли она на большее — но редко встретишь такое открытое, миловидное и мгновенно располагающее к себе личико, каким мисс Джеймс встречает нас в первой же сцене фильма. В её героине сочетаются естественным образом благожелательность и изящество, простодушие и аристократизм, сияющий оптимизм юности и решительная серьёзность творца, умение быть ответственной и дружелюбной, трогательной и чуткой. В общем, набор качеств самых светлых и уютных, заставляющих улыбаться, сочувствовать и желать этой девушке добиться всего.\n" +
                "\n" +
                "Простодушно-убедителен здесь и Михиль Хаусман (трудно сказать, как правильно это звучит по-английски — а уж по-голландски вообще не выговоришь), изображающий знакомого и покорно-любящего Даарио Нахариса, обладающего здесь той же харизмой парня простоватого, безотчётно-положительного и до смерти верного. А вдобавок — несколько других замечательных британцев в лице Тома Кортни, Мэттью Гуда и Джессики Браун-Финдли. Стоит упомянуть и изумительные, уютные пейзажи, горы, море и песок — и вообще ту атмосферу не-городской идиллии, которую можно обнаружить лишь в подобном месте. Мрачные же воспоминания и тайны прошлого, преследующие жителей острова Гернси, только всё больше сближают их с сочувствующей Джулиет Эштон и нами самими. Проникающимися уверенностью, что пусть и нет в этой истории ничего такого, что можно было бы выделить и превозносить как художественный успех, она всё равно утешает и приятно обволакивает нас, заставляя растрогаться и размечтаться. О том, чтобы прийти однажды в маленький сельский домик, посидеть и послушать, как читают книжки, поговорить, выпить странного, но согревающего джина и съесть кусочек пирога. Того самого, конечно — из картофельных очисток.",
        false
    ),
    Film(
        "Головоломка",
        R.drawable.breakinghead,
        "\n" +
                "Мультфильм вызвал положительные эмоции. С учётом того, что заранее выхода не ждал, предварительно отзывы не читал и даже трейлер не смотрел, получил вполне себе благоприятные впечатления.\n" +
                "\n" +
                "В сюжете есть две линии. В первой рассказывается о жизни обычного ребёнка, о трудностях, с которыми он сталкивается при кардинальной смене обстановки. Во второй действие происходит в голове ребёнка, где действующие лица — эмоции, которые преследуют самую благую цель — сделать их владельца счастливым.\n" +
                "\n" +
                "Вообще, нечто такое, где даётся взгляд на то, как устроено сознание, какие человечки там у тебя в голове живут и делают всю работу, давно хотелось увидеть. И тут в общем то, Pixar не подвёл. Показали память, движение мыслей, абстрактное мышление, идеи, сны и много чего другого. Ах да, эта навязчивая мелодия, которая внезапно всплывает в голове и играет без остановки. У каждого такое было. Этим, в общем то, мульт и привлекает зрителя.\n" +
                "\n" +
                "Да и с точки зрения сюжета показаны простые и понятные вещи: с одной стороны — трудности социализации подрастающего индивида, с другой — путешествие домой (по сути именно этим и была занята сюжетная линия с эмоциями). А с момента, где показаны бескрайние просторы сознания и подсознания меня начала преследовать стойкая ассоциация с фильмом «Начало», как будто кто-то включил лампочку в голове.\n" +
                "\n" +
                "Однако, есть моменты, которые подпортили впечатление и вызвали вопросы. Например, набор эмоций. Неужели, с возрастом этот набор не увеличивается и не меняется. И почему именно эти эмоции? Этот набор никак не обосновывается и слабовато обыгрывается. Смутило ещё, что у всех людей в головах сидят одни и те же человечки, будь то взрослый, ребёнок или собака.\n" +
                "\n" +
                "Про мораль могу сказать только то, что она тут показана слишком явно, не оставляя зрителю пищи для размышлений. Впрочем, этим самым разжевыванием страдают многие современные кинопродукты. Тот факт, что мульт детский, ни разу не оправдывает такую подачу главной и единственной идеи. Считаю «Головоломку» не самой сильной работой Pixar, но все же там есть на что посмотреть.",
        true
    ),
    Film(
        "Малыш и Карлсон",
        R.drawable.littleboy,
        "\n" +
                "Из огромного множества книг, какие только есть на свете, самой любимой, самой лучшей, самой-самой для меня является трилогия о Малыше и Карлсоне А. Линдгрен. Почему — тема отдельного разговора, скажу лишь, что рассматриваю ее как особую субкультуру, как источник ответов на все вопросы, путеводитель по всем жизненным ситуациям и просто как бесценное сокровище мировой литературы.\n" +
                "\n" +
                "А теперь задумаемся: сколь часто нам приходилось разочаровываться в результатах чьего-либо труда по переносу на экран (в художественной или мультипликационной форме — второй вопрос) содержания дорогой Вам книги? Думается, никак не реже, чем радоваться удачному исходу подобных действий. А тут САМ «Карлсон»! И, заметьте: общего между книгой и мультфильмом, не считая, разумеется, главных действующих лиц, нескольких сюжетных ходов и пары-тройки диалогов, весьма немного. Каковы, спрашивается, шансы на успех такого мультфильма? Да ничтожны! Чтобы одновременно нравились и книга, и созданный в лучшем случае «по мотивам» мультик! А вот.\n" +
                "\n" +
                "Не скажу, что это мой самый любимый мультфильм. Да, он близок к вершине, он где-то рядом, но… Как только начинаешь задумываться на тему, как можно было бы сделать!.. С такой-то картинкой! С такими-то мастерами дубляжа! Черт побери!\n" +
                "\n" +
                "И речь даже не о том, что собирались-собирались, да так и не собрались сделать третью часть с легендарным дядей Юлиусом, хотя это, безусловно, потеря для отечественной мультипликации. А в том, что авторы фильма слишком вольно обращаются с первоисточником, прокладывая свой собственный путь, творя свою историю. Зачем — вот вопрос, на который у меня нет ответа. Да, они добились успеха — в этом не приходится сомневаться. Но какой ценой?!\n" +
                "\n" +
                "Да, цитаты из «Карлсона» для каждого ценителя делятся на те, что «из книги» («Ты сказал: «Он ушёл!», а Он не ушёл, Он только спрятался!») и те, что «из мультика» («Там еще осталось немножко варенья?»). Они все одинаково любимы, последние, к примеру, — во многом благодаря блистательной работе В. Ливанова и др. Но… Мне тяжело осознавать, что вообще приходится говорить о каком-то делении.\n" +
                "\n" +
                "Впрочем, первой части это касается меньше, нежели продолжения. Поэтому, наверное, имеет смысл произнести сакраментальную фразу: «Пустяки, дело-то житейское!» и перестать думать о том, как оно могло бы быть. У нас есть свой — советский — Карлсон, и это здорово.",
        false
    )

)

class MainActivity : AppCompatActivity(), OnNewsClickListener {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        if (API_KEY.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Please obtain your API KEY from themoviedb.org first!",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val apiService = RetroApp.getInstance().service

        val call = apiService.getTopRatedMovies(API_KEY)



        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_navigator_home -> {
                    onBackPressed()
                    true
                }
                R.id.bottom_navigator_fav -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, FavoritesFragment(), FavoritesFragment.TAG)
                        .addToBackStack(null)
                        .commit()
                    true
                }
                R.id.bottom_navigator_share -> {
                    onInvite()
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, FilmsListFragment(), FilmsListFragment.TAG)
                .commit()
        }

    }

    fun onInvite() {
        val textMessage = "Поделиться в"
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.chooser)
        val chooser = Intent.createChooser(sendIntent, title)
        val let = sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
    }

    override fun openNewsDetailed(filmId: Int) {

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailsFragment.newInstance(filmId),
                DetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        buttonId = savedInstanceState.getInt("buttonId")
        //highlightText(buttonId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("buttonId", buttonId)
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            var answer: String? = null
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    answer = it.getStringExtra(ANSWER_CODE)
                }
            }
            Log.i(TAG, "the answer is:$answer")
        }
    }

    private fun highlightText(textId: Int) {
        shadeAllText()
        val textView = findViewById<TextView>(textId)
        textView.setTextColor(resources.getColor(R.color.colorAccent))
    }

    private fun shadeAllText() {
        /*  val textView = findViewById<TextView>(R.id.textView)
        textView.setTextColor(resources.getColor(android.R.color.secondary_text_light))
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.setTextColor(resources.getColor(android.R.color.secondary_text_light))
        val textView3 = findViewById<TextView>(R.id.textView3)
        textView3.setTextColor(resources.getColor(android.R.color.secondary_text_light))*/
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            val bld: AlertDialog.Builder = AlertDialog.Builder(this)
            val no_lst =
                DialogInterface.OnClickListener { dialog,
                                                  which ->
                    dialog.dismiss()
                }
            val yes_lst =
                DialogInterface.OnClickListener { dialog,
                                                  which ->
                    super.onBackPressed()
                }
            bld.setMessage("Вы уверены, что хотите выйти?")
            bld.setTitle("Выход?")
            bld.setNegativeButton("Нет", no_lst)
            bld.setPositiveButton("Выход", yes_lst)
            val dialog: AlertDialog = bld.create()
            dialog.show()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }

}




