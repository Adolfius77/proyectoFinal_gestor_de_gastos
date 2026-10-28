package ortega.adolfo.gestorgastos.model.domain

data class Grupo(
    val id: Int,
    val nombre: String,
    val ultimoMovimiento: String,
    val imagen: Int? = null
)