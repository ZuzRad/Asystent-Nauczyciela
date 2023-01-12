package com.example.asystent.fragments.zajecia.wybrane_zajecia

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.AppDatabase
import com.example.asystent.fragments.oceny.OcenyWybranegoUczniaFragment
import com.example.asystent.model.Uczen
import com.example.asystent.model.UczenZajecia
import kotlinx.android.synthetic.main.uczen_row.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UczniowieWybranychZajecAdapter(): RecyclerView.Adapter<UczniowieWybranychZajecAdapter.MyViewHolder>() {
    private lateinit var appDatabase: AppDatabase
    private var listaUczniow = emptyList<UczenZajecia>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        appDatabase = AppDatabase.getDatabase(parent.context) //???
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.uczen_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
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
        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                var send_id = currentItem.id
                var send_id_zajec = currentItem.id_zajec
                var send_id_ucznia = currentItem.id_ucznia

                val bundle = Bundle()
                if(position != RecyclerView.NO_POSITION){
                    if (send_id != null) {
                        bundle.putInt("input_id", send_id)
                    }
                    if (send_id_zajec != null) {
                        bundle.putInt("input_id_zajec", send_id_zajec)
                    }
                    if (send_id_ucznia != null) {
                        bundle.putInt("input_id_ucznia", send_id_ucznia)
                    }
                }

                val fragment: Fragment = OcenyWybranegoUczniaFragment()
                fragment.arguments = bundle

                val activity = v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commit()

            }
        })
    }

    override fun getItemCount(): Int {
        return listaUczniow.size
    }

    fun setData(uczenZajecia: List<UczenZajecia>){
        this.listaUczniow = uczenZajecia
        notifyDataSetChanged()
    }
}