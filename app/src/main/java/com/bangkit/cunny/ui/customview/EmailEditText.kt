package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.bangkit.cunny.R

class EmailEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private val emailIcon: Drawable = requireNotNull(
        ContextCompat.getDrawable(context, R.drawable.ic_email_dark)
    ) { "Email icon drawable not found!" }

    private val iconPadding = resources.getDimensionPixelSize(R.dimen.icon_padding)
    private val textPadding = resources.getDimensionPixelSize(R.dimen.text_padding)

    init {
        setupView()
        setupListeners()
    }

    private fun setupView() {
        // Set email-specific input type
        inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        // Add padding and email icon
        setPadding(textPadding, paddingTop, textPadding, paddingBottom)
        setCompoundDrawablesWithIntrinsicBounds(emailIcon, null, null, null)
        compoundDrawablePadding = iconPadding

        // Hint and text appearance
        setHintTextColor(ContextCompat.getColor(context, R.color.hint_color))
        hint = context.getString(R.string.enter_email)
        setTextAppearance(android.R.style.TextAppearance_Material_Body1)

        // Background styling
        background = ContextCompat.getDrawable(context, R.drawable.custom_edittext_background)
    }

    var isValid: Boolean = false
        private set

    private fun setupListeners() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isValid = !s.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(s).matches()
                error = if (isValid) null else context.getString(R.string.invalid_email)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}