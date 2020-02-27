package com.example.otusfirstapp

import android.app.Application



class RetroApp : Application() {
    var service: ApiInterface

    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
    }

    private fun initRetrofit() {

        val retrofit = AdvancedApiClient.getClient()

        service = retrofit.create(ApiInterface::class.java)
    }

    companion object {

        var instance: RetroApp? = null
            private set
    }
}
