package com.diehard04.englishspeaking.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.Resource
import com.diehard04.englishspeaking.data.model.ContentModel
import com.diehard04.englishspeaking.data.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    fun getContent(context: Context) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            var arrayList = getMainInfo(context)
            emit(Resource.success(data = arrayList))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun getMainInfo(context: Context): ArrayList<ContentModel> {
        val arrayList = ArrayList<ContentModel>()
        arrayList.add(ContentModel(context.getString(R.string.t_friends_family), "", ""))

        return arrayList
    }
}