package com.example.otusfirstapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var editText =findViewById<EditText>(R.id.editText)
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
        val intent = Intent()
        intent.putExtra(ANSWER_CODE, textField.text.toString())
        setResult(Activity.RESULT_OK, intent)


   }


}