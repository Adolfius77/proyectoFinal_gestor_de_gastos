package ortega.adolfo.gestorgastos.model.domain

import java.time.LocalDate

enum class TipoMovimiento { GASTO, INGRESO }

enum class MetodoPago { TARJETA, EFECTIVO }


data class Movimiento(
    val id: Int,
    val descripcion: String,
    val monto: Double,
    val fecha: LocalDate,
    val categoria: String,
    val tipo: TipoMovimiento,
    val metodoPago: MetodoPago
)
