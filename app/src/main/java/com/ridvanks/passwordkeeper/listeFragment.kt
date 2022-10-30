package com.ridvanks.passwordkeeper

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_liste.*
import java.lang.Exception

class listeFragment : Fragment() {

    var uygulamaAdiListesi = ArrayList<String>()
    var uygulamaIdListesi = ArrayList<Int>()
    private lateinit var listeAdapter : listeRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeAdapter = listeRecyclerAdapter(uygulamaAdiListesi, uygulamaIdListesi)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listeAdapter

        sqlVeriAlma()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste, container, false)
    }

    fun sqlVeriAlma(){

        try {

            activity?.let {
                var database = it.openOrCreateDatabase("Sifreler", Context.MODE_PRIVATE, null)
                val cursor = database.rawQuery("SELECT * FROM sifreler", null)
                val uygulamaAdiIndex = cursor.getColumnIndex("uygulamaadi")
                val uygulamaIdIndex = cursor.getColumnIndex("id")

                uygulamaAdiListesi.clear()
                uygulamaIdListesi.clear()

                while (cursor.moveToNext()){

                    uygulamaAdiListesi.add(cursor.getString(uygulamaAdiIndex))
                    uygulamaIdListesi.add(cursor.getInt(uygulamaIdIndex))
                }

                listeAdapter.notifyDataSetChanged()
                cursor.close()
            }


        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}