package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Frecuencia
import ortega.adolfo.gestorgastos.model.domain.IngresoFijo
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock

class PresupuestoIngresoFijoViewModel : ViewModel() {

    private val _estado = MutableStateFlow(PresupuestoIngresoFijoState())

    val estado: StateFlow<PresupuestoIngresoFijoState> = _estado.asStateFlow()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        _estado.update { it.copy(ingresos = DatosMock.ingresosFijos.toList()) }
    }

    fun onNombreCambio(valor: String) {
        _estado.update { it.copy(nombre = valor, errorNombre = null) }
    }

    fun onMontoCambio(valor: String) {
        // solo numeros y un punto decimal
        val limpio = valor.filter { c -> c.isDigit() || c == '.' }
        _estado.update { it.copy(monto = limpio, errorMonto = null) }
    }

    fun onFrecuenciaCambio(valor: Frecuencia) {
        _estado.update { it.copy(frecuencia = valor) }
    }

    fun onDiaCobroCambio(valor: Int) {
        _estado.update { it.copy(diaCobro = valor) }
    }

    fun onEditar(ingreso: IngresoFijo) {
        _estado.update {
            it.copy(
                nombre = ingreso.nombre,
                monto = ingreso.monto.toString().removeSuffix(".0"),
                frecuencia = ingreso.frecuencia,
                diaCobro = ingreso.diaCobro,
                idEditando = ingreso.id,
                errorNombre = null,
                errorMonto = null
            )
        }
    }

    fun onEliminar(id: Int) {
        _estado.update {
            it.copy(
                ingresos = it.ingresos.filter { ingreso -> ingreso.id != id },
                idEditando = if (it.idEditando == id) null else it.idEditando
            )
        }
    }

    fun onGuardadoConsumido() {
        _estado.update { it.copy(guardadoExitoso = false) }
    }

    fun agregarOtro() {
        agregarFormulario()
    }

    fun guardar() {
        val actual = _estado.value
        val formularioVacio = actual.nombre.isBlank() && actual.monto.isBlank()

        if (formularioVacio && actual.ingresos.isNotEmpty()) {
            // TODO: aqui se conectara el guardado real (repositorio / base de datos) con actual.ingresos
            _estado.update { it.copy(guardadoExitoso = true) }
            return
        }

        if (agregarFormulario()) {
            // TODO: aqui se conectara el guardado real (repositorio / base de datos)
            _estado.update { it.copy(guardadoExitoso = true) }
        }
    }

    private fun agregarFormulario(): Boolean {
        val actual = _estado.value

        val errorNombre = validarNombre(actual.nombre)
        val errorMonto = validarMonto(actual.monto)

        if (errorNombre != null || errorMonto != null) {
            _estado.update { it.copy(errorNombre = errorNombre, errorMonto = errorMonto) }
            return false
        }

        val id = actual.idEditando ?: ((actual.ingresos.maxOfOrNull { it.id } ?: 0) + 1)
        val nuevo = IngresoFijo(
            id = id,
            nombre = actual.nombre.trim(),
            monto = actual.monto.toDouble(),
            frecuencia = actual.frecuencia,
            diaCobro = actual.diaCobro
        )
        val lista = if (actual.idEditando != null) {
            actual.ingresos.map { if (it.id == id) nuevo else it }
        } else {
            actual.ingresos + nuevo
        }

        _estado.update {
            it.copy(
                ingresos = lista,
                nombre = "",
                monto = "",
                frecuencia = Frecuencia.MENSUAL,
                diaCobro = 30,
                idEditando = null,
                errorNombre = null,
                errorMonto = null
            )
        }
        return true
    }

    private fun validarNombre(nombre: String): String? = when {
        nombre.isBlank() -> "Escribe el nombre del ingreso"
        else -> null
    }

    private fun validarMonto(monto: String): String? {
        if (monto.isBlank()) return "Escribe el monto"
        val valor = monto.toDoubleOrNull()
        return if (valor == null || valor <= 0.0) "Monto no válido" else null
    }
}