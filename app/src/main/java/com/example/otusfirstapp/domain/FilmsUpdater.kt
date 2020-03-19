package com.example.otusfirstapp.domain

import android.os.Handler
import android.util.Log

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.otusfirstapp.data.FilmService
import com.example.otusfirstapp.data.entity.FilmsResponse

import com.example.otusfirstapp.presentation.view.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmsUpdater (private val service: FilmService) : LifecycleObserver {
    private val handler = Handler()

    private val taskRunnable = object : Runnable {
        override fun run() {
            Log.i(TAG, "taskRunnable")
            val task = OurTask(service, {
                handler.postDelayed(this, DELAY.toLong())
            })
            task.run()
        }
    }

    class OurTask (val service: FilmService, val onCompleteListener: () -> Unit): Runnable {
        override fun run() {
            Log.i(TAG, "Task")
            service.getTopRatedMovies(API_KEY).enqueue(object : Callback<FilmsResponse> {
                override fun onResponse(
                    call: Call<FilmsResponse>,
                    response: Response<FilmsResponse>
                ) {
                    onCompleteListener()
                }

                override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {

                }
            })
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onLifecycleResume() {
        Log.i(TAG, "resume")
        handler.postDelayed(taskRunnable, DELAY.toLong())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecyclePaused() {
        Log.i(TAG, "onpause")
        handler.removeCallbacksAndMessages(null)
    }


    companion object {
        private val TAG = "FilmsUpdater"
        private val DELAY = 5000
    }

}