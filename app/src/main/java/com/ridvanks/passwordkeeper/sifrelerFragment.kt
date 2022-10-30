package com.ridvanks.passwordkeeper

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_sifreler.*
import java.lang.Exception


class sifrelerFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sifreler, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        kaydet_button.setOnClickListener {
            kaydet(it)
        }

        arguments?.let {
            var gelenBilgi = sifrelerFragmentArgs.fromBundle(it).bilgi

            if (gelenBilgi.equals("menudengeldim")){

                uygulama_adi.setText("")
                kullanici_adi.setText("")
                kullanici_sifre.setText("")
                kaydet_button.visibility = View.VISIBLE
            }
            else{
                kaydet_button.visibility = View.INVISIBLE

                val secilenID = sifrelerFragmentArgs.fromBundle(it).id

                context?.let {
                    try {

                        val database = it.openOrCreateDatabase("Sifreler", Context.MODE_PRIVATE, null)
                        val cursor = database.rawQuery("SELECT * FROM sifreler WHERE id = ?", arrayOf(secilenID.toString()))

                        val uygulamaAdiIndex = cursor.getColumnIndex("uygulamaadi")
                        val kullaniciAdiIndex = cursor.getColumnIndex("kullaniciadi")
                        val kullaniciSifreIndex = cursor.getColumnIndex("kullanicisifre")

                        while (cursor.moveToNext()){

                            uygulama_adi.setText(cursor.getString(uygulamaAdiIndex))
                            kullanici_adi.setText(cursor.getString(kullaniciAdiIndex))
                            kullanici_sifre.setText(cursor.getString(kullaniciSifreIndex))

                        }

                        cursor.close()

                    }catch (e : Exception){
                        e.printStackTrace()
                    }
                }

            }
        }
    }

    fun kaydet(view: View){

        val uygulamaAdi= uygulama_adi.text.toString()
        val kullaniciAdi = kullanici_adi.text.toString()
        val kullaniciSifre = kullanici_sifre.text.toString()

        try {

            context?.let {
                val database = it.openOrCreateDatabase("Sifreler", Context.MODE_PRIVATE, null)
                database.execSQL("CREATE TABLE IF NOT EXISTS sifreler (id INTEGER PRIMARY KEY, uygulamaadi VARCHAR, kullaniciadi VARCHAR, kullanicisifre VARCHAR)")

                val sqlString = "INSERT INTO sifreler (uygulamaadi, kullaniciadi, kullanicisifre) VALUES (?, ?, ?)"
                val statement = database.compileStatement(sqlString)
                statement.bindString(1, uygulamaAdi)
                statement.bindString(2, kullaniciAdi)
                statement.bindString(3, kullaniciSifre)
                statement.execute()
            }

        }catch (e: Exception){
            e.printStackTrace()
        }

        val action = sifrelerFragmentDirections.actionSifrelerFragmentToListeFragment()
        Navigation.findNavController(view).navigate(action)

    }
}