package com.example.otusfirstapp

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.poster.*

const val REQUEST_CODE = 42
const val ANSWER_CODE = "Answer"

var items = arrayListOf<Film>(
    Film("Фильм 1", R.drawable.breakinghead, "Детали", false),
    Film("Фильм 2", R.drawable.club_lovers, "Детали", true),
    Film("Фильм 3", R.drawable.littleboy, "Детали", false)

)

class MainActivity : AppCompatActivity() {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        findViewById<Button>(R.id.Likes).setOnClickListener{
            onFavorites(it)
        }
    }

    fun initRecycler() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val likeListener = { id: Int ->
            items[id].like = !items[id].like
            recyclerView.adapter?.notifyItemChanged(id)
        }

        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PosterAdapter(LayoutInflater.from(this), items, likeListener)

        val itemDecor = PosterItemDecoreation(this, DividerItemDecoration.HORIZONTAL)
        itemDecor.setDrawable(getDrawable(R.drawable.myline)!!)
        recyclerView.addItemDecoration(itemDecor)


    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        buttonId = savedInstanceState.getInt("buttonId")
        highlightText(buttonId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("buttonId", buttonId)
    }



    fun onClickButton(view: View?) {
        val textId = when (view?.id) {
            R.id.button -> R.id.textView
            R.id.button2 -> R.id.textView2
            R.id.button3 -> R.id.textView3
            else -> R.id.textView
        }

        buttonId = textId
        highlightText(textId)


        val ClickButtonIntent = Intent(this, DetailActivity::class.java)
        startActivityForResult(ClickButtonIntent, REQUEST_CODE)
    }

    fun onFavorites(view: View?) {
        val ClickButtonIntent = Intent(this, LikeActivity::class.java)
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
        val textView = findViewById<TextView>(R.id.textView)
        textView.setTextColor(resources.getColor(android.R.color.secondary_text_light))
        val textView2 = findViewById<TextView>(R.id.textView2)
        textView2.setTextColor(resources.getColor(android.R.color.secondary_text_light))
        val textView3 = findViewById<TextView>(R.id.textView3)
        textView3.setTextColor(resources.getColor(android.R.color.secondary_text_light))
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


