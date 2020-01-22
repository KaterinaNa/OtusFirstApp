package com.example.otusfirstapp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    fun onClickinvite(view: View?) {
        val textMessage = "Поделиться в"
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val chooser = Intent.createChooser(sendIntent, title)
        val title = resources.getString(R.string.chooser)
        val let = sendIntent.resolveActivity(packageManager)?.let {
            startActivity(chooser)
        }
    }

    fun onCheckBoxClicked (view: View?) {
        val mycheckBox = findViewById<CheckBox>(R.id.СheckBox)
        editText = findViewById(R.layout.activity_detail)
        editText.isEnabled = true
        editText.text = editText.getText()

    }





    }




}