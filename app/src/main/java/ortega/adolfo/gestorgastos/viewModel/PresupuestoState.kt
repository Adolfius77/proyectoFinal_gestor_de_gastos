package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.Categoria
import ortega.adolfo.gestorgastos.model.domain.ModoVista

data class PresupuestoState(
    val mes: String = "Octubre 2026",
    val ingresosFijos: Double = 0.0,
    val gastosFijos: Double = 0.0,
    val categorias: List<Categoria> = emptyList(),

    val modo: ModoVista = ModoVista.MONTO,
    val alertasActivas: Boolean = true
) {
    // Lo que queda despues de pagar los gastos fijos (asi lo muestra el diseño)
    val disponible: Double get() = ingresosFijos - gastosFijos
}
