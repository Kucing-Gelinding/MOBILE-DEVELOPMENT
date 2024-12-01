package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.cunny.R

class ButtonWelcomeCustomEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    private val backgroundDrawable: Drawable =
        requireNotNull(ContextCompat.getDrawable(context, R.drawable.button_bg_welcome)) {
            "Background drawable not found!"
        }
    private val textColor: Int = ContextCompat.getColor(context, android.R.color.background_light)

    init {
        setupView()
    }

    private fun setupView() {
        // Set the button's background
        background = backgroundDrawable

        // Configure text appearance
        setTextColor(textColor)
        textSize = 14f // Set a clear and readable text size
        gravity = Gravity.CENTER // Center the text within the button
        isAllCaps = false // Disable all caps for the button text
    }

}