package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.Frecuencia
import ortega.adolfo.gestorgastos.model.domain.IngresoFijo

data class PresupuestoIngresoFijoState(
    val mes: String = "Octubre",
    val nombre: String = "",
    val monto: String = "",
    val frecuencia: Frecuencia = Frecuencia.MENSUAL,
    val diaCobro: Int = 30,
    val ingresos: List<IngresoFijo> = emptyList(),
    val idEditando: Int? = null,

    val errorNombre: String? = null,
    val errorMonto: String? = null,

    val guardadoExitoso: Boolean = false
) {
    val total: Double get() = ingresos.sumOf { it.monto }
}