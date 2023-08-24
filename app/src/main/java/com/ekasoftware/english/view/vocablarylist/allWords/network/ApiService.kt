package com.ekasoftware.english.view.vocablarylist.allWords.network

import com.ekasoftware.english.view.vocablarylist.allWords.model.Word
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getMovies() : List<Word>

    //https://node-api-vercel-lake.vercel.app/

    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://node-api-vercel-lake.vercel.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}