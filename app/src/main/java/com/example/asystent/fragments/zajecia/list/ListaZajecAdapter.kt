package com.example.asystent.fragments.zajecia.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.fragments.zajecia.list.selected.WybraneZajeciaFragment
import com.example.asystent.model.Zajecia
import kotlinx.android.synthetic.main.zajecia_row.view.*

class ListaZajecAdapter(): RecyclerView.Adapter<ListaZajecAdapter.MyViewHolder>() {

    private var listaZajec = emptyList<Zajecia>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.zajecia_row, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentItem = listaZajec[position]
//        holder.itemView.text_id.text = currentItem.id.toString()
        holder.itemView.nazwa.text = currentItem.nazwa
        holder.itemView.dzien.text = currentItem.dzien
        holder.itemView.godzina.text = currentItem.godzina

        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                var send_id = currentItem.id
                var send_nazwa = currentItem.nazwa
                var send_dzien = currentItem.dzien
                var send_godzina = currentItem.godzina

                val bundle = Bundle()
                if(position != RecyclerView.NO_POSITION){
                    if (send_id != null) {
                        bundle.putInt("input_id", send_id)
                    }
                    bundle.putString("input_nazwa", send_nazwa)
                    bundle.putString("input_dzien", send_dzien)
                    bundle.putString("input_godzina", send_godzina)
                }

                val fragment: Fragment = WybraneZajeciaFragment()
                fragment.arguments = bundle


                val activity = v!!.context as AppCompatActivity
                activity.supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).addToBackStack(null).commit()


            }

        })

    }


    override fun getItemCount(): Int {
        return listaZajec.size
    }

    fun setData(zajecia: List<Zajecia>){
        this.listaZajec = zajecia
        notifyDataSetChanged()
    }
}