package br.com.germanoribeiro.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import br.com.germanoribeiro.alcoolougasolina.ui.theme.AlcoolOuGasolinaTheme
import kotlinx.coroutines.launch

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
	
	var valorAlcool by remember { mutableStateOf(TextFieldValue("", TextRange(1))) }
	var valorGasolina by remember { mutableStateOf(TextFieldValue("", TextRange(1))) }
	var mostrarErroAlcool by remember { mutableStateOf(false) }
	var mostrarErroGasolina by remember { mutableStateOf(false) }
	var mostrarResultado by remember { mutableStateOf(false) }
	var ehGasolina by remember { mutableStateOf(false) }
	val focusManager = LocalFocusManager.current
	val snackbarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()
	
	fun formatarValor(valor: String): String {
		val numeros = valor.filter { it.isDigit() }.take(3) // Filtra apenas números e limita a 3
		return when {
			numeros.isEmpty() -> "" // Permite apagar todos os caracteres
			numeros.length == 1 -> "${numeros[0]},"
			numeros.length == 2 -> "${numeros[0]},${numeros[1]}"
			else -> "${numeros[0]},${numeros[1]}${numeros[2]}"
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
			
			TextField(
				value = valorAlcool,
				onValueChange = { novoValor ->
					// Permite apagar todos os caracteres
					if (novoValor.text.isEmpty()) {
						valorAlcool = TextFieldValue("", TextRange(0))
					} else {
						// Formata o valor se não estiver vazio e não for apenas a vírgula
						val valorFormatado = formatarValor(novoValor.text.filter { it.isDigit() })
						if (valorFormatado != valorAlcool.text) {
							valorAlcool =
								TextFieldValue(valorFormatado, TextRange(valorFormatado.length))
						} else {
							// Mantém o valor atual se a formatação não mudar
							valorAlcool = novoValor
						}
					}
					mostrarErroAlcool =
						valorAlcool.text.length < 4 // Exibe erro se menos de 4 caracteres (3 números e a virgula. ex.: 3,79)
				},
				label = { Text("Digite o valor do Álcool") },
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Number,
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
				isError = mostrarErroAlcool,
				supportingText = {
					if (mostrarErroAlcool) {
						Text(
							"Preencha com 3 dígitos para um cálculo mais preciso",
							color = Color.Red
						)
					}
				},
			)
			
			LaunchedEffect(valorAlcool) {
				if (valorAlcool.text.isNotEmpty()) {
					valorAlcool = valorAlcool.copy(selection = TextRange(valorAlcool.text.length))
				}
			}
			
			TextField(
				value = valorGasolina,
				onValueChange = { novoValor ->
					// Permite apagar todos os caracteres
					if (novoValor.text.isEmpty()) {
						valorGasolina = TextFieldValue("", TextRange(0))
					} else {
						// Formata o valor se não estiver vazio e não for apenas a vírgula
						val valorFormatado = formatarValor(novoValor.text.filter { it.isDigit() })
						if (valorFormatado != valorGasolina.text) {
							valorGasolina =
								TextFieldValue(valorFormatado, TextRange(valorFormatado.length))
						} else {
							// Mantém o valor atual se a formatação não mudar
							valorGasolina = novoValor
						}
					}
					
					mostrarErroGasolina =
						valorGasolina.text.length < 4 // Exibe erro se menos de 4 caracteres (3 números e a virgula. ex.: 5,69)
				},
				label = { Text("Digite o valor da Gasolina") },
				keyboardOptions = KeyboardOptions(
					keyboardType = KeyboardType.Number,
					imeAction = ImeAction.Done
				),
				keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
				isError = mostrarErroGasolina,
				supportingText = {
					if (mostrarErroGasolina) {
						Text(
							"Preencha com 3 dígitos para um cálculo mais preciso",
							color = Color.Red
						)
					}
				}
			)
			
			LaunchedEffect(valorGasolina) { // Executa após a atualização do valor
				if (valorGasolina.text.isNotEmpty()) {
					valorGasolina =
						valorGasolina.copy(selection = TextRange(valorGasolina.text.length))
				}
			}
			
//			Button(onClick = {
//				try {
//					// Força uma NumberFormatException para teste
//					"".toDouble()
//				} catch (e: NumberFormatException) {
//					scope.launch {
//						snackbarHostState.showSnackbar(
//							message = "Erro: Digite valores válidos.",
//							duration = SnackbarDuration.Short
//						)
//					}
//				}
//			}) {
//				Text("Testar Erro")
//			}
			
			Button(onClick = {
				try {
					if (valorAlcool.text.isNotBlank() && valorGasolina.text.isNotBlank()) {
						val valorAlcoolFormatado = valorAlcool.text.replace(",", ".").toDouble()
						val valorGasolinaFormatado = valorGasolina.text.replace(",", ".").toDouble()
						ehGasolina = valorAlcoolFormatado / valorGasolinaFormatado > 0.7
						mostrarResultado = true
					}
				} catch (e: NumberFormatException) {
					scope.launch {
						snackbarHostState.showSnackbar(
							message = "Erro: Digite valores válidos.",
							duration = SnackbarDuration.Short
						)
					}
				}
			}) {
				Text("Calcular")
			}
			SnackbarHost (hostState = snackbarHostState)
			
			Button(onClick = {
				// Reseta as variáveis de estado
				valorAlcool = TextFieldValue("", TextRange(1))
				valorGasolina = TextFieldValue("", TextRange(1))
				mostrarErroAlcool = false
				mostrarErroGasolina = false
				mostrarResultado = false
				ehGasolina = false
				// Limpa o foco dos campos de texto
				focusManager.clearFocus()
			}) {
				Text("Novo Cálculo")
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