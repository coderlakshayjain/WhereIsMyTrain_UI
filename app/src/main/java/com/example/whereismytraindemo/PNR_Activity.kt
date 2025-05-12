package com.example.whereismytraindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.whereismytraindemo.databinding.ActivityPnrBinding

class PNR_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityPnrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPnrBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolbar = binding.toolbarPNR
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_24)
        supportActionBar?.title = "PNR"


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pnr_menu, menu) // Inflate the menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

           R.id.Language->{
               Toast.makeText(this, "Language Clicked", Toast.LENGTH_SHORT).show()
               true
           }

            else -> super.onOptionsItemSelected(item)
        }
    }

}