package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

object TextUtils {
	fun construirTextoResultado(ehGasolina: Boolean): AnnotatedString {
		return buildAnnotatedString {
			append("Abasteça com ")
			if (ehGasolina) {
				withStyle(
					style = SpanStyle(
						color = Color(0xFFE14169),
						fontWeight = FontWeight.Bold,
						shadow = Shadow(
							color = Color.DarkGray,
							offset = Offset(3f,3f),
							blurRadius = 3f
							)
					)
				) {
					append("Gasolina")
				}
			} else {
				withStyle(
					style = SpanStyle(
						color = Color(0xFF69E141),
						fontWeight = FontWeight.Bold,
						shadow = Shadow(
							color = Color.DarkGray,
							offset = Offset(3f, 3f),
							blurRadius = 3f
						)
					)
				) {
					append("Álcool")
				}
			}
		}
	}
}