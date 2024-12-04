package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bangkit.cunny.R

class ButtonLoginCustomEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val backgroundDrawable: Drawable =
        requireNotNull(ContextCompat.getDrawable(context, R.drawable.button_bg_welcome)) {
            "Background drawable not found!"
        }

    private val textColor: Int = ContextCompat.getColor(context, android.R.color.background_light)

    private val textView: TextView = TextView(context).apply {
        text = "Login" // Change this to the appropriate text
        setTextColor(textColor)
        textSize = 14f
        isAllCaps = false
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    init {
        setupView()
    }

    private fun setupView() {
        // Set orientation to horizontal if you want consistent layout
        orientation = HORIZONTAL

        // Center contents
        gravity = Gravity.CENTER

        // Set background
        background = backgroundDrawable

        // Add padding
        setPadding(32, 32, 32, 32)

        // Add views to LinearLayout
        addView(textView)
    }
}
