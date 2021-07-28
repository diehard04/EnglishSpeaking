package com.diehard04.englishspeaking.data.api

import com.diehard04.englishspeaking.data.model.ContentModel
import retrofit2.http.GET

/**
 * Created by DieHard_04 on 28-07-2021.
 */
interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<ContentModel>
}