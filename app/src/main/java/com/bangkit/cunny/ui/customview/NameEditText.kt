package com.bangkit.cunny.ui.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.bangkit.cunny.R

class NameEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private val nameIcon: Drawable = requireNotNull(
        ContextCompat.getDrawable(context, R.drawable.ic_person_dark)
    ) { "Name icon drawable not found!" }

    private val iconPadding = resources.getDimensionPixelSize(R.dimen.icon_padding)
    private val textPadding = resources.getDimensionPixelSize(R.dimen.text_padding)

    init {
        setupView()
        setupListeners()
    }

    private fun setupView() {
        inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_FLAG_CAP_WORDS

        setPadding(textPadding, paddingTop, textPadding, paddingBottom)
        setCompoundDrawablesWithIntrinsicBounds(nameIcon, null, null, null)
        compoundDrawablePadding = iconPadding

        setHintTextColor(ContextCompat.getColor(context, R.color.hint_color))
        hint = context.getString(R.string.enter_name)
        setTextAppearance(android.R.style.TextAppearance_Material_Body1)

        background = ContextCompat.getDrawable(context, R.drawable.custom_edittext_background)
    }

    private fun setupListeners() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No-op
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Tampilkan error jika teks kosong
                error = if (s.isNullOrEmpty()) {
                    context.getString(R.string.name_cannot_be_empty)
                } else if (!isValidName(s.toString())) {
                    context.getString(R.string.invalid_name)
                } else {
                    null
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No-op
            }
        })
    }

    private fun isValidName(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z\\s]+$"))
    }
}
