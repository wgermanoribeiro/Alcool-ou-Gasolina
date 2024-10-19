package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PoliticaDePrivacidadeScreen() {
	Column(modifier = Modifier.padding(16.dp)) {
		Text("Nesta tela, você encontrará a política de privacidade do aplicativo.")
		// Adicione o texto da política de privacidade aqui
	}
}