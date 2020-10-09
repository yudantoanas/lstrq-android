package id.listrique.util

import android.widget.TextView

fun TextView.resetTextField() {
    this.apply {
        text = ""
        clearFocus()
    }
}