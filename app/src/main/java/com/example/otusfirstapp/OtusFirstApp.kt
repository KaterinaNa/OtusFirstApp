package com.example.otusfirstapp

import android.app.Application

class OtusFirstApp : Application() {
    var service: ApiInterface? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

        initRetrofit()
    }

    private fun initRetrofit() {

        val retrofit = ApiClient.getClient()

        service = retrofit?.create(ApiInterface::class.java)
    }

    companion object {
        var instance: OtusFirstApp? = null
    }
}
