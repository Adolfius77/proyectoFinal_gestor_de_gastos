package ortega.adolfo.gestorgastos.model.domain

data class GastoFijo(
    val id: Int,
    val nombre: String,
    val monto: Double,
    val tipo: TipoGasto,
    val diaPago: Int,
    val recordar: Boolean
)