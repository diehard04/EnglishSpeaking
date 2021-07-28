package com.diehard04.englishspeaking.view.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diehard04.englishspeaking.data.api.ApiHelper
import com.diehard04.englishspeaking.data.api.RetrofitBuilder
import com.diehard04.englishspeaking.data.factory.HomeViewModelFactory
import com.diehard04.englishspeaking.data.model.Status
import com.diehard04.englishspeaking.data.model.User
import com.diehard04.englishspeaking.databinding.FragmentHomeBinding
import com.diehard04.englishspeaking.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: HomeAdapter
    private lateinit var contentList: ArrayList<User>

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
    ):
            View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView(root)
        initViewModel()
        setupObserver()
        return root
    }

    private fun setupObserver() {
        homeViewModel.getContent().observe(viewLifecycleOwner, Observer {
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

    private fun updateList(data: List<User>?) {
        contentList = data as ArrayList<User>
        adapter.notifyDataSetChanged()
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            HomeViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(HomeViewModel::class.java)

    }

    private fun initView(root: View) {
        rvContents = _binding?.rvContents!!
        rvContents.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter(activity, arrayListOf())
        rvContents.addItemDecoration(
            DividerItemDecoration(
                rvContents.context,
                (rvContents.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvContents.apply {
            adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}