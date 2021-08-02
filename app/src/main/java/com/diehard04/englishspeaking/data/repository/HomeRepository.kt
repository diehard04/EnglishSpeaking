package com.diehard04.englishspeaking.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diehard04.englishspeaking.data.api.ApiHelper
import com.diehard04.englishspeaking.data.model.CategoryModel
import com.diehard04.englishspeaking.data.model.Resource
import com.diehard04.englishspeaking.data.model.Status
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeRepository(private val apiHelper: ApiHelper) {

    suspend fun getUser() = apiHelper.getUser()
    val TAG = HomeRepository::class.java.name

    // creating a variable for
    // our Firebase Database.
    var firebaseDatabase: FirebaseDatabase? = null

    // creating a variable for our
    // Database Reference for Firebase.
    var databaseReference: DatabaseReference? = null

    /**
     * get the friend and family response from firebase
     */
    fun getFriendFamilyContents(context: Context?): LiveData<Resource<ArrayList<CategoryModel>>> {
        // below line is used to get the instance
        // of our Firebase database.
        val resource = MutableLiveData<Resource<ArrayList<CategoryModel>>>()
        firebaseDatabase = FirebaseDatabase.getInstance()
        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase?.getReference("FRIEND_FAMILY")
        val sectionInfoList = ArrayList<CategoryModel>()

        databaseReference?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    val sectionsModel = item.getValue(CategoryModel::class.java)
                    if (sectionsModel != null) {
                        sectionInfoList.add(sectionsModel)
                    }
                    resource.value = Resource(Status.SUCCESS, data = sectionInfoList, "")
                }
                Log.d(TAG, "onDataChange ${sectionInfoList.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "error= ${error.message}", Toast.LENGTH_SHORT).show()
                resource.value = Resource(status = Status.ERROR, data = sectionInfoList, "")
            }
        })

        return resource
    }

    public interface FirebaseListener {
        fun getResult(categoryList: ArrayList<CategoryModel>)
    }
}