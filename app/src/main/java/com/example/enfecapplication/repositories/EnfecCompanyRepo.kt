package com.example.enfecapplication.repositories

import com.example.enfecapplication.clientRequests.CompanyDataRequestInterface
import com.example.enfecapplication.dataClasses.CompanyData
import com.example.enfecapplication.networking.Network

class EnfecCompanyRepo {

   suspend fun getCompanyData():List<CompanyData>{
      return Network.createRetrofit().create(CompanyDataRequestInterface::class.java).getCompanyData()
   }
}