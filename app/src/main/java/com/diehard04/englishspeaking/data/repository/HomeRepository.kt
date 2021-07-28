package com.diehard04.englishspeaking.data.repository

import com.diehard04.englishspeaking.data.api.ApiHelper

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeRepository(private val apiHelper: ApiHelper) {

    suspend fun getUser() = apiHelper.getUser()

}