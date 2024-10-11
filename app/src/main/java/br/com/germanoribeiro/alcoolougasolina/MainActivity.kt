package br.com.germanoribeiro.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
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
	
	var valorAlcool by remember { mutableStateOf("") }
	
	var valorGasolina by remember { mutableStateOf("") }
	
	var mostrarErroAlcool by remember { mutableStateOf(false) }
	
	var mostrarErroGasolina by remember { mutableStateOf(false) }
	
	val focusRequester = remember { FocusRequester() }
	
	var mostrarResultado by remember { mutableStateOf(false) }
	
	var ehGasolina by remember { mutableStateOf(false) }
	
	val focusManager = LocalFocusManager.current
	
	
	fun formatarValor(valor: String): String {
		val numeros = valor.filter { it.isDigit() }.take(3) // Filtra apenas números e limita a 3
		return when (numeros.length) {
			1 -> "${numeros[0]},"
			2 -> "${numeros[0]},${numeros[1]}"
			3 -> "${numeros[0]},${numeros[1]}${numeros[2]}"
			else -> ""
		}
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
				val resultado = if (ehGasolina) "Gasolina" else "Álcool"
				Text(
					text = resultado,
					style = TextStyle(
						color = if (ehGasolina) Color.Red else Color.Green,
						fontSize = 40.sp,
						fontWeight = FontWeight.Bold
					)
				)
			}


//			AnimatedVisibility(visible = mostrarResultado) {
//				if (mostrarResultado) {
//
//					val alcoolOuGasolina = if (ehGasolina) {
//						"Gasolina"
//					} else {
//						"Álcool"
//					}
//					val cor = if (ehGasolina) {
//						Color.Red
//					} else {
//						Color.Green
//					}
//					Text(
//						text = alcoolOuGasolina,
//						style = TextStyle(
//							color = cor,
//							fontSize = 40.sp,
//							fontWeight = FontWeight.Bold
//						)
//					)
//				}
//			}


//			TextField(
//				value = valorAlcool,
//				onValueChange = { newValue ->
//					valorAlcool = newValue // Remove a formatação aqui
//				},
//				label = { Text(text = "Digite aqui o valor do Álcool") },
//				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), // Altere o tipo de teclado
//				visualTransformation = CasasAposVirgula(2)
//			)

//			TextField(
//				value = valorGasolina,
//				onValueChange = { newValue ->
//					valorGasolina = newValue // Remove a formatação aqui
//				},
//				label = { Text(text = "Digite aqui o valor da Gasolina") },
//				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), // Altere o tipo de teclado
//				visualTransformation = CasasAposVirgula(2)
//			)
			
			
			TextField(
				value = valorAlcool,
				onValueChange = { novoValor ->
					valorAlcool = formatarValor(novoValor)
					mostrarErroAlcool = valorAlcool.length < 4 // Exibe erro se menos de 3 números
//					focusRequester.requestFocus()
				},
				label = { Text("Digite o valor do Álcool") },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				isError = mostrarErroAlcool,
				supportingText = {
					if (mostrarErroAlcool) {
						Text(
							"Preencha com 3 números",
							color = Color.Red
						)
					}
				},
//				modifier = Modifier.focusRequester(focusRequester),
			)
			
			
			TextField(
				value = valorGasolina,
				onValueChange = { novoValor ->
					valorGasolina = formatarValor(novoValor)
					mostrarErroGasolina =
						valorGasolina.length < 4 // Exibe erro se menos de 3 números
				},
				label = { Text("Digite o valor da Gasolina") },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
				isError = mostrarErroGasolina,
				supportingText = {
					if (mostrarErroGasolina) {
						Text(
							"Preencha com 3 números",
							color = Color.Red
						)
					}
				}
			)
			
			

//			Button(onClick = {
//				try {
//					if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
//						ehGasolina = valorAlcool.toDouble() / valorGasolina.toDouble() > 0.7
//						mostrarResultado = true
//					}
//				} catch (e: NumberFormatException) {
//					// Exiba uma mensagem de erro para o usuário
//				}
//			}) {
//				Text("Calcular")
//			}
			
			Button(onClick = {
				try {
					if (valorAlcool.isNotBlank() && valorGasolina.isNotBlank()) {
						val valorAlcoolFormatado = valorAlcool.replace(",", ".").toDouble()
						val valorGasolinaFormatado = valorGasolina.replace(",", ".").toDouble()
						ehGasolina = valorAlcoolFormatado / valorGasolinaFormatado > 0.7
						mostrarResultado = true
					}
				} catch (e: NumberFormatException) {
					// Exiba uma mensagem de erro para o usuário (ex: Toast ou Snackbar)
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