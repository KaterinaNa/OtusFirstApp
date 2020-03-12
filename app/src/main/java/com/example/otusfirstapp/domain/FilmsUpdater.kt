package com.example.otusfirstapp.domain

import android.os.Handler
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.data.entity.Film
import com.example.otusfirstapp.presentation.view.API_KEY
import com.example.otusfirstapp.presentation.view.MainActivity.Companion.TAG
import javax.security.auth.callback.Callback

class FilmsUpdater (private val service: FilmService) : LifecycleObserver {
    private val handel = Handler()
    private val taskRunnable = object Runnable {
        override fun run()
        OurTask(service) { handler.postDelyaed(this, DELAY.toLong())}.run()
    }
}

class OurTask (val service: FilmService, val onCompleteListener: () -> Unit): Runnable {
    override fun run() {
        service.getTopRatedMovies(API_KEY).enqueue(object : Callback<ArrayList<Film>>) {
            onCompleteListener.invoke()
        }
        override fun onFalure(callback<ArrayList<Film>>, t: Throwable) {
        }
    }
}
@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
fun onLifecycleResume() {
    Log.d(TAG, "resume")
    handler.postDelyaed(taskRunnble, DELAY.toLong())
}

@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
fun onLifecyclePaused() {
    Log.d(TAG, "onpause")
    handler.removeCallbackAndMessage(null)
}




companion object {
    private val TAG = "FilmsUpdater"
    private val DELAY = 5000
}