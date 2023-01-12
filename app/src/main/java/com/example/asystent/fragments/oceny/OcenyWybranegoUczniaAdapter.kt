package com.example.asystent.fragments.oceny

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.model.Oceny
import com.example.asystent.model.Uczen
import com.example.asystent.model.Zajecia
import kotlinx.android.synthetic.main.uczen_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//analogiczny adapter jak uczniowiezajeciaadapter wyswietli ocene jesli zgadza sie z id ucznia i z id zajec
class OcenyWybranegoUczniaAdapter(): RecyclerView.Adapter<OcenyWybranegoUczniaAdapter.MyViewHolder>() {
    private lateinit var appDatabase: AppDatabase
    private var listaOcen = emptyList<Oceny>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        appDatabase = AppDatabase.getDatabase(parent.context) //???
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.uczen_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listaOcen[position]
        runBlocking(Dispatchers.IO) {
            launch {
                var listaUczniow: List<Uczen> = appDatabase.uczenDao().wyswietlUczniow2()
                var listaZajec: List<Zajecia> = appDatabase.zajeciaDao().wyswietlZajecia2()
                for(x in listaUczniow.indices){
                    for(y in listaZajec.indices){
                        if(listaUczniow[x].id == currentItem.id_ucznia_ocena && listaZajec[y].id == currentItem.id_zajec_ocena){
                            holder.itemView.imie.text = currentItem.ocena.toString()
                            holder.itemView.nazwisko.text = ""
                            holder.itemView.nr.text = ""

                        }
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listaOcen.size
    }

    fun setData(ocena: List<Oceny>){
        this.listaOcen = ocena
        notifyDataSetChanged()
    }
}