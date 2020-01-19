package com.example.otusfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox

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
        /*val title = resources.getString(R.string.chooser_title)
        // Создаем Intent для отображения диалога выбора.
        val chooser = Intent.createChooser(sendIntent, title)
        // Проверяем, что intent может быть успешно обработан*/
        sendIntent.resolveActivity(packageManager)?.let {
                startActivity(sendIntent)
            }
        }



    val checkBox = findViewById<CheckBox>(R.id.checkBox)
    if (checkBox.isChecked()) {
        checkBox.setChecked(false);
    }

}