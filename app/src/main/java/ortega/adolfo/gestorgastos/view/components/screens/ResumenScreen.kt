package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.model.domain.Periodo
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.view.components.CabeceraEstadisticas
import ortega.adolfo.gestorgastos.view.components.GastoResumen
import ortega.adolfo.gestorgastos.view.components.PanelResumen
import ortega.adolfo.gestorgastos.viewModel.ResumenState
import ortega.adolfo.gestorgastos.viewModel.ResumenViewModel
import java.time.YearMonth


@Composable
fun ResumenSreen(modifier: Modifier = Modifier, viewModel: ResumenViewModel = viewModel()){
    val estado by viewModel.estado.collectAsState()
    ResumenContenido(
        estado = estado,
        onPeriodoCambio = viewModel::onPeriodoCambio,
        onMesCambio = viewModel::onMesCambio,
        onCategoriaCambio = viewModel::onCategoriaCambio,
        modifier = modifier
    )

}
@Composable
fun ResumenContenido(
    estado: ResumenState,
    onPeriodoCambio: (Periodo) -> Unit,
    onMesCambio: (YearMonth) -> Unit,
    onCategoriaCambio: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        CabeceraEstadisticas(
            seleccionado =  estado.periodo,
            onSeleccionar = onPeriodoCambio,
            mes = estado.mes,
            onSeleccionarMes = onMesCambio,
            categoria = estado.categoria,
            categorias = estado.categorias,
            onSeleccionarCategoria = onCategoriaCambio
        )
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            PanelResumen(
                totalGastado = estado.totalGastado,
                categoriaMayor = estado.categoriaMayor,
                precioMayor = estado.precioMayor
            )
        }
        Spacer(Modifier.height(16.dp))

        GastoResumen()
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun ResumenScreenPreview(){
    ResumenContenido(
        estado = ResumenState(),
        onPeriodoCambio = {},
        onMesCambio = {},
        onCategoriaCambio = {}
    )
}