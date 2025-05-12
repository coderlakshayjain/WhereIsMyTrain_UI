package com.example.whereismytraindemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog


class CustomBottomSheetFragment : BottomSheetDialogFragment() {

    private var scaleFactor = 1.0f // Scale factor to zoom the image
    private var lastTouchX = 0f // Last touch X position
    private var lastTouchY = 0f // Last touch Y position
    private var dX = 0f // Delta X for panning
    private var dY = 0f // Delta Y for panning
    private lateinit var scaleDetector: ScaleGestureDetector

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mapView = inflater.inflate(R.layout.maphelper_layout, container, false)

        val mapImageView = mapView.findViewById<ImageView>(R.id.mapImageView)
        val zoomInButton = mapView.findViewById<ImageButton>(R.id.zoomInButton)
        val zoomOutButton = mapView.findViewById<ImageButton>(R.id.zoomOutButton)

        mapImageView.setImageResource(R.drawable.mapmumbai)

        // Setup ScaleGestureDetector for pinch-to-zoom
        scaleDetector = ScaleGestureDetector(requireContext(), object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                detector?.let {
                    scaleFactor *= it.scaleFactor
                    scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)) // Prevents the image from getting too small or too large
                    mapImageView.scaleX = scaleFactor
                    mapImageView.scaleY = scaleFactor
                    return true
                }
                return false
            }
        })

        mapImageView.setOnTouchListener { v, event ->
            scaleDetector.onTouchEvent(event) // Handle scaling

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastTouchX = event.x
                    lastTouchY = event.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaX = event.x - lastTouchX
                    val deltaY = event.y - lastTouchY

                    // Calculate the new position by updating the translation
                    mapImageView.translationX += deltaX
                    mapImageView.translationY += deltaY

                    lastTouchX = event.x
                    lastTouchY = event.y
                }
            }
            true
        }

        zoomInButton.setOnClickListener {
            scaleFactor *= 1.1f // Increment zoom by 10%
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)) // Limit max zoom
            mapImageView.scaleX = scaleFactor
            mapImageView.scaleY = scaleFactor
        }

        zoomOutButton.setOnClickListener {
            scaleFactor *= 0.9f // Decrement zoom by 10%
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f)) // Limit min zoom
            mapImageView.scaleX = scaleFactor
            mapImageView.scaleY = scaleFactor
        }

        return mapView
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            val layoutParams = it.layoutParams
            layoutParams.height = (resources.displayMetrics.heightPixels * 0.80).toInt() // Limit the height to 60% of screen
            it.layoutParams = layoutParams
        }
    }
}
