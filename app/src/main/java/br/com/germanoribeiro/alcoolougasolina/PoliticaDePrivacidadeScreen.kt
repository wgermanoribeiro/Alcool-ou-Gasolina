package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PoliticaDePrivacidadeScreen(navController: NavController) {
	Column(
		Modifier
			.background(color = Color(222, 222, 222, 255))
			.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	){
		IconButton(
			onClick = { navController.popBackStack() }, // Navega de volta
			modifier = Modifier
				.align(Alignment.Start) // Alinha o ícone à esquerda
				.padding(8.dp) // Adiciona espaçamento
		) {
			Icon(
				imageVector = Icons.Filled.ArrowBack,
				contentDescription = "Voltar"
			)
		}
		
		Column (
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = "Política de Privacidade",
				style = TextStyle(
					color = Color.DarkGray,
					fontSize = 32.sp,
					fontWeight = FontWeight.Bold
				)
			)
			
			Text(
				text = "Nesta tela, você encontrará a política de privacidade do aplicativo."
			
			)
			
		}
		
	}
}