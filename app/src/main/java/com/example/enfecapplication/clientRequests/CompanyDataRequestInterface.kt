package com.example.enfecapplication.clientRequests

import com.example.enfecapplication.dataClasses.CompanyData
import retrofit2.Call
import retrofit2.http.GET




interface CompanyDataRequestInterface {

    @GET("users")
   suspend fun getCompanyData(): List<CompanyData>
}