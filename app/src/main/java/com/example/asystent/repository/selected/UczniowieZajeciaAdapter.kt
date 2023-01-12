package com.example.asystent.repository.selected

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import androidx.fragment.app.Fragment
import com.example.asystent.data.AppDatabase
import com.example.asystent.fragments.uczen.list.ListaUczniowAdapter
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import com.example.asystent.model.Zajecia
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
    }

    override fun getItemCount(): Int {
        return listaUczniow.size
    }

    fun setData(uczenZajecia: List<UczenZajecia>){
        this.listaUczniow = uczenZajecia
        notifyDataSetChanged()
    }
}