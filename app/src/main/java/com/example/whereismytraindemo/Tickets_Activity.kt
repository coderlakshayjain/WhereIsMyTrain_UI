package com.example.whereismytraindemo

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.Toast
import com.example.whereismytraindemo.databinding.ActivityTicketsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Tickets_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityTicketsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarTickets)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_24)
        supportActionBar?.title= "TICKETS"


        binding.selectedJD.setOnClickListener {
            openDatePicker()
        }

        val dropdownItems = listOf("GN General Tickets", "TQ Tatkal", "LD Ladies")
        var selectedPosition = 0

        binding.selectedQuota.setOnClickListener {
            val popupWindow = ListPopupWindow(this)
            popupWindow.anchorView = binding.selectedQuota

            val adapter = object : ArrayAdapter<String>(
                this,
                R.layout.item_dropdown,
                dropdownItems
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = convertView ?: LayoutInflater.from(context)
                        .inflate(R.layout.item_dropdown, parent, false)
                    val textView = view.findViewById<TextView>(R.id.dropdownItemText)
                    textView.text = getItem(position)

                    if (position == selectedPosition) {
                        textView.setBackgroundColor(Color.parseColor("#D3D3D3"))
                        textView.setTextColor(Color.BLACK)
                    } else {
                        textView.setBackgroundColor(Color.WHITE)
                        textView.setTextColor(Color.GRAY)
                    }
                    return view
                }
            }

            popupWindow.setAdapter(adapter)
            popupWindow.setOnItemClickListener { _, _, position, _ ->
                selectedPosition = position
                val actualQuota = dropdownItems[position]

                binding.selectedQuota.text = actualQuota.substring(2, actualQuota.length)
                val initialS =actualQuota.substring(0,2)
                binding.intitals.visibility = View.VISIBLE
                binding.intitals.text = initialS
                popupWindow.dismiss()
                Toast.makeText(this, "Selected: ${dropdownItems[position]}", Toast.LENGTH_SHORT).show()
            }

            popupWindow.show()
        }


    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                calendar.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)

                binding.selectedJD.text = formattedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pnr_menu, menu)
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