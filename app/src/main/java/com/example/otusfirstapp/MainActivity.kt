package com.example.otusfirstapp

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val REQUEST_CODE = 42
const val ANSWER_CODE = "Answer"

var items = arrayListOf<Film>(
    Film("Клуб любителей книг и пирогов с картофельными очистками", R.drawable.club_lovers, "В послевоенном Лондоне молодая писательница Джулиет должна написать статью о книжном клубе. На помощь приходит случай — в виде письма одного свиновода с острова Гернси. Оказывается, даже свинари любят почитать, и неведомый Доуси, к которому в руки попала книга, некогда принадлежавшая Джулиет, просит ее посоветовать хорошую книжную лавку. Дело в том, что на Гернси с книгами сейчас туго, поскольку остров, в годы войны оккупированный немцами, только-только возрождается к жизни. Письмо переворачивает жизнь Джулиет. История книжного клуба, ставшего прикрытием для запрещенных встреч жителей деревни, увлекает и затягивает ее.", false),
    Film("Головоломка", R.drawable.breakinghead, "Райли — обычная 11-летняя школьница, и, как у каждого из нас, ее поведение определяют пять базовых эмоций: Радость, Печаль, Страх, Гнев и Брезгливость. Эмоции живут в сознании девочки и каждый день помогают ей справляться с проблемами, руководя всеми ее поступками. До поры до времени эмоции живут дружно, но вдруг оказывается, что Райли и ее родителям предстоит переезд из небольшого уютного городка в шумный и людный мегаполис. Каждая из эмоций считает, что именно она лучше прочих знает, что нужно делать в этой непростой ситуации, и в голове у девочки наступает полная неразбериха. Чтобы наладить жизнь в большом городе, освоиться в новой школе и подружиться с одноклассниками, эмоциям Райли предстоит снова научиться работать сообща.", true),
    Film("Малыш и Карлсон", R.drawable.littleboy, "Всем известная история Малыша (который очень хотел собаку) и его друга, Карлсона, который живет на крыше.", false)

)

class MainActivity : AppCompatActivity() {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        findViewById<Button>(R.id.Favorites).setOnClickListener{
            onFavorites(it)
        }
    }

    fun initRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val likeListener = { id: Int ->
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(id)
        }
        val detailsListener = { id: Int ->
            val openDetailsIntent = Intent(this, DetailActivity::class.java)
            openDetailsIntent.putExtra("FilmId", id)
            startActivityForResult(openDetailsIntent, REQUEST_CODE)
        }

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PosterAdapter(LayoutInflater.from(this), items, likeListener, detailsListener)

        val itemDecor = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        itemDecor.setDrawable(getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)

        val itemDecor2 = DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        itemDecor2.setDrawable(getDrawable(R.drawable.myline2)!!)
        recyclerView.addItemDecoration(itemDecor2)


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

    fun onFavorites(view: View?) {
        val ClickButtonIntent = Intent(this, FavoritesActivity::class.java)
        startActivity(ClickButtonIntent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            var answer: String? = null
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    answer = it.getStringExtra(ANSWER_CODE)
            }
            }
            Log.i("main_activity", "the answer is:$answer")
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


