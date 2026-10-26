package ortega.adolfo.gestorgastos.model.domain

data class IngresoFijo(
    val nombre: String,
    val monto: Double,
    val frecuencia: String,
    val diaCobro: Int
)
