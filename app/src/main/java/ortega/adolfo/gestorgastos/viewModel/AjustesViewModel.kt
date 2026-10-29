package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AjustesViewModel : ViewModel() {
    private val _estado = MutableStateFlow(AjustesState())
    val estado: StateFlow<AjustesState> = _estado.asStateFlow()

    fun onModoOscuroCambio(activo: Boolean) {
        _estado.update { it.copy(modoOscuro = activo) }
    }
}
