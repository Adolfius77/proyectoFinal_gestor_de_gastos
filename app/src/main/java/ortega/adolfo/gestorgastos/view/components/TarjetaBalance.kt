package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.NaranjaSuave
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.VerdeSuave
import ortega.adolfo.gestorgastos.utilities.formatoDinero
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme




@Composable
fun TarjetaBalance(
    queda: Double,
    ingresos: Double,
    gastos: Double,
    diasRestantes: Int,
    gastoDiario: Double,
    porcentajeUsado: Float,
    onVerDetalles: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(VerdePrimario)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.te_queda_este_mes),
            fontFamily = SpaceGrotesk,
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = formatoDinero(queda),
            style = MaterialTheme.typography.displaySmall,
            color = Color.White


        )
        Spacer(Modifier.height(10.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(50))
                .background(Color.White.copy(alpha = 0.15f))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = textoConNumeros(
                    if (gastoDiario > 0)
                        "$diasRestantes días | Puedes gastar ${formatoDinero(gastoDiario)} al día"
                    else
                        stringResource(R.string.ya_usaste_todo_tu_ingreso_del_mes)
                ),
                fontFamily = Manrope,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniTarjeta(
                titulo = stringResource(R.string.ingresos_del_mes),
                monto = "+${formatoDinero(ingresos)}",
                fondo = VerdeSuave,
                colorTexto = VerdePrimario,
                modifier = Modifier.weight(1f)
            )
            MiniTarjeta(
                titulo = stringResource(R.string.gastos_del_mes),
                monto = "-${formatoDinero(gastos)}",
                fondo = NaranjaSuave,
                colorTexto = RojoError,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(Modifier.height(14.dp))

        LinearProgressIndicator(
            progress = { porcentajeUsado.coerceIn(0f, 1f) },
            color = Color.White,
            trackColor = Color.White.copy(alpha = 0.3f),
            gapSize = 0.dp,
            drawStopIndicator = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(50))
        )
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = textoConNumeros("Usaste el ${(porcentajeUsado * 100).toInt()}% de tus ingresos"),
                fontFamily = Manrope,
                fontSize = 12.sp,
                color = Color.White
            )
            Text(
                text = stringResource(R.string.ver_detalles),
                fontFamily = Manrope,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.clickable(onClick = onVerDetalles)
            )
        }
    }
}

@Composable
private fun MiniTarjeta(
    titulo: String,
    monto: String,
    fondo: Color,
    colorTexto: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(fondo)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titulo,
            fontFamily = Manrope,
            fontSize = 13.sp,
            color = colorTexto
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = monto,
            fontFamily = SpaceGrotesk,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorTexto
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TarjetaBalancePreview() {
    GestorGastosTheme {
        TarjetaBalance(
            queda = 1500.0,
            ingresos = 20000.0,
            gastos = 18500.0,
            diasRestantes = 14,
            gastoDiario = 107.0,
            porcentajeUsado = 0.92f,
            onVerDetalles = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
