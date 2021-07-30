package com.diehard04.englishspeaking.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.diehard04.englishspeaking.data.api.ApiHelper
import com.diehard04.englishspeaking.data.model.SectionsModel
import com.google.firebase.database.*

/**
 * Created by DieHard_04 on 28-07-2021.
 */
class HomeRepository(private val apiHelper: ApiHelper) {

    suspend fun getUser() = apiHelper.getUser()

    // creating a variable for
    // our Firebase Database.
    var firebaseDatabase: FirebaseDatabase? = null

    // creating a variable for our
    // Database Reference for Firebase.
    var databaseReference: DatabaseReference? = null

    /**
     * get the friend and family response from firebase
     */
    fun getFriendFamilyContents(context: Context?): List<SectionsModel> {
        val sectionInfoList = ArrayList<SectionsModel>()
        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()
        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase?.getReference("FRIEND_FAMILY")

        databaseReference?.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (item in snapshot.children) {
                    val sectionsModel = item.getValue(SectionsModel::class.java)
                    if (sectionsModel != null) {
                        sectionInfoList.add(sectionsModel)
                    }
                }
                Log.d("onDataChange ", "${sectionInfoList.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "error= ${error.message}", Toast.LENGTH_SHORT).show()
            }

        })

//        sectionInfoList.add(SectionsModel("da", "2"))
//        sectionInfoList.add(SectionsModel("da", "2"))
//        sectionInfoList.add(SectionsModel("da", "2"))
//        sectionInfoList.add(SectionsModel("da", "2"))
        return sectionInfoList
    }

}