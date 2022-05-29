package com.example.myapplication.ui

import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.model.ListaEESSPrecio


class GasStatusListAdapter(private val courses: List<ListaEESSPrecio>, private val combustible:String) :
    RecyclerView.Adapter<GasStatusViewHolder>()  {
    private lateinit var m_listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position:Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        m_listener=listener


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GasStatusViewHolder {
        val itemView= ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GasStatusViewHolder(itemView.root,m_listener)       //Puede que no esté bien
    }

    override fun onBindViewHolder(holder: GasStatusViewHolder, position: Int) {
        val item = courses.get(position)
        holder.bind(item,combustible)
       // holder.itemView.setOnClickListener { onItemClicked(item) }
    }


    override fun getItemCount()=courses.size

}
