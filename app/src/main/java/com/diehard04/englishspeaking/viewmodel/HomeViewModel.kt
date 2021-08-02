package com.diehard04.englishspeaking.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.Resource
import com.diehard04.englishspeaking.data.model.HomeModel
import com.diehard04.englishspeaking.data.model.CategoryModel
import com.diehard04.englishspeaking.data.repository.HomeRepository
import com.diehard04.englishspeaking.utils.Constants
import kotlinx.coroutines.*
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel(){
    private val TAG = HomeViewModel::class.java.name
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    private var loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loading.postValue(false)
    }

    // get data from repository suspend method
    fun getContent(context: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            // as dashboard data not depended on backed getting information form hardcode data. "getMainInfo()"
            var arrayList = getMainInfo(context)
            emit(Resource.success(data = arrayList))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun getMainInfo(context: Context): ArrayList<HomeModel> {
        val arrayList = ArrayList<HomeModel>()
        with(arrayList) {
            add(
                HomeModel(
                    Constants.FRIEND_FAMILY, Constants.SECTION + "10",
                    Constants.CONVERSATION + 16, context.resources.getDrawable(R.drawable.family)
                )
            )
            add(
                HomeModel(
                    Constants.EATING_TOPIC,
                    Constants.SECTION + "12",
                    Constants.CONVERSATION + "17",
                    context.resources.getDrawable(R.drawable.eating_outside)
                )
            )
            add(
                HomeModel(
                    Constants.DAY_TO_DAY, Constants.SECTION + "5",
                    Constants.CONVERSATION + 11, context.resources.getDrawable(R.drawable.day_day)
                )
            )
            add(
                HomeModel(
                    Constants.TALK_ABOUT_CHILD,
                    Constants.SECTION + "10",
                    Constants.CONVERSATION + 16,
                    context.resources.getDrawable(R.drawable.talk_child)
                )
            )
            add(
                HomeModel(
                    Constants.SHOP_MARKET, Constants.SECTION + "8",
                    Constants.CONVERSATION + 6, context.resources.getDrawable(R.drawable.shop_conv)
                )
            )
            add(
                HomeModel(
                    Constants.ENTERTAINMENT,
                    Constants.SECTION + "11",
                    Constants.CONVERSATION + 6,
                    context.resources.getDrawable(R.drawable.entertainment)
                )
            )
            add(
                HomeModel(
                    Constants.SCHOOL_COLLEGE, Constants.SECTION + "4",
                    Constants.CONVERSATION + 6, context.resources.getDrawable(R.drawable.school)
                )
            )
            add(
                HomeModel(
                    Constants.WORK_OFFICE, Constants.SECTION + "14",
                    Constants.CONVERSATION + 21, context.resources.getDrawable(R.drawable.work)
                )
            )
            add(
                HomeModel(
                    Constants.SPORTS, Constants.SECTION + "10",
                    Constants.CONVERSATION + 16, context.resources.getDrawable(R.drawable.sports)
                )
            )
            add(
                HomeModel(
                    Constants.TRIP_VACAT, Constants.SECTION + "9",
                    Constants.CONVERSATION + 11, context.resources.getDrawable(R.drawable.holiday)
                )
            )
            add(
                HomeModel(
                    Constants.ENVIRONMENT_NATURE,
                    Constants.SECTION + "5",
                    Constants.CONVERSATION + 26,
                    context.resources.getDrawable(R.drawable.environment)
                )
            )
            add(
                HomeModel(
                    Constants.SPECIAL_IMPORT, Constants.SECTION + "10",
                    Constants.CONVERSATION + 21, context.resources.getDrawable(R.drawable.special)
                )
            )
        }
        return arrayList
    }

    private val _subSectionList = MutableLiveData<List<CategoryModel>>()

    /**
     * fetch section information from firebase using CoroutineScope
     */
    fun fetchSectionDetails(sectionName: String): LiveData<List<CategoryModel>> {
        loading.postValue(true)
        if (sectionName == Constants.FRIEND_FAMILY) {
            CoroutineScope(Dispatchers.IO).launch {
                val threadName = Thread.currentThread().name
                Log.d("threadName ", "IO $threadName")
//                withContext(Dispatchers.Main) {
//                    val threadNameMain = Thread.currentThread().name
//                    Log.d("threadNameMain ", "Main $threadNameMain")
//                    _subSectionList.value = sectionInfoList
//                }
                //_subSectionList.postValue(homeRepository.getFriendFamilyContents())
            }
        }
        return _subSectionList
    }

    fun fetchSectionDataFromEmit(sectionName: String, context: Context?):LiveData<Resource<ArrayList<CategoryModel>>> {
        var arrayList = homeRepository.getFriendFamilyContents(context)
        return arrayList
    }
//        liveData(Dispatchers.IO) {
//            if (sectionName == Constants.FRIEND_FAMILY) {
//                emit(Resource.loading(data = null))
//                try {
//                    // as dashboard data not depended on backed getting information form hardcode data. "getMainInfo()"
//
//                    val threadNameMain = Thread.currentThread().name
//                    Log.d("threadName ", " $threadNameMain")
//                    emit(Resource.success(data = arrayList))
//                } catch (exception: Exception) {
//                    emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//                }
//            }
//        }
}