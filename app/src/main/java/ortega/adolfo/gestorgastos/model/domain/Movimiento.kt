package ortega.adolfo.gestorgastos.model.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class Movimiento(
    val id: Int,
    val descripcion: String,
    val monto: Double,
    val fecha: LocalDate,
    val categoria: String,
    val tipo: TipoMovimiento,
    val metodoPago: MetodoPago,
    val tarjeta: Tarjeta? = null,
    val hora: LocalTime? = null,
    val ubicacion: String? = null,
    val nota: String? = null,
    val fotoRecibo: String? = null
)

enum class TipoMovimiento { GASTO, INGRESO }
enum class MetodoPago { TARJETA, EFECTIVO }

data class Tarjeta(
    val tipo: String,
    val terminacion: String
)



