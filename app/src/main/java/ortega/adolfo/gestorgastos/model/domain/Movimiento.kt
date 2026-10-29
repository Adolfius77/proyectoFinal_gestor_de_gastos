package ortega.adolfo.gestorgastos.model.domain

import java.math.BigDecimal
import java.time.LocalDate

data class Movimiento(val id: String,
                      val movimiento: TipoMovimiento,
                      val monto: BigDecimal,
                      val descripcion: String,
                      val categoria: String,
                      val tipoPago: String,
                      val tarjeta: String?,
                      val ubicacion: String?,
                      val reciboUri: String?,
                      val fecha: LocalDate)