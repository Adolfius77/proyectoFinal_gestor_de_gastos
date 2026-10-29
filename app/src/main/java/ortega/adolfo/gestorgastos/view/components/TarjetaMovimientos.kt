package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme


@Composable
fun TarjetaMovimientos(
    movimientos: List<Movimiento>,
    onVerTodos: () -> Unit,
    modifier: Modifier = Modifier,
    limite: Int = 5
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.movimientos_recientes),
                    fontFamily = Manrope,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Text(
                    text = stringResource(R.string.ver_todos),
                    fontFamily = Manrope,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = VerdePrimario,
                    modifier = Modifier.clickable(onClick = onVerTodos)
                )
            }
            Spacer(Modifier.height(4.dp))

            val visibles = movimientos.take(limite)
            if (visibles.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_hay_movimientos_que_coincidan),
                    fontFamily = Manrope,
                    fontSize = 14.sp,
                    color = TextoSecundario,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            } else {
                visibles.forEachIndexed { indice, movimiento ->
                    ItemMovimiento(movimiento)

                    if (indice < visibles.lastIndex) {
                        HorizontalDivider(color = FondoClaro)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TarjetaMovimientosPreview() {
    GestorGastosTheme {
        TarjetaMovimientos(
            movimientos = DatosMock.movimientos.sortedByDescending { it.fecha },
            onVerTodos = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
