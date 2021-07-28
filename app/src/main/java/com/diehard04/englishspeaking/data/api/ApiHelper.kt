package com.diehard04.englishspeaking.data.api

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class ApiHelper(private val apiService: ApiService) {

    suspend fun getUser() = apiService.getUsers()
}