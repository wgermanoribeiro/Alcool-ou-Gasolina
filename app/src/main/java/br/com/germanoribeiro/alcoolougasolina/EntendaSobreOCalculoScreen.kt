package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EntendaSobreOCalculoScreen() {
	Column(modifier = Modifier.padding(16.dp)) {Text("Nesta tela, você encontrará uma explicação detalhada sobre como o cálculo do aplicativo é realizado.")
		// Adicione mais texto explicativo aqui
	}
}