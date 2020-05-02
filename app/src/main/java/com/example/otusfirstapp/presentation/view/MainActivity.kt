package com.example.otusfirstapp.presentation.view

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.otusfirstapp.R
import com.example.otusfirstapp.data.entity.Film
import com.google.android.material.bottomnavigation.BottomNavigationView

const val API_KEY = "836cbf0813244b3c64888bc53e1975f8"

class MainActivity : AppCompatActivity(),
    OnFilmClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    FilmsListFragment(),
                    FilmsListFragment.TAG
                )
                .commit()
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
                        .replace(
                            R.id.fragmentContainer,
                            FilmsListFragment(),
                            FilmsListFragment.TAG
                        )
                        .commit()
                    true
                }
                R.id.bottom_navigator_fav -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            FavoritesFragment(),
                            FavoritesFragment.TAG
                        )
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i(TAG, "onActivityResult $requestCode")
        super.onActivityResult(requestCode, resultCode, data)
/*        if (requestCode == 42) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {
                    val film = intent.getParcelableExtra<Film>("film")
                    openFilmDetailed()
                }
            }
        }*/
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

    override fun openFilmDetailed() {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailsFragment(),
                DetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
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
            bld.setMessage(getString(R.string.exit_ask))
            bld.setTitle(getString(R.string.exit_ask_title))
            bld.setNegativeButton(getString(R.string.exit_no), no_lst)
            bld.setPositiveButton(getString(R.string.exit_yes), yes_lst)
            val dialog: AlertDialog = bld.create()
            dialog.show()
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
