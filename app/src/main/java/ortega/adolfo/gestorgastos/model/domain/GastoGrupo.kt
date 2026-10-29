package ortega.adolfo.gestorgastos.model.domain

data class GastoGrupo(
    val id: Int,
    val titulo: String,
    val fecha: String,
    val categoria: String,
    val monto: Double,
    val pagadoPor: String? = null
)