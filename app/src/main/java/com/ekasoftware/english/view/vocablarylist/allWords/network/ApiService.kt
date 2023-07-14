package com.ekasoftware.english.view.vocablarylist.network

import com.ekasoftware.english.view.vocablarylist.model.Word
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/v1/dcb916ab-8593-4899-9db8-99bba94477db")
    suspend fun getMovies() : List<Word>


    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://mocki.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}