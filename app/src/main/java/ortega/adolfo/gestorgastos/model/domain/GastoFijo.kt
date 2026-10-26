package ortega.adolfo.gestorgastos.model.domain

data class GastoFijo(
    val nombre: String,
    val monto: Double,
    val tipo: String,
    val diaPago: Int,
    val recordar: Boolean
)
