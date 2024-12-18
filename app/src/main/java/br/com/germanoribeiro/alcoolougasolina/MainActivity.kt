package br.com.germanoribeiro.alcoolougasolina

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.germanoribeiro.alcoolougasolina.TextUtils.construirTextoResultado
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

@SuppressLint("DefaultLocale")
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
	val navController = rememberNavController()
	val scrollState = rememberScrollState()
//	val image = painterResource(id = R.drawable.combustivel2)
	
	fun formatarValor(valor: String): String {
		val numeros = valor.filter { it.isDigit() }.take(3) // Filtra apenas números e limita a 3
		return when {
			numeros.isEmpty() -> "" // Permite apagar todos os caracteres
			numeros.length == 1 -> "${numeros[0]},"
			numeros.length == 2 -> "${numeros[0]},${numeros[1]}"
			else -> "${numeros[0]},${numeros[1]}${numeros[2]}"
		}
	}
	
	@Composable
	fun HomeScreen(navController: NavController) {
		ModalNavigationDrawer(
			drawerState = drawerState,
			drawerContent = {
				MenuLateral { menuItem ->
					// Lógica para lidar com o clique no item do menu
					when (menuItem.title) {
						"Home" -> { /* Ação para a tela inicial */
						}
						
						"Entenda sobre o cálculo" -> {
							navController.navigate("entenda_sobre_o_calculo")
						}
						
						"Avalie-nos" -> { /* Ação para a tela*/
						}
						
						"Política de privacidade" -> {
							navController.navigate("politica_de_privacidade")
						}
						
						"Compartilhe esse aplicativo" -> { /* Ação para a tela*/
						}
					}
					scope.launch { drawerState.close() } // Fecha o menu após o clique
				}
			},
			
			content = {
				
				Column(
					Modifier
						.background(color = Color(222, 222, 222, 255))
						.fillMaxSize()
						.verticalScroll(scrollState)
						.pointerInput(Unit) {
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
								fontSize = 36.sp,
								fontWeight = FontWeight.Bold
							)
						)
						
						AnimatedVisibility(visible = mostrarResultado) {
							Text(
								text = construirTextoResultado(ehGasolina), // Chamar a função para construir o texto
								style = TextStyle(
									color = Color.DarkGray, // Cor padrão do texto
									fontSize = 30.sp,
									fontWeight = FontWeight.Bold
								)
							)
						}
						
						//OutlinedTextField(value = , onValueChange = )
						
						OutlinedTextField(
							textStyle = TextStyle(
								fontSize = 20.sp,
								color = Color.Black
							),
							value = valorAlcool,
							onValueChange = { novoValor ->
								// Permite apagar todos os caracteres
								if (novoValor.text.isEmpty()) {
									valorAlcool = TextFieldValue("", TextRange(0))
								} else {
									// Formata o valor se não estiver vazio e não for apenas a vírgula
									val valorFormatado =
										formatarValor(novoValor.text.filter { it.isDigit() })
									if (valorFormatado != valorAlcool.text) {
										valorAlcool =
											TextFieldValue(
												valorFormatado,
												TextRange(valorFormatado.length)
											)
									} else {
										// Mantém o valor atual se a formatação não mudar
										valorAlcool = novoValor
									}
								}
								mostrarErroAlcool =
									valorAlcool.text.length < 4 // Exibe erro se menos de 4 caracteres (3 números e a virgula. ex.: 3,79)
							},
							label = {
								Text(
									text = "Digite o valor do Álcool",
									style = TextStyle(
										fontWeight = FontWeight.Bold,
										fontSize = 16.sp
									)
								
								)
							},
							keyboardOptions = KeyboardOptions(
								keyboardType = KeyboardType.Number,
								imeAction = ImeAction.Done
							),
							keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
							isError = mostrarErroAlcool,
							supportingText = {
								if (mostrarErroAlcool) {
									Text(
										text = "Preencha com 3 dígitos para um cálculo mais preciso.",
										color = Color.Red,
										fontSize = 14.sp
									)
								}
							},
							modifier = Modifier
								.onFocusChanged { focusState ->
									if (!focusState.isFocused) {
										keyboardController?.hide()
									}
								},
							//shape = RoundedCornerShape(25.dp),
							colors = OutlinedTextFieldDefaults.colors(
								focusedContainerColor = Color.White,
								unfocusedContainerColor = Color.White,
								focusedBorderColor = Color.DarkGray,
								unfocusedBorderColor = Color.DarkGray,
								errorContainerColor = Color.White,
								focusedLabelColor = Color.Blue,
								unfocusedLabelColor = Color.DarkGray,
								cursorColor = Color.DarkGray,
								errorLabelColor = Color.Red,
								errorBorderColor = Color.Red,
							),
						)
						
						LaunchedEffect(valorAlcool) {
							if (valorAlcool.text.isNotEmpty()) {
								valorAlcool =
									valorAlcool.copy(selection = TextRange(valorAlcool.text.length))
							}
						}
						
						OutlinedTextField(
							textStyle = TextStyle(
								fontSize = 20.sp,
								color = Color.Black
							),
							value = valorGasolina,
							onValueChange = { novoValor ->
								// Permite apagar todos os caracteres
								if (novoValor.text.isEmpty()) {
									valorGasolina = TextFieldValue("", TextRange(0))
								} else {
									// Formata o valor se não estiver vazio e não for apenas a vírgula
									val valorFormatado =
										formatarValor(novoValor.text.filter { it.isDigit() })
									if (valorFormatado != valorGasolina.text) {
										valorGasolina =
											TextFieldValue(
												valorFormatado,
												TextRange(valorFormatado.length)
											)
									} else {
										// Mantém o valor atual se a formatação não mudar
										valorGasolina = novoValor
									}
								}
								mostrarErroGasolina =
									valorGasolina.text.length < 4 // Exibe erro se menos de 4 caracteres (3 números e a virgula. ex.: 5,69)
							},
							label = {
								Text(
									text = "Digite o valor da Gasolina",
									style = TextStyle(
										fontWeight = FontWeight.Bold,
										fontSize = 16.sp
									)
								
								)
							},
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
							modifier = Modifier
								.onFocusChanged { focusState ->
									if (!focusState.isFocused) {
										keyboardController?.hide()
									}
								},
							//shape = RoundedCornerShape(25.dp),
							colors = OutlinedTextFieldDefaults.colors(
								focusedContainerColor = Color.White,
								unfocusedContainerColor = Color.White,
								focusedBorderColor = Color.DarkGray,
								unfocusedBorderColor = Color.DarkGray,
								errorContainerColor = Color.White,
								focusedLabelColor = Color.Blue,
								unfocusedLabelColor = Color.DarkGray,
								cursorColor = Color.DarkGray,
								errorLabelColor = Color.Red,
								errorBorderColor = Color.Red,
							),
							
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
						
						Button(
							onClick = {
								KeyboardUtils.FecharTeclado(keyboardController, focusManager)
								try {
									if (valorAlcool.text.isNotBlank() && valorGasolina.text.isNotBlank()) {
										val valorAlcoolFormatado =
											valorAlcool.text.replace(",", ".").toDouble()
										val valorGasolinaFormatado =
											valorGasolina.text.replace(",", ".").toDouble()
										ehGasolina =
											valorAlcoolFormatado / valorGasolinaFormatado > 0.7
										mostrarResultado = true
										val porcentagem =
											(valorAlcoolFormatado / valorGasolinaFormatado * 100)
										textoResultado = "O valor do Álcool está ${
											String.format(
												"%.2f",
												porcentagem
											)
										}% do valor da Gasolina" // Formata o valor da val porcentagem para mostrar 2 digitos após a virgula
									}
								} catch (e: NumberFormatException) {
									scope.launch {
										snackbarHostState.showSnackbar(
											message = "Erro: Digite valores válidos.",
											duration = SnackbarDuration.Short
										)
									}
								}
							},
							colors = ButtonDefaults.buttonColors(
								containerColor = Color(0xFF4169E1)
							),
							border = BorderStroke(width = 1.dp, color = Color.DarkGray)
						
						) {
							Text(
								text = "Calcular",
								style = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 18.sp,
									color = Color.White
								)
							)
						}
						
						Button(
							onClick = {
								KeyboardUtils.FecharTeclado(
									keyboardController,
									focusManager
								) // Reseta as variáveis de estado
								valorAlcool = TextFieldValue("", TextRange(1))
								valorGasolina = TextFieldValue("", TextRange(1))
								mostrarErroAlcool = false
								mostrarErroGasolina = false
								mostrarResultado = false
								ehGasolina = false
								textoResultado = "" // Limpa o foco dos campos de texto
								focusManager.clearFocus()
							},
							colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4169E1)),
							border = BorderStroke(width = 1.dp, color = Color.DarkGray)
						) {
							Text(
								text = "Novo Cálculo",
								style = TextStyle(
									fontWeight = FontWeight.Bold,
									fontSize = 18.sp,
									color = Color.White
								)
							)
						}
						
						Spacer(modifier = Modifier.size(10.dp))
						
						Text(
							text = textoResultado, // Exibir o texto do resultado
							style = TextStyle(
								color = Color.Black,
								fontSize = 15.sp
							)
						)
						SnackbarHost(hostState = snackbarHostState)
					}

