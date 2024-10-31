package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.background
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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
				text = "Política de Privacidade",
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
				text = "Esta política de privacidade se aplica ao aplicativo Álcool ou Gasolina (doravante denominado \"Aplicativo\") para dispositivos móveis que foi criado por Germano Ribeiro (doravante denominado \"Provedor de Serviços\") como um serviço Suportado por Anúncios. Este serviço é destinado ao uso \"NO ESTADO EM QUE SE ENCONTRA\".\n" +
						"\n" +
						"Coleta e Uso de Informações\n" +
						"\n" +
						"O Aplicativo coleta informações quando você o baixa e o usa. Essas informações podem incluir informações como\n" +
						"\n" +
						"O endereço de Protocolo de Internet do seu dispositivo (por exemplo, endereço IP)\n" +
						"As páginas do Aplicativo que você visita, a hora e a data da sua visita, o tempo gasto nessas páginas\n" +
						"O tempo gasto no Aplicativo\n" +
						"O sistema operacional que você usa no seu dispositivo móvel\n" +
						"\n" +
						"O Aplicativo coleta a localização do seu dispositivo, o que ajuda o Provedor de Serviços a determinar sua localização geográfica aproximada e fazer uso das seguintes maneiras:\n" +
						"\n" +
						"Serviços de Geolocalização: O Provedor de Serviços utiliza dados de localização para fornecer recursos como conteúdo personalizado, recomendações relevantes e serviços baseados em localização.\n" +
						"Análise e melhorias: dados de localização agregados e anônimos ajudam o Provedor de serviços a analisar o comportamento do usuário, identificar tendências e melhorar o desempenho geral e a funcionalidade do Aplicativo.\n" +
						"Serviços de terceiros: periodicamente, o Provedor de serviços pode transmitir dados de localização anônimos para serviços externos. Esses serviços os auxiliam a aprimorar o Aplicativo e otimizar suas ofertas.\n" +
						"\n" +
						"\n" +
						"O Provedor de serviços pode usar as informações que você forneceu para contatá-lo de tempos em tempos para fornecer informações importantes, avisos necessários e promoções de marketing.\n" +
						"\n" +
						"Para uma melhor experiência, ao usar o Aplicativo, o Provedor de serviços pode exigir que você nos forneça certas informações de identificação pessoal, incluindo, mas não se limitando a, informações financeiras, informações de ativos. As informações que o Provedor de serviços solicitar serão retidas por eles e usadas conforme descrito nesta política de privacidade.\n" +
						"\n" +
						"\n" +
						"Acesso de terceiros\n" +
						"\n" +
						"Somente dados agregados e anônimos são transmitidos periodicamente para serviços externos para ajudar o Provedor de serviços a melhorar o Aplicativo e seu serviço. O Provedor de serviços pode compartilhar suas informações com terceiros nas formas descritas nesta declaração de privacidade.\n" +
						"\n" +
						"Observe que o Aplicativo utiliza serviços de terceiros que têm sua própria Política de Privacidade sobre o tratamento de dados. Abaixo estão os links para a Política de Privacidade dos provedores de serviços de terceiros usados \u200B\u200Bpelo Aplicativo:\n" +
						"\n" +
						"Google Play Services\n" +
						"AdMob\n" +
						"Google Analytics para Firebase\n" +
						"Firebase Crashlytics\n" +
						"\n" +
						"O Provedor de Serviços pode divulgar Informações Fornecidas pelo Usuário e Coletadas Automaticamente:\n" +
						"\n" +
						"conforme exigido por lei, como para cumprir uma intimação ou processo legal semelhante;\n" +
						"quando eles acreditam de boa fé que a divulgação é necessária para proteger seus direitos, proteger sua segurança ou a segurança de outros, investigar fraudes ou responder a uma solicitação governamental;\n" +
						"com seus provedores de serviços confiáveis \u200B\u200Bque trabalham em seu nome, não têm um uso independente das informações que divulgamos a eles e concordaram em aderir às regras estabelecidas nesta declaração de privacidade.\n" +
						"\n" +
						"\n" +
						"Direitos de Opt-Out\n" +
						"\n" +
						"Você pode interromper toda a coleta de informações pelo Aplicativo facilmente desinstalando-o. Você pode usar os processos de desinstalação padrão, conforme podem estar disponíveis como parte do seu dispositivo móvel ou por meio do mercado ou rede de aplicativos móveis.\n" +
						"\n" +
						"\n" +
						"\n" +
						"Política de retenção de dados\n" +
						"\n" +
						"O Provedor de serviços reterá os dados fornecidos pelo usuário enquanto você usar o aplicativo e por um tempo razoável depois disso. Se você quiser que eles excluam os dados fornecidos pelo usuário que você forneceu por meio do aplicativo, entre em contato com eles em w.germanoribeiro@gmail.com e eles responderão em um tempo razoável.\n" +
						"\n" +
						"\n" +
						"\n" +
						"Crianças\n" +
						"\n" +
						"O Provedor de serviços não usa o aplicativo para solicitar dados ou fazer marketing para crianças menores de 13 anos.\n" +
						"\n" +
						"O aplicativo não se dirige a ninguém menor de 13 anos. O Provedor de serviços não coleta intencionalmente informações de identificação pessoal de crianças menores de 13 anos. No caso de o Provedor de serviços descobrir que uma criança menor de 13 anos forneceu informações pessoais, o Provedor de serviços as excluirá imediatamente de seus servidores. Se você for pai/mãe ou responsável e estiver ciente de que seu filho nos forneceu informações pessoais, entre em contato com o Provedor de serviços (w.germanoribeiro@gmail.com) para que eles possam tomar as medidas necessárias.\n" +
						"\n" +
						"\n" +
						"\n" +
						"Segurança\n" +
						"\n" +
						"O Provedor de Serviços está preocupado em proteger a confidencialidade de suas informações. O Provedor de Serviços fornece salvaguardas físicas, eletrônicas e processuais para proteger as informações que o Provedor de Serviços processa e mantém.\n" +
						"\n" +
						"\n" +
						"\n" +
						"Alterações\n" +
						"\n" +
						"Esta Política de Privacidade pode ser atualizada de tempos em tempos por qualquer motivo. O Provedor de Serviços notificará você sobre quaisquer alterações na Política de Privacidade atualizando esta página com a nova Política de Privacidade. É aconselhável que você consulte esta Política de Privacidade regularmente para quaisquer alterações, pois o uso contínuo é considerado aprovação\n" +
						"\n" +
						"\n" +
						"\n" +
						"Esta política de privacidade é efetiva a partir de 2024-10-01\n" +
						"\n" +
						"\n" +
						"\n" +
						"Seu Consentimento\n" +
						"\n" +
						"Ao usar o Aplicativo, você está consentindo com o processamento de suas informações conforme estabelecido nesta Política de Privacidade agora e conforme alterado por nós.\n" +
						"\n" +
						"\n" +
						"\n" +
						"Entre em contato conosco\n" +
						"\n" +
						"Se você tiver alguma dúvida sobre privacidade ao usar o Aplicativo, ou tiver dúvidas sobre as práticas, entre em contato com o Provedor de Serviços por e-mail em w.germanoribeiro@gmail.com.",
				style = TextStyle(
					color = Color.Black,
					fontSize = 14.sp,
				),
				textAlign = TextAlign.Justify,
				modifier = Modifier
					.padding(25.dp)
					.align(alignment = Alignment.CenterHorizontally)
			
			)
			
			
			TextButton(
				onClick = {
				
				},
				modifier = Modifier
					.padding(horizontal = 0.dp, vertical = 50.dp)
			)
			{
				Text(
					text = "Clique aqui para acessar a página da Política de Privacidade",
					color = Color.Black
				)
				
			}
			
		}
		
	}
}