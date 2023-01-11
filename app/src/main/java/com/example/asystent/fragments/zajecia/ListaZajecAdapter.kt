package com.example.asystent.fragments.zajecia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.zajecia.Zajecia
import kotlinx.android.synthetic.main.zajecia_row.view.*

class ListaZajecAdapter: RecyclerView.Adapter<ListaZajecAdapter.MyViewHolder>() {

    private var listaZajec = emptyList<Zajecia>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.zajecia_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listaZajec[position]
//        holder.itemView.text_id.text = currentItem.id.toString()
        holder.itemView.nazwa.text = currentItem.nazwa
        holder.itemView.dzien.text = currentItem.dzien
        holder.itemView.godzina.text = currentItem.godzina

    }

    override fun getItemCount(): Int {
        return listaZajec.size
    }

    fun setData(zajecia: List<Zajecia>){
        this.listaZajec = zajecia
        notifyDataSetChanged()
    }
}