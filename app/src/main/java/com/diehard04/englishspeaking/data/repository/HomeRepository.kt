package com.diehard04.englishspeaking.data.repository

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
    fun getFriendFamilyContents(): List<SectionsModel> {
        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()
        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase?.getReference("friends & family")

        databaseReference?.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        val sectionInfoList = ArrayList<SectionsModel>()
        sectionInfoList.add(SectionsModel("da", "2"))
        sectionInfoList.add(SectionsModel("da", "2"))
        sectionInfoList.add(SectionsModel("da", "2"))
        sectionInfoList.add(SectionsModel("da", "2"))
        return sectionInfoList
    }

}