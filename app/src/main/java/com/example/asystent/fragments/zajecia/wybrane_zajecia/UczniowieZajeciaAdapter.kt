package com.example.asystent.fragments.zajecia.wybrane_zajecia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import kotlinx.android.synthetic.main.uczen_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UczniowieZajeciaAdapter(): RecyclerView.Adapter<UczniowieZajeciaAdapter.MyViewHolder>() {
    private lateinit var appDatabase: AppDatabase
    private var listaUczniow = emptyList<UczenZajecia>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        appDatabase = AppDatabase.getDatabase(parent.context) //???
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.uczen_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listaUczniow[position]
        runBlocking(Dispatchers.IO) {
            launch {
                var list: List<Uczen> = appDatabase.uczenDao().wyswietlUczniow2()

                for(x in list.indices){
                    if(list[x].id == currentItem.id_ucznia){
                        holder.itemView.imie.text = list[x].imie
                        holder.itemView.nazwisko.text = list[x].nazwisko
                        holder.itemView.nr.text = list[x].nr.toString()
                    }
                }
            }
        }
        //TO DO: klikając ten item wyślij id_zajec i id_ucznia do ocenyfragment
    }

    override fun getItemCount(): Int {
        return listaUczniow.size
    }

    fun setData(uczenZajecia: List<UczenZajecia>){
        this.listaUczniow = uczenZajecia
        notifyDataSetChanged()
    }
}