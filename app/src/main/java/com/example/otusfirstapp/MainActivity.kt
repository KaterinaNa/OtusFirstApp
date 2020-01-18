package com.example.otusfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    /*fun onClickfirstButton(view: View?) {
        val Podrobnee = Toast.makeText(this, "Hello world!", Toast.LENGTH_SHORT)

        Podrobnee.show()
    }

    fun onClicksecondButton(view: View?) {
        val countString = textView.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++
        textView.text = count.toString()
    }
*/
    fun onClickButton(view: View?) {

        val textId = when (view?.id) {
            R.id.button -> R.id.textView
            R.id.button2 -> R.id.textView2
            R.id.button3 -> R.id.textView3
            else -> R.id.textView
        }

        val textView = findViewById<TextView>( textId )
        textView.setTextColor(resources.getColor(R.color.colorAccent))


        val ClickButtonIntent = Intent(this, DetailActivity::class.java)
        startActivity(ClickButtonIntent)
    }
}


