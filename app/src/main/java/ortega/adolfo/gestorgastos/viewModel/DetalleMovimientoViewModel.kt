package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.navigation.Ruta

class DetalleMovimientoViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id: Int = savedStateHandle.toRoute<Ruta.DetalleMovimiento>().id

    private val _estado = MutableStateFlow(
        DetalleMovimientoState(movimiento = DatosMock.movimientos.find { it.id == id })
    )
    val estado: StateFlow<DetalleMovimientoState> = _estado.asStateFlow()

    fun onEliminarClick() {
        _estado.update { it.copy(mostrarConfirmacion = true) }
    }

    fun onCancelarEliminar() {
        _estado.update { it.copy(mostrarConfirmacion = false) }
    }

    fun onConfirmarEliminar() {
        DatosMock.movimientos.removeAll { it.id == id }
        _estado.update { it.copy(mostrarConfirmacion = false, eliminado = true) }
    }
}
