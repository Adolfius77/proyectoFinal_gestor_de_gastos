package ortega.adolfo.gestorgastos.model.domain

data class Categoria(
    val id: Int,
    val nombre: String,
    val presupuesto: Double,
    val gastado: Double
)