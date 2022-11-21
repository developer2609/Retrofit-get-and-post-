package com.example.retrofit_get.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_get.databinding.ItemLayoutBinding


class RvAdapter (var list: List<MyToDo> = emptyList()) : RecyclerView.Adapter<RvAdapter.VP_Vh>() {

    inner class VP_Vh(var itemListBinding: ItemLayoutBinding):
        RecyclerView.ViewHolder(itemListBinding.root) {
        fun onBind(myToDo: MyToDo) {

            itemListBinding.textName.text=myToDo.sarlavha
            itemListBinding.textDate.text=myToDo.oxirgi_muddat
            itemListBinding.textId.text=myToDo.id.toString()
            itemListBinding.textHolati.text=myToDo.holat


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VP_Vh {
        return VP_Vh(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    }

    override fun onBindViewHolder(holder: VP_Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size



}