package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EntendaSobreOCalculoScreen() {
	Column(
		Modifier
			.background(color = Color(222, 222, 222, 255))
			.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	){
		Column (
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = "Álcool ou Gasolina?",
				style = TextStyle(
					color = Color.DarkGray,
					fontSize = 32.sp,
					fontWeight = FontWeight.Bold
				)
			)
			
			Text("Nesta tela, você encontrará uma explicação detalhada sobre como o cálculo do aplicativo é realizado.")
			
		}
		
	}
}