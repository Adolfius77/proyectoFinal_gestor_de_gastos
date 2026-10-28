package ortega.adolfo.gestorgastos.model.domain

data class IngresoFijo(
    val id: Int,
    val nombre: String,
    val monto: Double,
    val frecuencia: Frecuencia,
    val diaCobro: Int
)