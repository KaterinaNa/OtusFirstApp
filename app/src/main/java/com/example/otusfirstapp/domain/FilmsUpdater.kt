package com.example.otusfirstapp.domain

import androidx.lifecycle.LifecycleObserver


class FilmsUpdater (
    private val interactor: FilmInteractor
) : LifecycleObserver {
/*
    private val handler = Handler()

    private val taskRunnable = object : Runnable {
        override fun run() {
            Log.i(TAG, "taskRunnable")
            val task = OurTask(interactor) {
                handler.postDelayed(this, DELAY.toLong())
            }
            task.run()
        }
    }

    class OurTask (private val interactor: FilmInteractor, val onCompleteListener: () -> Unit): Runnable {
        override fun run() {
            Log.i(TAG, "Task")
            val dateNow = Date().time
            val dateResponse = OtusFirstApp.instance.sharedPref.getLong(
                OtusFirstApp.instance.LAST_RESPONSE_KEY, dateNow
            )
            val timePeriod = dateResponse-dateNow
            if (timePeriod >= PERIOD) {
                interactor.getTopFilms(API_KEY, 1, object : GetTopFilmsCallback {
                    override fun onSuccess(films: ArrayList<Film>) {
                        onCompleteListener()
                    }

                    override fun onError(error: String) {

                        // run()
                    }
                })
            }
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

*/
    companion object {
        private const val TAG = "FilmsUpdater"
        private const val DELAY = 5000
        private const val PERIOD = 20*60*1000
    }

}
