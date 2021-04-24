package com.example.enfecapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enfecapplication.adapters.CompanyDataAdapter
import com.example.enfecapplication.dataClasses.CompanyData
import com.example.enfecapplication.dataClasses.CompanyDescData
import com.example.enfecapplication.databinding.ActivityMainBinding
import com.example.enfecapplication.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar

//https://jsonplaceholder.typicode.com/users

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var mainDataBinding: ActivityMainBinding
    private lateinit var companyDataAdapter: CompanyDataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDataBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        setUpRecyclerView()
        setUpSwipeRefresh()
        setUpUI()
        displayDataFromWeb()
    }

    private fun setUpUI(){
        mainDataBinding.increaseSortButton.setOnClickListener {

            val incSort=mainViewModel.companyData.sortedWith(compareBy { it.id })
            mainViewModel.companyData.clear()
            mainViewModel.companyData.addAll(incSort)
            companyDataAdapter.notifyDataSetChanged()
        }
        mainDataBinding.decreaseSortButton.setOnClickListener {
            val decSort=mainViewModel.companyData.sortedWith(compareByDescending { it.id })
            mainViewModel.companyData.clear()
            mainViewModel.companyData.addAll(decSort)
            companyDataAdapter.notifyDataSetChanged()

        }
    }


    private fun setUpRecyclerView(){
        mainDataBinding.enfecRecyclerView.layoutManager=LinearLayoutManager(this)
        companyDataAdapter= CompanyDataAdapter(mainViewModel.companyData)
        mainDataBinding.enfecRecyclerView.adapter=companyDataAdapter
    }

    private fun setUpSwipeRefresh(){
        mainDataBinding.swipeRefreshLayout.setOnRefreshListener {
            mainDataBinding.swipeRefreshLayout.setRefreshing(true)
            displayDataFromWeb()
        }
    }
   private fun  displayDataFromWeb(){

       mainDataBinding.swipeRefreshLayout.setRefreshing(true)
       mainViewModel.getCompanyData(success = { companyDataAdapter.notifyDataSetChanged() },

               failure = {Snackbar.
               make(mainDataBinding.
               enfecRecyclerView,
                       getString(R.string.response_failure),Snackbar.LENGTH_LONG).show() })

       mainDataBinding.swipeRefreshLayout.setRefreshing(false)

   }


}