package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.ResumenCategoria
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento

const val FILTRO_TODOS = "Todos"

data class HomeState(

    val nombreUsuario: String = "",


    val ingresosMes: Double = 0.0,
    val gastosMes: Double = 0.0,
    val diasRestantes: Int = 0,


    val categoriasGasto: List<ResumenCategoria> = emptyList(),


    val tipo: TipoMovimiento = TipoMovimiento.GASTO,
    val busqueda: String = "",
    val filtroCategoria: String = FILTRO_TODOS,
    val filtros: List<String> = listOf(FILTRO_TODOS),
    val movimientos: List<Movimiento> = emptyList()
) {

    val quedaEsteMes: Double get() = ingresosMes - gastosMes

    val porcentajeUsado: Float get() =
        if (ingresosMes <= 0.0) 0f else (gastosMes / ingresosMes).toFloat()

    val gastoDiarioSugerido: Double get() =
        if (diasRestantes <= 0 || quedaEsteMes <= 0) 0.0 else quedaEsteMes / diasRestantes
}
