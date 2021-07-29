package com.diehard04.englishspeaking.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.Resource
import com.diehard04.englishspeaking.data.model.ContentModel
import com.diehard04.englishspeaking.data.model.SectionsModel
import com.diehard04.englishspeaking.data.repository.HomeRepository
import com.diehard04.englishspeaking.utils.Constants
import kotlinx.coroutines.*
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

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
    private fun getMainInfo(context: Context): ArrayList<ContentModel> {
        val arrayList = ArrayList<ContentModel>()
        with(arrayList) {
            add(
                ContentModel(
                    Constants.FRIEND_FAMILY, Constants.SECTION + "10",
                    Constants.CONVERSATION + 16, context.resources.getDrawable(R.drawable.family)
                )
            )
            add(
                ContentModel(
                    Constants.EATING_TOPIC,
                    Constants.SECTION + "12",
                    Constants.CONVERSATION + "17",
                    context.resources.getDrawable(R.drawable.eating_outside)
                )
            )
            add(
                ContentModel(
                    Constants.DAY_TO_DAY, Constants.SECTION + "5",
                    Constants.CONVERSATION + 11, context.resources.getDrawable(R.drawable.day_day)
                )
            )
            add(
                ContentModel(
                    Constants.TALK_ABOUT_CHILD,
                    Constants.SECTION + "10",
                    Constants.CONVERSATION + 16,
                    context.resources.getDrawable(R.drawable.talk_child)
                )
            )
            add(
                ContentModel(
                    Constants.SHOP_MARKET, Constants.SECTION + "8",
                    Constants.CONVERSATION + 6, context.resources.getDrawable(R.drawable.shop_conv)
                )
            )
            add(
                ContentModel(
                    Constants.ENTERTAINMENT,
                    Constants.SECTION + "11",
                    Constants.CONVERSATION + 6,
                    context.resources.getDrawable(R.drawable.entertainment)
                )
            )
            add(
                ContentModel(
                    Constants.SCHOOL_COLLEGE, Constants.SECTION + "4",
                    Constants.CONVERSATION + 6, context.resources.getDrawable(R.drawable.school)
                )
            )
            add(
                ContentModel(
                    Constants.WORK_OFFICE, Constants.SECTION + "14",
                    Constants.CONVERSATION + 21, context.resources.getDrawable(R.drawable.work)
                )
            )
            add(
                ContentModel(
                    Constants.SPORTS, Constants.SECTION + "10",
                    Constants.CONVERSATION + 16, context.resources.getDrawable(R.drawable.sports)
                )
            )
            add(
                ContentModel(
                    Constants.TRIP_VACAT, Constants.SECTION + "9",
                    Constants.CONVERSATION + 11, context.resources.getDrawable(R.drawable.holiday)
                )
            )
            add(
                ContentModel(
                    Constants.ENVIRONMENT_NATURE,
                    Constants.SECTION + "5",
                    Constants.CONVERSATION + 26,
                    context.resources.getDrawable(R.drawable.environment)
                )
            )
            add(
                ContentModel(
                    Constants.SPECIAL_IMPORT, Constants.SECTION + "10",
                    Constants.CONVERSATION + 21, context.resources.getDrawable(R.drawable.special)
                )
            )
        }
        return arrayList
    }

    private val _subSectionList = MutableLiveData<List<SectionsModel>>()

    /**
     * fetch section information from firebase using coroutine
     */
    fun fetchSectionDetails(sectionName: String): LiveData<List<SectionsModel>> {
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
                _subSectionList.postValue(homeRepository.getFriendFamilyContents())
            }
        }
        return _subSectionList
    }

    fun fetchLoading(): LiveData<Boolean> = loading

}