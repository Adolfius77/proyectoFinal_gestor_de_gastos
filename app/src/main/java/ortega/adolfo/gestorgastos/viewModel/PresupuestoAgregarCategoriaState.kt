package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.Categoria

data class PresupuestoAgregarCategoriaState(
    val mes: String = "Octubre",
    val nombre: String = "",
    val presupuesto: String = "",
    val sugeridas: List<String> = listOf("Mascotas", "Educación", "Hogar"),

    val ingresosFijos: Double = 0.0,
    val gastosFijos: Double = 0.0,
    val categorias: List<Categoria> = emptyList(),

    val errorNombre: String? = null,
    val errorPresupuesto: String? = null,

    val guardadoExitoso: Boolean = false
) {
    val montoIngresado: Double get() = presupuesto.toDoubleOrNull() ?: 0.0

    val restante: Double
        get() = ingresosFijos - gastosFijos - categorias.sumOf { it.presupuesto } - montoIngresado
}