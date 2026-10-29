package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.model.domain.GastoGrupo
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.view.components.CabeceraDetalleGrupo
import ortega.adolfo.gestorgastos.view.components.ItemGastoGrupo
import ortega.adolfo.gestorgastos.view.components.TarjetaDeuda

@Composable
fun GrupoDetalleScreen(
    grupo: Grupo,
    gastos: List<GastoGrupo>,
    modifier: Modifier = Modifier,
    montoPendiente: Double = 750.0,
    onVolver: () -> Unit = {},
    onAbrirInfo: () -> Unit = {},
    onVerDeuda: () -> Unit = {},
    onAbrirGasto: (GastoGrupo) -> Unit = {},
    onAgregarGasto: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
    ) {
        CabeceraDetalleGrupo(
            grupo = grupo,
            onVolver = onVolver,
            onAbrirInfo = onAbrirInfo
        )
        HorizontalDivider(color = Color.Black.copy(alpha = 0.08f))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 24.dp, end = 24.dp, top = 24.dp, bottom = 96.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    TarjetaDeuda(monto = montoPendiente, onClick = onVerDeuda)
                }
                items(gastos, key = { it.id }) { gasto ->
                    ItemGastoGrupo(gasto = gasto, onClick = { onAbrirGasto(gasto) })
                }
            }

            ExtendedFloatingActionButton(
                onClick = onAgregarGasto,
                containerColor = VerdePrimario,
                contentColor = Color.White,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 24.dp, bottom = 24.dp)
            ) {
                Text(
                    text = "Agregar gasto",
                    fontFamily = Manrope,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GrupoDetalleScreenPreview() {
    GrupoDetalleScreen(
        grupo = DatosMock.grupos.first(),
        gastos = DatosMock.gastosGrupo
    )
}