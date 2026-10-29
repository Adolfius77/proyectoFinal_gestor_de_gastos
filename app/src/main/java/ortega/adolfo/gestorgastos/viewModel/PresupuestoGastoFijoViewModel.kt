package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.GastoFijo
import ortega.adolfo.gestorgastos.model.domain.TipoGasto
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock

class PresupuestoGastoFijoViewModel : ViewModel() {

    private val _estado = MutableStateFlow(PresupuestoGastoFijoState())

    val estado: StateFlow<PresupuestoGastoFijoState> = _estado.asStateFlow()

    init {
        cargarDatos()
    }

    fun cargarDatos() {
        _estado.update {
            it.copy(
                ingresosFijos = DatosMock.ingresosFijos.sumOf { ingreso -> ingreso.monto },
                gastos = DatosMock.gastosFijos.toList()
            )
        }
    }

    fun onNombreCambio(valor: String) {
        _estado.update { it.copy(nombre = valor, errorNombre = null) }
    }

    fun onMontoCambio(valor: String) {
        val limpio = valor.filter { c -> c.isDigit() || c == '.' }
        _estado.update { it.copy(monto = limpio, errorMonto = null) }
    }

    fun onTipoCambio(valor: TipoGasto) {
        _estado.update { it.copy(tipo = valor) }
    }

    fun onDiaPagoCambio(valor: Int) {
        _estado.update { it.copy(diaPago = valor) }
    }

    fun onRecordarCambio(valor: Boolean) {
        _estado.update { it.copy(recordar = valor) }
    }

    fun onEditar(gasto: GastoFijo) {
        _estado.update {
            it.copy(
                nombre = gasto.nombre,
                monto = gasto.monto.toString().removeSuffix(".0"),
                tipo = gasto.tipo,
                diaPago = gasto.diaPago,
                recordar = gasto.recordar,
                idEditando = gasto.id,
                errorNombre = null,
                errorMonto = null
            )
        }
    }

    fun onEliminar(id: Int) {
        _estado.update {
            it.copy(
                gastos = it.gastos.filter { gasto -> gasto.id != id },
                idEditando = if (it.idEditando == id) null else it.idEditando
            )
        }
    }

    fun onGuardadoConsumido() {
        _estado.update { it.copy(guardadoExitoso = false) }
    }

    fun guardar() {
        val actual = _estado.value
        val formularioVacio = actual.nombre.isBlank() && actual.monto.isBlank()

        if (formularioVacio && actual.gastos.isNotEmpty()) {
            // TODO: aqui se conectara el guardado real (repositorio / base de datos) con actual.gastos
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

        val id = actual.idEditando ?: ((actual.gastos.maxOfOrNull { it.id } ?: 0) + 1)
        val nuevo = GastoFijo(
            id = id,
            nombre = actual.nombre.trim(),
            monto = actual.monto.toDouble(),
            tipo = actual.tipo,
            diaPago = actual.diaPago,
            recordar = actual.recordar
        )
        val lista = if (actual.idEditando != null) {
            actual.gastos.map { if (it.id == id) nuevo else it }
        } else {
            actual.gastos + nuevo
        }

        _estado.update {
            it.copy(
                gastos = lista,
                nombre = "",
                monto = "",
                tipo = TipoGasto.VIVIENDA,
                diaPago = 1,
                recordar = true,
                idEditando = null,
                errorNombre = null,
                errorMonto = null
            )
        }
        return true
    }


    private fun validarNombre(nombre: String): String? = when {
        nombre.isBlank() -> "Escribe el nombre del gasto"
        else -> null
    }

    private fun validarMonto(monto: String): String? {
        if (monto.isBlank()) return "Escribe el monto"
        val valor = monto.toDoubleOrNull()
        return if (valor == null || valor <= 0.0) "Monto no válido" else null
    }
}
