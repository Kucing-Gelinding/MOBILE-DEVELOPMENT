package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.marginEnd
import com.bangkit.cunny.R

class ButtonSignInCustomEdit @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val backgroundDrawable: Drawable =
        requireNotNull(ContextCompat.getDrawable(context, R.drawable.button_bg_welcome)) {
            "Background drawable not found!"
        }

    private val googleLogoDrawable: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.ic_google) // Ganti dengan nama logo Anda

    private val textColor: Int = ContextCompat.getColor(context, android.R.color.background_light)

    private val imageView: ImageView = ImageView(context).apply {
        setImageDrawable(googleLogoDrawable)
        layoutParams = LayoutParams(60, 60).apply {
            marginEnd = 16 // Spasi antara ikon dan teks
        }
    }

    private val textView: TextView = TextView(context).apply {
        text = "Sign in with Google"

        setTextColor(textColor)
        textSize = 14f
        isAllCaps = false
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    init {
        setupView()
    }

    private fun setupView() {
        // Set orientation horizontal
        orientation = HORIZONTAL

        // Center contents
        gravity = android.view.Gravity.CENTER

        // Set background
        background = backgroundDrawable

        // Add padding
        setPadding(32, 32, 32, 32)

        // Add views to LinearLayout
        addView(imageView)
        addView(textView)
    }
}