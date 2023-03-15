package com.example.pixabay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    fun getImage(
        @Query("key") key: String = "34060888-611f2c6c3ce4aa18e3c8c17b5",
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20
    ): Call<PixabayModel>
}