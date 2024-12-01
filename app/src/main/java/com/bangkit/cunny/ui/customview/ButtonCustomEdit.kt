package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bangkit.cunny.R

class ButtonCustomEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatButton(context, attrs) {

    private val enabledBackground: Drawable =
        requireNotNull(ContextCompat.getDrawable(context, R.drawable.button_bg)) {
            "Enabled background drawable not found!"
        }
    private val disabledBackground: Drawable =
        requireNotNull(ContextCompat.getDrawable(context, R.drawable.button_bg_disabled)) {
            "Disabled background drawable not found!"
        }
    private val textColor: Int = ContextCompat.getColor(context, android.R.color.background_light)

    init {
        setupView()
    }

    private fun setupView() {
        // Initial configuration
        isAllCaps = false // Disable all caps for the button text
        textSize = 14f // Set a more readable default size
        gravity = Gravity.CENTER // Ensure content is centered
        updateState() // Set initial state based on `isEnabled`
    }

    private fun updateState() {
        // Update the background and text based on the enabled state
        background = if (isEnabled) enabledBackground else disabledBackground
        setTextColor(textColor)
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        updateState() // Reapply styles when the enabled state changes
    }

    fun updateButtonState(inputs: List<EditText>) {
        val isValid = inputs.all { input ->
            input.error == null && !input.text.isNullOrEmpty()
        }
        isEnabled = isValid
    }

}