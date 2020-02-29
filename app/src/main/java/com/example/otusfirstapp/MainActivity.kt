package com.example.otusfirstapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val API_KEY = "836cbf0813244b3c64888bc53e1975f8"

var currentPage = 0
lateinit var items: ArrayList<Film>

class MainActivity : AppCompatActivity(), OnNewsClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = OtusFirstApp.instance?.service

        if (savedInstanceState == null) {
            currentPage = 0

            apiService?.getTopRatedMovies(API_KEY, ++currentPage)?.enqueue(object : Callback<FilmsResponse> {
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
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            }
            when (it.itemId) {
                R.id.bottom_navigator_home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, FilmsListFragment(), FilmsListFragment.TAG)
                        .commit()
                    true
                }
                R.id.bottom_navigator_fav -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, FavoritesFragment(), FavoritesFragment.TAG)
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

    private fun onInvite() {
        val textMessage = getString(R.string.share_into)
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
        sendIntent.type = "text/plain"
        val title = resources.getString(R.string.chooser)
        val chooser = Intent.createChooser(sendIntent, title)
        sendIntent.resolveActivity(packageManager)?.let {
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            val bld: AlertDialog.Builder = AlertDialog.Builder(this)
            val no_lst =
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }
            val yes_lst =
                DialogInterface.OnClickListener { dialog, which ->
                    super.onBackPressed()
                }
            bld.setMessage("Вы уверены, что хотите выйти?")
            bld.setTitle("Выход?")
            bld.setNegativeButton("Нет", no_lst)
            bld.setPositiveButton("Выхxод", yes_lst)
            val dialog: AlertDialog = bld.create()
            dialog.show()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
