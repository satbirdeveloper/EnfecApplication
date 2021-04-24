package com.example.enfecapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enfecapplication.dataClasses.CompanyData
import com.example.enfecapplication.repositories.EnfecCompanyRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var companyData=ArrayList<CompanyData>()

    fun getCompanyData(success:()->Unit,failure:()->Unit){
        viewModelScope.launch {
            try {
                val data = EnfecCompanyRepo().getCompanyData()
                if (data.isNullOrEmpty()) {
                    Log.e("AccessFailure", "AccessFailure")
                    failure()
                } else {
                    companyData.clear()
                    companyData.addAll(data)
                    success()
                }
            }
            catch (e:Exception){
                Log.e("ResponseFailure", "Exception: ${e.message}")
                failure()
            }
        }
    }

}