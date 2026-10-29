package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.util.comoMoneda

@Composable
fun ResumenPresupuesto(
    disponible: Double,
    ingresosFijos: Double,
    gastosFijos: Double,
    onAgregarIngreso: () -> Unit,
    onAgregarGastoFijo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = VerdePrimario)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.resumen_disponible),
                fontSize = 13.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = disponible.comoMoneda(),
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(color = Color.White.copy(alpha = 0.25f))
            Spacer(Modifier.height(12.dp))

            FilaMonto(
                etiqueta = stringResource(R.string.resumen_ingresos_fijos),
                monto = "+" + ingresosFijos.comoMoneda()
            )
            Spacer(Modifier.height(6.dp))
            FilaMonto(
                etiqueta = stringResource(R.string.resumen_gastos_fijos),
                monto = "-" + gastosFijos.comoMoneda()
            )
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BotonSecundario(
                    texto = stringResource(R.string.boton_ingreso),
                    onClick = onAgregarIngreso,
                    modifier = Modifier.weight(1f)
                )
                BotonSecundario(
                    texto = stringResource(R.string.boton_gasto_fijo),
                    onClick = onAgregarGastoFijo,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun FilaMonto(etiqueta: String, monto: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = etiqueta, fontSize = 14.sp, color = Color.White.copy(alpha = 0.85f))
        Text(
            text = monto,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ResumenPresupuestoPreview() {
    Column(Modifier.padding(16.dp)) {
        ResumenPresupuesto(
            disponible = 12800.0,
            ingresosFijos = 20000.0,
            gastosFijos = 7200.0,
            onAgregarIngreso = {},
            onAgregarGastoFijo = {}
        )
    }
}