//					Box(
//						modifier = Modifier
//							.align(Alignment.End)
//							.padding(1.dp)
//					)
//					{
//						Image(
//							painter = image,
//							contentDescription = "Imagem de fundo",
//							modifier = Modifier
//								.padding(1.dp)
//								.size(width = 100.dp, height = 100.dp)
//						)
//					}
					
					
				}
				
				Box(modifier = Modifier.fillMaxSize()) {
					IconButton(
						onClick = {
							KeyboardUtils.FecharTeclado(keyboardController, focusManager)
							scope.launch { drawerState.open() }
						},
						modifier = Modifier
							.align(Alignment.TopStart)
							.padding(25.dp)
							.size(50.dp)
//							.border(1.dp,Color.DarkGray,shape = CircleShape)
//							.background(Color.White,shape = CircleShape)
					
					) {
						Text("")
						Icon(
							imageVector = Icons.Default.Menu,
							contentDescription = "Menu",
							tint = Color.DarkGray
						)
					}
				}
			}
		)
	}
	
	NavHost(navController = navController, startDestination = "home") {
		composable("home") { HomeScreen(navController) } // Tela principal
		composable("entenda_sobre_o_calculo") { EntendaSobreOCalculoScreen(navController) } // Tela EntendaSobreOCalculoScreen.kt
		composable("politica_de_privacidade") { PoliticaDePrivacidadeScreen(navController) } // Tela PoliticaDePrivacidadeScreen.kt
	}
}

@Preview
@Composable
fun AppPreview() {
	AlcoolOuGasolinaTheme {
		App()
	}
}