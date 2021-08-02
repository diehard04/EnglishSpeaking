package com.diehard04.englishspeaking.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by DieHard_04 on 29-07-2021.
 */
class CategoryModel: Serializable{

    @SerializedName("SECTION")
    @Expose
    private var SECTION: String? = null

    @SerializedName("image")
    @Expose
    private var image: String? = null


    fun setSection(section:String) {
        this.SECTION= section
    }

    fun setImage(image:String) {
        this.image = image
    }

    fun getSection():String? {
        return SECTION
    }

    fun getImage():String? {
        return image
    }
}