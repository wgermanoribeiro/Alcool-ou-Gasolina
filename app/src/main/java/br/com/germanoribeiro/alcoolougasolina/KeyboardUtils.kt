package br.com.germanoribeiro.alcoolougasolina

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController

object KeyboardUtils {
	fun FecharTeclado(keyboardController: SoftwareKeyboardController?, focusManager: FocusManager) {
		keyboardController?.hide()
		focusManager.clearFocus()
	}
	
	class LocalFocusManager {
		fun clearFocus() {
			TODO("Not yet implemented")
		}
		
	}
	
	class LocalSoftwareKeyboardController {
		fun hide() {
			TODO("Not yet implemented")
		}
		
	}
}
