package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.Periodo
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import java.time.YearMonth


@Composable
fun CabeceraEstadisticas(

    seleccionado: Periodo,
    onSeleccionar: (Periodo) -> Unit,
    mes: YearMonth,
    onSeleccionarMes: (YearMonth) -> Unit,
    categoria: String,
    categorias: List<String>,
    onSeleccionarCategoria: (String) -> Unit,
    modifier: Modifier = Modifier,
    cantidadMeses: Int = 6
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.estadisticas),
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = TextoPrincipal
        )
        Spacer(Modifier.height(10.dp))
        SelectorPeriodo(
            seleccionado = seleccionado,
            onSeleccionar = onSeleccionar
        )
        Spacer(Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            BotonFecha(
                mes = mes,
                onSeleccionar = onSeleccionarMes,
                cantidadMeses = cantidadMeses,
                modifier = Modifier.weight(1f)

            )
            Spacer(Modifier.width(20.dp))
            SelectorCategoria(
                seleccion = categoria,
                opciones = categorias,
                onSeleccion = onSeleccionarCategoria,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun cabeceraEstadisticaPreview() {
    var periodo by remember { mutableStateOf(Periodo.MES) }
    var mes by remember { mutableStateOf(YearMonth.of(2026, 10)) }
    var categoria by remember { mutableStateOf("Todas") }
    CabeceraEstadisticas(
        seleccionado = periodo,
        onSeleccionar = { periodo = it },
        mes = mes,
        onSeleccionarMes = { mes = it },
        categoria = categoria,
        categorias = DatosMock.categoriasFiltro,
        onSeleccionarCategoria = { categoria = it }
    )
}
