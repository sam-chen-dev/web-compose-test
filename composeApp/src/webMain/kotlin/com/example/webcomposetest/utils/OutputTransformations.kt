package com.example.webcomposetest.utils

import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer

class PriceOutputTransformation : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        val newText = "$${originalText.toString().toDouble().showDecimal(2)}"

        replace(0, length, newText)
    }
}