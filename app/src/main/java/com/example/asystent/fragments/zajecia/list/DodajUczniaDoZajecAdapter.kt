package com.example.asystent.fragments.zajecia.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.fragments.uczen.list.ListaUczniowAdapter
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import com.example.asystent.repository.selected.WybraneZajeciaFragment
import kotlinx.android.synthetic.main.uczen_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

//adapter do drugiego recycler view - miał w momencie kliknięcia elementu dodawać do tabeli id osoby i id zajeć (problem nie ma dostępu do id zajęć :c)
class DodajUczniaDoZajecAdapter: RecyclerView.Adapter<DodajUczniaDoZajecAdapter.MyViewHolder>() {
    private lateinit var appDatabase: AppDatabase
    private var listaUczniow = emptyList<Uczen>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        appDatabase = AppDatabase.getDatabase(parent.context) //???
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.uczen_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = listaUczniow[position]
//        holder.itemView.text_id.text = currentItem.id.toString()
        holder.itemView.imie.text = currentItem.imie
        holder.itemView.nazwisko.text = currentItem.nazwisko
        holder.itemView.nr.text = currentItem.nr.toString()
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                runBlocking(Dispatchers.IO) {
                    var list:List<Uczen> = appDatabase.uczenDao().wyswietlUczniow2()
                    var dane = UczenZajecia(0,0)
                    dane = UczenZajecia(list[position].id, 0)
                    appDatabase.uczenZajeciaDao().insert(dane)
//                                newlist.removeAt(position)
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return listaUczniow.size
    }

    fun setData(uczen: List<Uczen>){
        this.listaUczniow = uczen
        notifyDataSetChanged()
    }
}