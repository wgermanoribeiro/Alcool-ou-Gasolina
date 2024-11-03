package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun EntendaSobreOCalculoScreen(navController: NavController) {
	val scrollState = rememberScrollState()
	
	Column(
		Modifier
			.background(color = Color(222, 222, 222, 255))
			.fillMaxSize()
			.verticalScroll(scrollState),
		//verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	){
		IconButton(
			onClick = { navController.popBackStack() }, // Navega de volta
			modifier = Modifier
				.align(Alignment.Start) // Alinha o ícone à esquerda
				.padding(8.dp) // Adiciona espaçamento
		) {
			Icon(
				imageVector = Icons.AutoMirrored.Filled.ArrowBack,
				contentDescription = "Voltar",
				tint = Color.DarkGray
			)
		}
		
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
			
			Text(
				text = "Quando vale a pena usar um combustível ou outro?",
				style = TextStyle(
					color = Color.DarkGray,
					fontSize = 22.sp,
					fontWeight = FontWeight.Bold,
				),
				modifier = Modifier
					.padding(10.dp),
				textAlign = TextAlign.Center
			)
			
			Text(
				text = "A principal diferença entre os dois combustíveis está na proporção entre o preço e o desempenho de cada um. Atualmente, o etanol vale a pena quando custar até 70% do valor da gasolina, considerando que há, no Brasil, uma adição de 27,5% de etanol anidro na composição da gasolina.\n" +
						"\n" +
						"Existe um cálculo básico para saber se o álcool é ou não mais recomendado que a gasolina para abastecer automóveis.\n" +
						"Para chegar à conclusão, basta dividir o preço do litro do álcool pelo preço do litro da gasolina. Se o resultado for menor ou igual a 0,7 (70%), o álcool é o recomendado para abastecer.\n" +
						"\n" +
						"Por outro lado, caso o valor seja maior que 0,7, é recomendado abastecer com gasolina.\n" +
						"\n" +
						"Suponha que o valor do álcool, em um determinado posto de gasolina, é de R\$3,29 o litro. O valor da gasolina, por sua vez, é de R\$4,60 o litro.\n" +
						"\n" +
						"Nesse caso, a conta vai ser a seguinte:\n" +
						"\n" +
						"3,29/4,60 = 0,71\n" +
						"\n" +
						"Ou seja, o valor do álcool (R\$3,29) dividido pelo valor da gasolina (R\$4,60), que teve como resultado 0,71. Dessa maneira, por ser um resultado maior que 0,7, compensa mais abastecer com gasolina.\n",
				
				style = TextStyle(
					color = Color.Black,
					fontSize = 14.sp,
				),
				textAlign = TextAlign.Justify,
				modifier = Modifier
					.padding(25.dp)
					.align(alignment = Alignment.CenterHorizontally)
					
			)
			
			val annotatedText = buildAnnotatedString {
				withStyle(
					style = SpanStyle(
						color = Color.Black,
						fontSize = 14.sp,
						fontWeight = FontWeight.Bold
					)
				) {
					append("Clique ")
				}
				
				// Adicione um link clicável à palavra "aqui"
				pushStringAnnotation(
					tag = "link",
					annotation = "https://meubolsoemdia.com.br/calculadora-alcool-gasolina"
				)
				withStyle(
					style = SpanStyle(
						color = Color.Blue,
						fontSize = 14.sp,
						fontWeight = FontWeight.Bold
					)
				) {
					append("aqui")
				}
				pop()
				
				withStyle(
					style = SpanStyle(
						color = Color.Black,
						fontSize = 14.sp,
						fontWeight = FontWeight.Bold
					)
				) {
					append(" e saiba mais.")
				}
			}
			
			val uriHandler = LocalUriHandler.current
			
			Text(
				text = annotatedText, // Use o AnnotatedString aqui
				textAlign = TextAlign.Right,
				modifier = Modifier
					.padding(horizontal = 0.dp, vertical = 50.dp)
					.align(alignment = Alignment.CenterHorizontally)
					.clickable { uriHandler.openUri("https://meubolsoemdia.com.br/calculadora-alcool-gasolina") }
			)
			
		}
		
	}
}