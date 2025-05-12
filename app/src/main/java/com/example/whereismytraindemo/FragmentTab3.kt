package com.example.whereismytraindemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragmentTab3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab3, container, false)

        val click = view.findViewById<ImageView>(R.id.upIcon)

        click.setOnClickListener {
            val customBottomSheetFragment = CustomBottomSheetFragment()
            customBottomSheetFragment.show(childFragmentManager, customBottomSheetFragment.tag)
        }
        return view
    }
}