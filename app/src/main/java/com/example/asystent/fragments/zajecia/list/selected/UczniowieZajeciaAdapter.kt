package com.example.asystent.fragments.zajecia.list.selected

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.fragments.uczen.list.ListaUczniowAdapter
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import kotlinx.android.synthetic.main.uczen_row.view.*

class UczniowieZajeciaAdapter: RecyclerView.Adapter<UczniowieZajeciaAdapter.MyViewHolder>() {
    private var listaUczniow = emptyList<UczenZajecia>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.uczen_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listaUczniow[position]
        holder.itemView.imie.text = currentItem.id_ucznia.toString()
        holder.itemView.nazwisko.text = currentItem.id_zajec.toString()
        holder.itemView.nr.text = ""

    }

    override fun getItemCount(): Int {
        return listaUczniow.size
    }

    fun setData(uczenZajecia: List<UczenZajecia>){
        this.listaUczniow = uczenZajecia
        notifyDataSetChanged()
    }
}