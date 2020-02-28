package com.example.otusfirstapp

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val REQUEST_CODE = 42
const val ANSWER_CODE = "Answer"
const val API_KEY = "836cbf0813244b3c64888bc53e1975f8"

lateinit var items: ArrayList<Film>



class MainActivity : AppCompatActivity(), OnNewsClickListener {

    private var buttonId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = itemView.findViewById<ImageView>(R.id.image)

        val PosterPath = Film.setToString(poster())

        Glide {
            .with(items[posterId])
            .load(PosterPath)
            .into(imageView)
        }


        val apiService = OtusFirstApp.instance?.service

        if (savedInstanceState == null) {

            val call = apiService?.getTopRatedMovies(API_KEY)?.enqueue(object : Callback<FilmsResponse> {
                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                    Log.e(TAG, t.toString())
                }

                override fun onResponse(
                    call: Call<FilmsResponse>,
                    response: Response<FilmsResponse>
                ) {
                    val res = response.body()?.results
                    if (res == null) return

                    items = res
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, FilmsListFragment(), FilmsListFragment.TAG)
                        .commit()

                }
            })
        }


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




