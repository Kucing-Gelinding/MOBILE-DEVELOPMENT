package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.bangkit.cunny.R

class PasswordEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs), View.OnTouchListener {

    private var isPasswordVisible = false
    private val eyeOpenIcon: Drawable = requireNotNull(
        ContextCompat.getDrawable(context, R.drawable.ic_remove_eye)
    ) { "Eye icon drawable not found!" }
    private val eyeClosedIcon: Drawable = requireNotNull(
        ContextCompat.getDrawable(context, R.drawable.ic_close)
    ) { "Eye closed icon drawable not found!" }
    private val personIcon: Drawable = requireNotNull(
        ContextCompat.getDrawable(context, R.drawable.ic_password)
    ) { "Person icon drawable not found!" }

    private val iconPadding = resources.getDimensionPixelSize(R.dimen.icon_padding)
    private val textPadding = resources.getDimensionPixelSize(R.dimen.text_padding)

    init {
        setupView()
        setupListeners()
    }

    private fun setupView() {
        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        setPadding(textPadding, paddingTop, textPadding, paddingBottom)
        setCompoundDrawablesWithIntrinsicBounds(personIcon, null, eyeOpenIcon, null)
        compoundDrawablePadding = iconPadding
        setHintTextColor(ContextCompat.getColor(context, R.color.hint_color))
        hint = context.getString(R.string.enter_password)

        // Apply modern style to text
        setTextAppearance(android.R.style.TextAppearance_Material_Body1)
        background = ContextCompat.getDrawable(context, R.drawable.custom_edittext_background)
    }

    var isValid: Boolean = false
        private set


    private fun setupListeners() {
        setOnTouchListener(this)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isValid = !s.isNullOrEmpty() && s.length >= 8
                error = if (isValid) null else context.getString(R.string.password_8digit)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        inputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        setSelection(text?.length ?: 0) // Maintain cursor position
        updateIcons()
    }

    private fun updateIcons() {
        val endIcon = if (isPasswordVisible) eyeClosedIcon else eyeOpenIcon
        setCompoundDrawablesWithIntrinsicBounds(personIcon, null, endIcon, null)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        val drawableEnd = compoundDrawables[2] ?: return false
        val drawableEndBoundary = width - paddingEnd - drawableEnd.intrinsicWidth

        if (event.x > drawableEndBoundary) {
            if (event.action == MotionEvent.ACTION_UP) {
                togglePasswordVisibility()
            }
            return true
        }
        return false
    }
}
