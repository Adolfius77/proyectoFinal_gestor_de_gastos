package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Categoria
import ortega.adolfo.gestorgastos.model.domain.ModoVista
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock

class PresupuestoViewModel : ViewModel() {

    private val _estado = MutableStateFlow(PresupuestoState())

    val estado: StateFlow<PresupuestoState> = _estado.asStateFlow()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        _estado.update {
            it.copy(
                ingresosFijos = DatosMock.ingresosFijos.sumOf { ingreso -> ingreso.monto },
                gastosFijos = DatosMock.gastosFijos.sumOf { gasto -> gasto.monto },
                categorias = DatosMock.categorias.toList()
            )
        }
    }

    fun onMesCambio(valor: String) {
        _estado.update { it.copy(mes = valor) }
    }

    fun onModoCambio(valor: ModoVista) {
        _estado.update { it.copy(modo = valor) }
    }

    fun onAlertasCambio(valor: Boolean) {
        _estado.update { it.copy(alertasActivas = valor) }
    }

    fun onEliminarCategoria(categoria: Categoria) {
        _estado.update { it.copy(categoriaPorEliminar = categoria) }
    }

    fun onEliminarCancelado() {
        _estado.update { it.copy(categoriaPorEliminar = null) }
    }

    fun onEliminarConfirmado() {
        val porEliminar = _estado.value.categoriaPorEliminar ?: return
        _estado.update {
            it.copy(
                categorias = it.categorias.filter { categoria -> categoria.id != porEliminar.id },
                categoriaPorEliminar = null
            )
        }
    }
}