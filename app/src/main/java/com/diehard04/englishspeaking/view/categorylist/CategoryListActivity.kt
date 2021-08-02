package com.diehard04.englishspeaking.view.categorylist

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.diehard04.englishspeaking.R
import com.diehard04.englishspeaking.data.model.CategoryModel
import com.diehard04.englishspeaking.view.BaseActivity

/**
 * Created by DieHard_04 on 31-07-2021.
 */
class CategoryListActivity:BaseActivity() {

    val TAG = CategoryListActivity::class.java.name
    var ivCategory:ImageView ? = null
    var tvCategoryContent:TextView ?= null
    var categoryList:ArrayList<CategoryModel> = ArrayList<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        initView()
        categoryList = intent.extras?.getSerializable("data") as ArrayList<CategoryModel>
        Log.d("categoryList ", "${categoryList.size}")
    }

    private fun initView() {
        ivCategory = findViewById(R.id.ivCategory)
        tvCategoryContent = findViewById(R.id.tvCategoryContent)

    }
}