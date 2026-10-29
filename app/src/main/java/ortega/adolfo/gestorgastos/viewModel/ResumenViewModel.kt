package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Periodo
import java.time.YearMonth

class ResumenViewModel : ViewModel() {
    private val _estado = MutableStateFlow(ResumenState())
    val estado: StateFlow<ResumenState> = _estado.asStateFlow()

    fun onPeriodoCambio(valor: Periodo){
        _estado.update { it.copy(periodo = valor) }
    }

    fun onMesCambio(valor: YearMonth){
        _estado.update { it.copy(mes = valor) }
    }

    fun onCategoriaCambio(valor: String){
        _estado.update { it.copy(categoria = valor) }
    }

}