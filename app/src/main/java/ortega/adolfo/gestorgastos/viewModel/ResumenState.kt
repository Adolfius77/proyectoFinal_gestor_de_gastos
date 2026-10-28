package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.Periodo
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import java.time.YearMonth

data class ResumenState(
    val periodo: Periodo = Periodo.MES,
    val mes: YearMonth = YearMonth.now(),
    val categoria: String = "Todas",
    val categorias: List<String> = DatosMock.categoriasFiltro,
    val totalGastado: String = "$9,550",
    val categoriaMayor: String = "Vivienda",
    val precioMayor: String = "$4,500"
)
