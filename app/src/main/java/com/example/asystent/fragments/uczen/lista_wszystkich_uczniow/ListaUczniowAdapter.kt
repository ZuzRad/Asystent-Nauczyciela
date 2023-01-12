package com.example.asystent.fragments.uczen.lista_wszystkich_uczniow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.model.Uczen
import kotlinx.android.synthetic.main.uczen_row.view.*


class ListaUczniowAdapter: RecyclerView.Adapter<ListaUczniowAdapter.MyViewHolder>() {


    private var listaUczniow = emptyList<Uczen>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.uczen_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listaUczniow[position]
        holder.itemView.imie.text = currentItem.imie
        holder.itemView.nazwisko.text = currentItem.nazwisko
        holder.itemView.nr.text = currentItem.nr.toString()
    }

    override fun getItemCount(): Int {
        return listaUczniow.size
    }

    fun setData(uczen: List<Uczen>){
       this.listaUczniow = uczen
        notifyDataSetChanged()
    }
}