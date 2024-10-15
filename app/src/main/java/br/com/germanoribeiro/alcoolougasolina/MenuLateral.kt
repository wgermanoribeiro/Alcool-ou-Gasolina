package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

// Dados para os itens do menu
data class MenuItem(
	val title: String, val icon: ImageVector,
	val contentDescription: String,
	val onClick: () -> Unit
)

@Composable
fun MenuLateral(
	onItemClick: (MenuItem) -> Unit
) {
	// Itens do menu
	val menuItems = listOf(
		MenuItem(
			title = "Home",
			icon = Icons.Default.Home,
			contentDescription = "Tela inicial",
			onClick = { /* Ação para a tela inicial */ }
		),
		MenuItem(
			title = "Configurações",
			icon = Icons.Default.Settings,
			contentDescription = "Tela de configurações",
			onClick = { /* Ação para a tela de configurações */ }
		),
		MenuItem(
			title = "Sobre",
			icon = Icons.Default.Info,
			contentDescription = "Tela sobre o aplicativo",
			onClick = { /* Ação para a tela sobre */ }
		)
	)
	
	// DrawerContent
	ModalDrawerSheet {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			// Cabeçalho do menu (opcional)
			Text(
				text = "Menu Lateral",
				style = MaterialTheme.typography.headlineMedium
			)
			Spacer(modifier = Modifier.height(16.dp))
			
			// Itens do menu
			menuItems.forEach { item ->
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.clickable { onItemClick(item) }
						.padding(8.dp),
					verticalAlignment = Alignment.CenterVertically
				) {
					Icon(
						imageVector = item.icon,
						contentDescription = item.contentDescription
					)
					Spacer(modifier = Modifier.width(16.dp))
					Text(text = item.title)
				}
			}
		}
	}
}