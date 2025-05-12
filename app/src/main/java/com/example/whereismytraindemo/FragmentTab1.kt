package com.example.whereismytraindemo

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.button.MaterialButton

class FragmentTab1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tab1, container, false)


        val pnrbtn = view.findViewById<Button>(R.id.btnPnr)
        val fromStation = view.findViewById<EditText>(R.id.FromStation)
        val toStation = view.findViewById<EditText>(R.id.ToStation)
        val upDownImage = view.findViewById<ImageView>(R.id.updown)

        val clearFrom = view.findViewById<ImageView>(R.id.clearFrom)
        val clearTo = view.findViewById<ImageView>(R.id.clearTo)

        clearFrom.clearTextInsidethis(fromStation)
        clearTo.clearTextInsidethis(toStation)

        //or
        //ClearActionUtil.setClearAction(clearFrom, fromStation)
        //        ClearActionUtil.setClearAction(clearTo, toStation)

        upDownImage.setOnClickListener {
            // Swap the text between FromStation and ToStation
            val tempText = fromStation.text.toString()
            fromStation.setText(toStation.text.toString())
            toStation.setText(tempText)

            // Rotate the updown ImageView by 360 degrees
            val rotateAnimator = ObjectAnimator.ofFloat(upDownImage, "rotation", 0f, 180f)
            rotateAnimator.duration = 500
            rotateAnimator.start()
        }


        pnrbtn.setOnClickListener { v->
            Intent(requireActivity(), PNR_Activity::class.java).also { startActivity(it) }
        }

        val tickets = view.findViewById<Button>(R.id.tickets)
        tickets.setOnClickListener{vi->
            Intent(requireActivity(), Tickets_Activity::class.java).also { startActivity(it) }
        }


        return view
    }



    fun ImageView.clearTextInsidethis(editText: EditText){
        this.setOnClickListener {
            editText.text.clear()
        }
    }

}