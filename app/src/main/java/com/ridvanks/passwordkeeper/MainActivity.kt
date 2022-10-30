package com.ridvanks.passwordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.sifre_ekleme, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sifre_ekleme_butonu){
            val action = listeFragmentDirections.actionListeFragmentToSifrelerFragment("menudengeldim", 0)
            Navigation.findNavController(this, R.id.fragmentContainerView).navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }
}