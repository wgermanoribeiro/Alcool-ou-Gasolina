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
fun PoliticaDePrivacidadeScreen(navController: NavController) {
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
				contentDescription = "Voltar"
			)
		}
		
		Column (
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				text = "Política e Privacidade",
				style = TextStyle(
					color = Color.DarkGray,
					fontSize = 32.sp,
					fontWeight = FontWeight.Bold
				)
			)
			
//			Text(
//				text = "Quando vale a pena usar um combustível ou outro?",
//				style = TextStyle(
//					color = Color.DarkGray,
//					fontSize = 22.sp,
//					fontWeight = FontWeight.Bold,
//				),
//				modifier = Modifier
//					.padding(10.dp),
//				textAlign = TextAlign.Center
//			)
			
			Text(
				text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin egestas ligula ac ex fringilla, tincidunt volutpat ligula imperdiet. Mauris in enim quam. Pellentesque quis quam convallis, porttitor enim at, iaculis mauris. Curabitur dignissim nibh ac nibh ornare, eget euismod purus scelerisque. Morbi arcu massa, elementum at suscipit eget, iaculis venenatis sem. Morbi nec urna eu lectus pretium malesuada condimentum dapibus enim. Aenean ultrices turpis in varius aliquet. Nunc consectetur enim et laoreet euismod.\n" +
						"\n" +
						"Quisque et porttitor nibh. Nulla velit quam, cursus et neque sit amet, venenatis hendrerit orci. Nam elementum dui libero, tristique dapibus mi efficitur quis. Nunc luctus est vitae tortor viverra ullamcorper. Praesent iaculis libero quis ex viverra ornare. Suspendisse vel eros mauris. In non mi ipsum. Nulla egestas eget tortor et malesuada. Cras eget suscipit urna, et tempus lorem. Praesent nibh quam, pretium in interdum vitae, lobortis eu dui. Fusce mi urna, hendrerit id elit non, hendrerit elementum mauris. Praesent vitae mattis lorem, in rhoncus enim.\n" +
						"\n" +
						"Integer et dolor lobortis, porta eros ac, interdum odio. Fusce luctus id turpis ut dapibus. Quisque malesuada consequat arcu a cursus. Curabitur lobortis, arcu et eleifend semper, lectus elit porttitor magna, sed consequat nisl magna non eros. Nulla a ex nec tellus interdum lobortis. Nulla quis maximus nibh. Vestibulum cursus ac lectus nec vestibulum. Curabitur non venenatis diam, at pretium enim. Vivamus euismod, nisi luctus vehicula dictum, est turpis tincidunt tortor, ut efficitur dolor nisl vel tortor. Duis sodales eros et velit ullamcorper feugiat. Cras nec est at ex tempor commodo nec non dui. Nunc pulvinar elit vitae bibendum tincidunt. Fusce egestas a nibh quis varius.\n" +
						"\n" +
						"Duis viverra blandit commodo. Cras non justo lacus. Aenean pellentesque convallis mi ut vestibulum. Nulla euismod, est volutpat gravida accumsan, lacus elit consectetur felis, sed laoreet augue felis ut tellus. Mauris ac justo ut purus pulvinar accumsan et in dolor. Donec nec quam magna. Phasellus semper purus nec lectus malesuada euismod. Praesent facilisis molestie lacus non accumsan. Sed nisi nisi, convallis id dictum eget, mollis quis nulla. Aliquam quis mollis mi, id consequat felis. Integer sollicitudin auctor fermentum.\n" +
						"\n" +
						"Aenean fermentum viverra mattis. Sed ac neque eu urna pretium gravida vitae tempus ipsum. Aenean interdum faucibus nibh, nec dictum erat placerat sed. Ut eget magna ut lacus facilisis maximus ac id erat. Ut egestas ante lacus, eu egestas mauris elementum eget. Nam consequat finibus hendrerit. Vivamus lacinia, nisi id dapibus malesuada, arcu lacus gravida nulla, non mattis magna lacus sed tortor. In fringilla sed nisi at lacinia. Praesent faucibus auctor nulla. Aenean et diam rhoncus nunc dictum egestas vel non ipsum. Fusce eu lorem nibh. Etiam vel elit ex. Maecenas auctor rhoncus posuere. Morbi sed purus auctor, condimentum ante sed, elementum urna. Morbi eleifend cursus turpis, quis hendrerit ipsum rutrum in.",
				
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
					.padding(20.dp)
					.align(alignment = Alignment.CenterHorizontally)
					.clickable { uriHandler.openUri("https://meubolsoemdia.com.br/calculadora-alcool-gasolina") }
			)
			
		}
		
	}
}