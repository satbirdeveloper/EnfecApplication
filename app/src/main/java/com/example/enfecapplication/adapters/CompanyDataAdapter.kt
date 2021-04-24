package com.example.enfecapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enfecapplication.R
import com.example.enfecapplication.dataClasses.CompanyData

class CompanyDataAdapter(var dataList:ArrayList<CompanyData>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView=LayoutInflater.from(parent.context).
                        inflate(R.layout.company_row_ui,parent,false)
        return CompanyDataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CompanyDataViewHolder).also {viewHolder->
            dataList[position].let { companyData ->
                viewHolder.idText.text=companyData.id.toString()
                viewHolder.nameText.text=companyData.name
                viewHolder.emailText.text=companyData.email
                viewHolder.companyNameText.text=companyData.company.name
                viewHolder.websiteText.text=companyData.website
            }

        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class CompanyDataViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var idText: TextView =itemView.findViewById(R.id.idTextView)
        var nameText: TextView=itemView.findViewById(R.id.nameTextView)
        var emailText: TextView=itemView.findViewById(R.id.emailTextView)
        var companyNameText: TextView=itemView.findViewById(R.id.companyNameTextView)
        var websiteText: TextView=itemView.findViewById(R.id.websiteTextView)

    }

}