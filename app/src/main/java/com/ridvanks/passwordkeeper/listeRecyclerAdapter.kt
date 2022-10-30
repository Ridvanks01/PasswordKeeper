package com.ridvanks.passwordkeeper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class listeRecyclerAdapter(val sifreListesi: ArrayList<String>, val idListesi: ArrayList<Int>) : RecyclerView.Adapter<listeRecyclerAdapter.SifreTutucu>() {

        class SifreTutucu(itemView: View) : RecyclerView.ViewHolder(itemView){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SifreTutucu {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row, parent,false)
        return SifreTutucu(view)
    }

    override fun onBindViewHolder(holder: SifreTutucu, position: Int) {
        holder.itemView.recycler_row_text.text = sifreListesi[position]
        holder.itemView.setOnClickListener(){

            val action = listeFragmentDirections.actionListeFragmentToSifrelerFragment("recyclerdangeldim", idListesi[position])
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return idListesi.size
    }
}