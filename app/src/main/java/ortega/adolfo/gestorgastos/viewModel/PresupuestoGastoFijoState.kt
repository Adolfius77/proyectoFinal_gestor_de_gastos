package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.GastoFijo
import ortega.adolfo.gestorgastos.model.domain.TipoGasto

data class PresupuestoGastoFijoState(
    val mes: String = "Octubre",
    val nombre: String = "",
    val monto: String = "",
    val tipo: TipoGasto = TipoGasto.VIVIENDA,
    val diaPago: Int = 1,
    val recordar: Boolean = true,
    val ingresosFijos: Double = 0.0,
    val gastos: List<GastoFijo> = emptyList(),
    val idEditando: Int? = null,

    val errorNombre: String? = null,
    val errorMonto: String? = null,

    val guardadoExitoso: Boolean = false
) {
    val total: Double get() = gastos.sumOf { it.monto }

    val restante: Double get() = ingresosFijos - total
}
