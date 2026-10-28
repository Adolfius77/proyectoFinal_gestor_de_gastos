package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Categoria
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock

class PresupuestoAgregarCategoriaViewModel : ViewModel() {

    private val _estado = MutableStateFlow(PresupuestoAgregarCategoriaState())

    val estado: StateFlow<PresupuestoAgregarCategoriaState> = _estado.asStateFlow()

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

    fun onNombreCambio(valor: String) {
        _estado.update { it.copy(nombre = valor, errorNombre = null) }
    }

    fun onPresupuestoCambio(valor: String) {
        val limpio = valor.filter { c -> c.isDigit() || c == '.' }
        _estado.update { it.copy(presupuesto = limpio, errorPresupuesto = null) }
    }

    fun onGuardadoConsumido() {
        _estado.update { it.copy(guardadoExitoso = false) }
    }

    fun guardar() {
        val actual = _estado.value

        val errorNombre = validarNombre(actual.nombre, actual.categorias)
        val errorPresupuesto = validarPresupuesto(actual.presupuesto)

        if (errorNombre != null || errorPresupuesto != null) {
            _estado.update {
                it.copy(errorNombre = errorNombre, errorPresupuesto = errorPresupuesto)
            }
            return
        }

        val nueva = Categoria(
            id = (actual.categorias.maxOfOrNull { it.id } ?: 0) + 1,
            nombre = actual.nombre.trim(),
            presupuesto = actual.presupuesto.toDouble(),
            gastado = 0.0
        )

        _estado.update {
            it.copy(
                categorias = it.categorias + nueva,
                nombre = "",
                presupuesto = "",
                guardadoExitoso = true
            )
        }
    }

    private fun validarNombre(nombre: String, existentes: List<Categoria>): String? = when {
        nombre.isBlank() -> "Escribe el nombre de la categoría"
        existentes.any { it.nombre.equals(nombre.trim(), ignoreCase = true) } ->
            "Ya existe una categoría con ese nombre"
        else -> null
    }

    private fun validarPresupuesto(presupuesto: String): String? {
        if (presupuesto.isBlank()) return "Escribe el presupuesto mensual"
        val valor = presupuesto.toDoubleOrNull()
        return if (valor == null || valor <= 0.0) "Monto no válido" else null
    }
}
