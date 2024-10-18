package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

object TextUtils {
	fun construirTextoResultado(ehGasolina: Boolean): AnnotatedString {
		return buildAnnotatedString {
			append("Abasteça com ")
			if (ehGasolina) {
				withStyle(style = SpanStyle(color = Color.Red)) {
					append("Gasolina")
				}
			} else {
				withStyle(style = SpanStyle(color = Color.Green)) {
					append("Álcool")
				}
			}
		}
	}
}