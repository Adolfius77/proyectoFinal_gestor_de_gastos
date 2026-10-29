package ortega.adolfo.gestorgastos.viewModel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import ortega.adolfo.gestorgastos.model.domain.Tarjeta
import ortega.adolfo.gestorgastos.model.domain.MetodoPago

class NuevoMovimientoViewModel : ViewModel() {
    private val _estado = MutableStateFlow(NuevoMovimientoState())
    val estado: StateFlow<NuevoMovimientoState> = _estado.asStateFlow()

    fun onTipoCambio(valor: TipoMovimiento) {
        if (valor == _estado.value.tipo) return
        // Las categorias cambian segun el tipo, asi que se limpia la elegida
        _estado.update { it.copy(tipo = valor, categoria = "", errorCategoria = null) }
    }

    fun onMontoCambio(valor: String) {
        val limpio = valor.filter { it.isDigit() || it == '.' }
        if (limpio.count { it == '.' } > 1) return
        if (limpio.substringAfter('.', "").length > 2) return
        _estado.update { it.copy(monto = limpio, errorMonto = null) }
    }

    fun onDescripcionCambio(valor: String) {
        _estado.update { it.copy(descripcion = valor, errorDescripcion = null) }
    }

    fun onCategoriaCambio(valor: String) {
        _estado.update { it.copy(categoria = valor, errorCategoria = null) }
    }

    fun onTipoPagoCambio(valor: String) {
        _estado.update { it.copy(tipoPago = valor, errorTarjeta = null) }
    }

    fun onTarjetaCambio(valor: String) {
        _estado.update { it.copy(tarjeta = valor, errorTarjeta = null) }
    }

    fun onUbicacionCambio(valor: String) {
        _estado.update { it.copy(ubicacion = valor) }
    }

    fun onUsarUbicacionActual() {
        _estado.update { it.copy(ubicacion = DatosMock.UBICACION_MOCK) }
    }

    fun onReciboSeleccionado(uri: String?) {
        _estado.update { it.copy(reciboUri = uri) }
    }

    fun onFechaCambio(valor: LocalDate) {
        _estado.update { it.copy(fecha = valor, errorFecha = null) }
    }

    // ---------- Dialogo para agregar tarjeta ----------

    fun abrirDialogoTarjeta() {
        _estado.update { it.copy(mostrarDialogoTarjeta = true, nuevaTarjeta = "", errorNuevaTarjeta = null) }
    }

    fun cerrarDialogoTarjeta() {
        _estado.update { it.copy(mostrarDialogoTarjeta = false, nuevaTarjeta = "", errorNuevaTarjeta = null) }
    }

    fun onNuevaTarjetaCambio(valor: String) {
        _estado.update { it.copy(nuevaTarjeta = valor, errorNuevaTarjeta = null) }
    }

    fun agregarTarjeta() {
        val actual = _estado.value
        val nombre = actual.nuevaTarjeta.trim()

        val error = when {
            nombre.isBlank() -> "Escribe el nombre de la tarjeta"
            actual.tarjetas.any { it.equals(nombre, ignoreCase = true) } -> "Esta tarjeta ya existe"
            else -> null
        }
        if (error != null) {
            _estado.update { it.copy(errorNuevaTarjeta = error) }
            return
        }

        DatosMock.tarjetas.add(nombre)
        _estado.update {
            it.copy(
                tarjetas = it.tarjetas + nombre,
                tarjeta = nombre,
                errorTarjeta = null,
                mostrarDialogoTarjeta = false,
                nuevaTarjeta = "",
                errorNuevaTarjeta = null
            )
        }
    }

    fun guardar() {
        val actual = _estado.value

        val errorMonto = validarMonto(actual.monto)
        val errorDescripcion = if (actual.descripcion.isBlank()) "Escribe una descripción" else null
        val errorCategoria = if (actual.categoria.isBlank()) "Selecciona una categoría" else null
        val errorTarjeta =
            if (actual.pagaConTarjeta && actual.tarjeta.isBlank()) "Selecciona una tarjeta" else null
        val errorFecha = validarFecha(actual.fecha)

        val hayError = listOf(
            errorMonto, errorDescripcion, errorCategoria, errorTarjeta, errorFecha
        ).any { it != null }

        if (hayError) {
            _estado.update {
                it.copy(
                    errorMonto = errorMonto,
                    errorDescripcion = errorDescripcion,
                    errorCategoria = errorCategoria,
                    errorTarjeta = errorTarjeta,
                    errorFecha = errorFecha
                )
            }
            return
        }

        val siguienteId = (DatosMock.movimientos.maxOfOrNull { it.id } ?: 0) + 1

        val movimiento = Movimiento(
            id = siguienteId,
            descripcion = actual.descripcion.trim(),
            monto = BigDecimal(actual.monto).toDouble(),
            fecha = requireNotNull(actual.fecha),
            categoria = actual.categoria,
            tipo = actual.tipo,
            metodoPago = MetodoPago.desdeEtiqueta(actual.tipoPago),
            tarjeta = if (actual.pagaConTarjeta) Tarjeta.desdeTexto(actual.tarjeta) else null,
            hora = LocalTime.now().withSecond(0).withNano(0),
            ubicacion = actual.ubicacion.trim().ifBlank { null },
            fotoRecibo = actual.reciboUri
        )
        DatosMock.movimientos.add(movimiento)

        _estado.update { it.copy(guardadoExitoso = true) }
    }

    fun onGuardadoConsumido() {
        _estado.update { it.copy(guardadoExitoso = false) }
    }

    private fun validarMonto(monto: String): String? {
        val valor = monto.toBigDecimalOrNull()
        return when {
            monto.isBlank() -> "Escribe el monto"
            valor == null -> "Monto no válido"
            valor <= BigDecimal.ZERO -> "El monto debe ser mayor a 0"
            else -> null
        }
    }

    private fun validarFecha(fecha: LocalDate?): String? = when {
        fecha == null -> "Selecciona la fecha"
        fecha.isAfter(LocalDate.now()) -> "La fecha no puede ser futura"
        else -> null
    }

}