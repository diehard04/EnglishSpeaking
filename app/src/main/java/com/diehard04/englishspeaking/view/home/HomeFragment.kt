package com.diehard04.englishspeaking.view.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diehard04.englishspeaking.data.api.ApiHelper
import com.diehard04.englishspeaking.data.api.RetrofitBuilder
import com.diehard04.englishspeaking.data.factory.HomeViewModelFactory
import com.diehard04.englishspeaking.data.model.HomeModel
import com.diehard04.englishspeaking.data.model.CategoryModel
import com.diehard04.englishspeaking.data.model.Status
import com.diehard04.englishspeaking.databinding.FragmentHomeBinding
import com.diehard04.englishspeaking.view.`interface`.HomeAdapterListener
import com.diehard04.englishspeaking.view.categorylist.CategoryListActivity
import com.diehard04.englishspeaking.viewmodel.HomeViewModel

class HomeFragment : Fragment(), HomeAdapterListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mAdapterHome: HomeAdapter
    private var categoryList: ArrayList<CategoryModel> = ArrayList<CategoryModel>()
    private var TAG = HomeFragment::class.java.name

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var rvContents: RecyclerView
    private lateinit var activity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView(root)
        initViewModel()
        setupObserver()
        return root
    }

    private fun setupObserver() {
        context?.let {
            homeViewModel.getContent(it).observe(viewLifecycleOwner, Observer {
                it.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            rvContents.visibility = View.VISIBLE
                            _binding?.progressBar?.visibility = View.GONE
                            updateList(resource.data)
                        }
                        Status.ERROR -> {
                            rvContents.visibility = View.VISIBLE
                            _binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                        }

                        Status.LOADING -> {
                            _binding!!.progressBar.visibility = View.VISIBLE
                            _binding!!.rvContents.visibility = View.GONE
                        }

                    }
                }
            })
        }
    }

    private fun updateList(data: List<HomeModel>?) {
        mAdapterHome.addContent(data as ArrayList<HomeModel>)
        mAdapterHome.notifyDataSetChanged()
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(HomeViewModel::class.java)

    }

    private fun initView(root: View) {
        rvContents = _binding?.rvContents!!
        mAdapterHome = HomeAdapter(activity, arrayListOf(), this@HomeFragment)
        rvContents.layoutManager = LinearLayoutManager(activity)
        rvContents.itemAnimator = DefaultItemAnimator()

        rvContents.adapter = mAdapterHome
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(title: String) {
        Log.d(TAG, " title $title")
        categoryList.clear()
        homeViewModel.fetchSectionDataFromEmit(title, context)?.observe(this, Observer {
            Log.d("it ", it.data.toString())
            when(it.status) {
                Status.SUCCESS -> {
                    categoryList = it.data as ArrayList<CategoryModel>
                    Log.d(TAG , " categoryList ${categoryList.size}" )
                    val intent = Intent(getActivity(), CategoryListActivity::class.java)
                    val bundle = Bundle();
                    bundle.putSerializable("data", categoryList)
                    intent.putExtras(bundle)
                    startActivity(intent)
                    _binding?.progressBar?.visibility = View.GONE
                }
                Status.ERROR -> {
                    _binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {
                    _binding!!.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }
}