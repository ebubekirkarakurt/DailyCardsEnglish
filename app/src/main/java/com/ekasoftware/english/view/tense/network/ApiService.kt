package com.ekasoftware.english.view.tense.network

import com.ekasoftware.english.view.tense.model.Tense
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("api/v1/tense")
    suspend fun getTense() : List<Tense>

    //https://64b3dc980efb99d86268750d.mockapi.io/api/v1/tense

    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://64b3dc980efb99d86268750d.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}