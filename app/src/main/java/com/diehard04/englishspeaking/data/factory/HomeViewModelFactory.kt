package com.diehard04.englishspeaking.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diehard04.englishspeaking.data.api.ApiHelper
import com.diehard04.englishspeaking.data.repository.HomeRepository
import com.diehard04.englishspeaking.view.home.HomeViewModel

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(HomeRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}