package com.diehard04.englishspeaking.viewmodel

import com.diehard04.englishspeaking.data.model.HomeModel
import com.diehard04.englishspeaking.data.repository.HomeRepository
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by DieHard_04 on 30-07-2021.
 */

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : TestCase() {

    @Mock
    lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var homeRepository: HomeRepository

  /*  @Before
    fun setUp() {

        homeViewModel = HomeViewModel(homeRepository)
    }*/

    @Test
    fun testGetContent() {
        var contentModelList = ArrayList<HomeModel>()
    }


    fun testFetchSectionDetails() {

    }

    @Test
    fun `insert shopping item with empty filed, return error`() {
        homeViewModel.fetchSectionDetails("name")
    }

    @Test
    fun testFetchSectionDataFromEmit() {

    }

    fun testFetchLoading() {}
}