package br.com.germanoribeiro.alcoolougasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
	
	var valorAlcool by remember { mutableStateOf(TextFieldValue("", TextRange(0))) }
	var valorGasolina by remember { mutableStateOf(TextFieldValue("", TextRange(0))) }
	var mostrarErroAlcool by remember { mutableStateOf(false) }
	var mostrarErroGasolina by remember { mutableStateOf(false) }
	var mostrarResultado by remember { mutableStateOf(false) }
	var ehGasolina by remember { mutableStateOf(false) }
	val focusManager = LocalFocusManager.current
	val snackbarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val keyboardController = LocalSoftwareKeyboardController.current
	var textoResultado by remember { mutableStateOf("") } // Variável para o texto do resultado
	
	fun formatarValor(valor: String): String {
		val numeros = valor.filter { it.isDigit() }.take(3) // Filtra apenas números e limita a 3
		return when {
			numeros.isEmpty() -> "" // Permite apagar todos os caracteres
			numeros.length == 1 -> "${numeros[0]},"
			numeros.length == 2 -> "${numeros[0]},${numeros[1]}"
			else -> "${numeros[0]},${numeros[1]}${numeros[2]}"
		}
	}
	
	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = {
			MenuLateral { menuItem ->
				// Lógica para lidar com o clique no item do menu
				when (menuItem.title) {
					"Home" -> { /* Ação para a tela inicial */ }
					"Configurações" -> { /* Ação para a tela de configurações */ }
					"Sobre" -> { /* Ação para a tela sobre */ }
				}
				// Fecha o menu após o clique
				scope.launch { drawerState.close() }
			}
		},
		
		content = {
		
			Column(
				Modifier
					.background(color = Color(222, 222, 222, 255))
					.fillMaxSize()
					.pointerInput(Unit) { // Substitua clickable por pointerInput
						detectTapGestures(onTap = {
							KeyboardUtils.FecharTeclado(keyboardController, focusManager)
						})
					},
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
						modifier = Modifier.onFocusChanged { focusState ->
							if (!focusState.isFocused) {
								keyboardController?.hide()
							}
						}
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
						},
						modifier = Modifier.onFocusChanged { focusState ->
							if (!focusState.isFocused) {
								keyboardController?.hide()
							}
						}
					)
					
					LaunchedEffect(valorGasolina) { // Executa após a atualização do valor
						if (valorGasolina.text.isNotEmpty()) {
							valorGasolina =	valorGasolina.copy(selection = TextRange(valorGasolina.text.length))
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
						KeyboardUtils.FecharTeclado(keyboardController, focusManager)
						try {
							if (valorAlcool.text.isNotBlank() && valorGasolina.text.isNotBlank()) {
								val valorAlcoolFormatado = valorAlcool.text.replace(",", ".").toDouble()
								val valorGasolinaFormatado = valorGasolina.text.replace(",", ".").toDouble()
								ehGasolina = valorAlcoolFormatado / valorGasolinaFormatado > 0.7
								mostrarResultado = true
								val porcentagem = (valorAlcoolFormatado / valorGasolinaFormatado * 100).toInt()
								textoResultado = "O valor do Álcool está $porcentagem% do valor da Gasolina"
							}
						} catch (e: NumberFormatException) {
							scope.launch {
								snackbarHostState.showSnackbar(
									message = "Erro: Digite valores válidos.",
									duration = SnackbarDuration.Short
								)
							}
						}
					}
					
					) {
						Text("Calcular")
					}
					
					Button(onClick = {
						KeyboardUtils.FecharTeclado(keyboardController, focusManager)
						// Reseta as variáveis de estado
						valorAlcool = TextFieldValue("", TextRange(1))
						valorGasolina = TextFieldValue("", TextRange(1))
						mostrarErroAlcool = false
						mostrarErroGasolina = false
						mostrarResultado = false
						ehGasolina = false
						textoResultado = ""
						// Limpa o foco dos campos de texto
						focusManager.clearFocus()
					}) {
						Text("Novo Cálculo")
					}
					Text(text = textoResultado) // Exibir o texto do resultado
					SnackbarHost (hostState = snackbarHostState)
				}
			}
			
			Box(modifier = Modifier.fillMaxSize()) {
				Button(
					onClick = {
						KeyboardUtils.FecharTeclado(keyboardController, focusManager)
						scope.launch { drawerState.open() }
							  },
					modifier = Modifier
						.wrapContentSize() // Permite que o botão ocupe apenas o espaço necessário
						.align(Alignment.TopStart) // Alinha o botão no topo à esquerda
						.padding(20.dp) // Adiciona espaçamento ao redor do botão
						.background(Color.Blue, shape = CircleShape) // Fundo azul e forma circular
						.border(
							1.dp,
							Color.DarkGray,
							shape = CircleShape
						) // Borda branca e forma circular
						.clip(CircleShape) // Recorta o conteúdo do botão em um círculo
						.shadow(4.dp, shape = CircleShape) // Adiciona uma sombra circular
				) {
					Text("Menu")
				}
			}
		}
	)
}

@Preview
@Composable
fun AppPreview() {
	AlcoolOuGasolinaTheme {
		App()
	}
}