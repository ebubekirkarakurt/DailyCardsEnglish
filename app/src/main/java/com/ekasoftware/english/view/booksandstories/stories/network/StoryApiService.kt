package com.ekasoftware.english.view.booksandstories.stories.network

import com.ekasoftware.english.view.booksandstories.stories.model.Story
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface StoryApiService {
    @GET("api/v1/stories")
    suspend fun getStories() : List<Story>

    //https://64b3dc980efb99d86268750d.mockapi.io/api/v1/stories

    companion object {
        var apiService: StoryApiService? = null
        fun getInstance() : StoryApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://64b3dc980efb99d86268750d.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(StoryApiService::class.java)
            }
            return apiService!!
        }
    }
}