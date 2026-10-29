package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.Movimiento

data class DetalleMovimientoState(
    val movimiento: Movimiento? = null,
    val mostrarConfirmacion: Boolean = false,
    val eliminado: Boolean = false
)
