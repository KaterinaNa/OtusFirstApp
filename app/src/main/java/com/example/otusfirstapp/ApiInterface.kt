package com.example.otusfirstapp

import android.os.Parcel
import android.os.Parcelable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface() : Parcelable {
    @GET("FilmInfo/{id}")
    Call<FilmInfoResponse> getFilmInfoDelails(@Path("id", id: Int), @Query("api-key", apiKey: String))
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApiInterface> {
        override fun createFromParcel(parcel: Parcel): ApiInterface {
            return ApiInterface(parcel)
        }

        override fun newArray(size: Int): Array<ApiInterface?> {
            return arrayOfNulls(size)
        }
    }
}