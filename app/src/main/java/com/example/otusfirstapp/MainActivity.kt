package com.example.otusfirstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val REQUEST_CODE = 42
const val ANSWER_CODE = "Answer"


class MainActivity : AppCompatActivity() {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}


