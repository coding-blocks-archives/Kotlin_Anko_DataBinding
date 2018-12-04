package com.codingblocks.kotlinobservables.viewextensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.codingblocks.kotlinobservables.observables.StandardObservableProperty

@Suppress("NOTHING_TO_INLINE")
inline fun EditText.bindString(bond: StandardObservableProperty<String>) {
    setText(bond.value)

    addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (bond.value != s.toString()) {
                bond.value = s.toString()
                setSelection(start+count)
            }
        }

    })
    bond.addValueChangeListener {
        setText(it)
    }
}