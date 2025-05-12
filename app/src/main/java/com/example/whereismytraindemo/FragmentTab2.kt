package com.example.whereismytraindemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class FragmentTab2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tab2, container, false)

        val click = view.findViewById<ImageView>(R.id.upIcon)

        click.setOnClickListener {
            val customBottomSheetFragment = CustomBottomSheetFragment()
            customBottomSheetFragment.show(childFragmentManager, customBottomSheetFragment.tag)
        }
        return view

    }

}
