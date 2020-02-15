package com.example.otusfirstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_detail_fragment)

        var filmId = intent.getIntExtra("FilmId", 0)

        val filmName = findViewById<TextView>(R.id.filmName)
        val filmImage = findViewById<ImageView>(R.id.filmImage)
        val filmDescr = findViewById<TextView>(R.id.filmDescr)

        filmName.text = items[filmId].name
        filmImage.setImageDrawable(getDrawable(items[filmId].posterId))
        filmDescr.text = items[filmId].detail




        var editText = findViewById<EditText>(R.id.editText)
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                this@DetailActivity.onTextChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        editText.isEnabled = false
    }

    fun onClickinvite(view: View?) {
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

    fun onCheckBoxClicked (view: View?) {
        val mycheckBox = findViewById<CheckBox>(R.id.mycheckBox)
        val textField = findViewById<TextView>(R.id.editText)
        textField.isEnabled = mycheckBox.isChecked
        Log.i("Text", textField.text.toString())


    }

    fun onTextChanged () {
        val textField = findViewById<TextView>(R.id.editText)
        val text = textField.text.toString()
        val intent = Intent()
        intent.putExtra(ANSWER_CODE, text)
        setResult(Activity.RESULT_OK, intent)
        Log.i("detail_activity", text)
    }


}