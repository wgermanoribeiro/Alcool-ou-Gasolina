package br.com.germanoribeiro.alcoolougasolina

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.germanoribeiro.alcoolougasolina.ui.theme.AlcoolOuGasolinaTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AlcoolOuGasolinaTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					App()
				}
			}
		}
	}
}

@Composable
fun App() {
	
	var valorAlcool by remember {
		mutableStateOf("")
	}
	
	var valorGasolina by remember {
		mutableStateOf("")
	}
	
	var mostrarResultado by remember {
		mutableStateOf(false)
	}
	
	var ehGasolina by remember {
		mutableStateOf(false)
	}
	
	
	
	Column(
		Modifier
			.background(color = Color(222, 222, 222, 255))
			.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Column(
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
			
			
			AnimatedVisibility(visible = mostrarResultado) {
				if (mostrarResultado) {
					
					val alcoolOuGasolina = if (ehGasolina) {
						"Gasolina"
					} else {
						"Álcool"
					}
					val cor = if (ehGasolina) {
						Color.Red
					} else {
						Color.Green
					}
					Text(
						text = alcoolOuGasolina,
						style = TextStyle(
							color = cor,
							fontSize = 40.sp,
							fontWeight = FontWeight.Bold
						)
					)
				}
			}
			
			
			TextField(
				value = valorAlcool,
				onValueChange = { newValue ->
					val formattedValue = newValue.takeWhile { it != '.' }
						.plus(newValue.substringAfter('.', "").take(2))
					valorAlcool = formattedValue
				},
				label = { Text(text = "Digite aqui o valor do Álcool") },
			)
			
			
			TextField(
				value = valorGasolina,
				onValueChange = {
					valorGasolina = it
				},
				label = {
					Text(text = "Digite aqui o valor da Gasolina")
				}
			)
			
			Button(onClick = {
				if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
					ehGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7
					mostrarResultado = true
				}
			}) {
				Text("Calcular")
			}
		}
		
		
		
		
	}
}



@Preview
@Composable
fun AppPreview() {
	AlcoolOuGasolinaTheme {
		App()
	}
}