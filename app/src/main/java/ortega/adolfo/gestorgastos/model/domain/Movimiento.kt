package ortega.adolfo.gestorgastos.model.domain

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

enum class TipoMovimiento { GASTO, INGRESO }

enum class MetodoPago { TARJETA, EFECTIVO }

data class Tarjeta(
    val tipo: String,
    val terminacion: String
)

                      val descripcion: String,
                      val categoria: String,