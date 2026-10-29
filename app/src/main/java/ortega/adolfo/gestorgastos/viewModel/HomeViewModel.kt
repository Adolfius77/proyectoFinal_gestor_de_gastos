package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.ResumenCategoria
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import java.time.LocalDate
import java.time.YearMonth

class HomeViewModel : ViewModel() {
    private val _estado = MutableStateFlow(estadoInicial())
    val estado: StateFlow<HomeState> = _estado.asStateFlow()



    fun onTipoCambio(valor: TipoMovimiento) {
        actualizar { it.copy(tipo = valor, filtroCategoria = FILTRO_TODOS, busqueda = "") }
    }

    fun onBusquedaCambio(valor: String) {
        actualizar { it.copy(busqueda = valor) }
    }

    fun onFiltroCambio(valor: String) {
        actualizar { it.copy(filtroCategoria = valor) }
    }

    private fun actualizar(cambio: (HomeState) -> HomeState) {
        _estado.update { conFiltros(cambio(it)) }
    }

    private fun estadoInicial(): HomeState {
        val hoy = LocalDate.now()
        val mes = YearMonth.from(hoy)
        val delMes = DatosMock.movimientos.filter { YearMonth.from(it.fecha) == mes }

        val gastos = delMes.filter { it.tipo == TipoMovimiento.GASTO }
        val totalGastos = gastos.sumOf { it.monto }
        val totalIngresos = delMes
            .filter { it.tipo == TipoMovimiento.INGRESO }
            .sumOf { it.monto }

        val usuario = DatosMock.usuarioActual ?: DatosMock.usuarios.firstOrNull()

        val base = HomeState(
            nombreUsuario = usuario?.nombre?.replaceFirstChar { it.uppercase() } ?: "",
            ingresosMes = totalIngresos,
            gastosMes = totalGastos,
            diasRestantes = mes.lengthOfMonth() - hoy.dayOfMonth + 1,
            categoriasGasto = agruparPorCategoria(gastos, totalGastos)
        )
        return conFiltros(base)
    }


    private fun conFiltros(estado: HomeState): HomeState {
        val delTipo = DatosMock.movimientos.filter { it.tipo == estado.tipo }

        val filtros = listOf(FILTRO_TODOS) + delTipo.map { it.categoria }.distinct()

        val lista = delTipo
            .filter { estado.filtroCategoria == FILTRO_TODOS || it.categoria == estado.filtroCategoria }
            .filter { it.descripcion.contains(estado.busqueda.trim(), ignoreCase = true) }
            .sortedByDescending { it.fecha }

        return estado.copy(filtros = filtros, movimientos = lista)
    }


    private fun agruparPorCategoria(gastos: List<Movimiento>, total: Double): List<ResumenCategoria> {
        if (total <= 0.0) return emptyList()

        val porCategoria = gastos
            .groupBy { it.categoria }
            .map { (nombre, lista) -> nombre to lista.sumOf { it.monto } }
            .sortedByDescending { it.second }

        val principales = porCategoria.take(3).map { (nombre, monto) ->
            ResumenCategoria(nombre, monto, (monto / total).toFloat())
        }
        val resto = porCategoria.drop(3)
        if (resto.isEmpty()) return principales

        val montoResto = resto.sumOf { it.second }
        return principales + ResumenCategoria("Otras ${resto.size}", montoResto, (montoResto / total).toFloat())
    }
}
