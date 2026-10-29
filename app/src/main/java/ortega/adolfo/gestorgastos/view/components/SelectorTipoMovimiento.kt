package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

private fun TipoMovimiento.etiqueta(): String = when (this) {
    TipoMovimiento.GASTO -> "Gastos"
    TipoMovimiento.INGRESO -> "Ingresos"
}


@Composable
fun SelectorTipoMovimiento(
    seleccionado: TipoMovimiento,
    onSeleccionar: (TipoMovimiento) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TipoMovimiento.entries.forEach { tipo ->
            val activo = tipo == seleccionado
            val forma = RoundedCornerShape(12.dp)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(forma)
                    .background(if (activo) VerdePrimario else Color.White)
                    .then(if (activo) Modifier else Modifier.border(1.dp, TextoPrincipal, forma))
                    .clickable { onSeleccionar(tipo) }
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tipo.etiqueta(),
                    style = MaterialTheme.typography.labelLarge,
                    color = if (activo) Color.White else TextoPrincipal,
                    fontFamily = Manrope
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectorTipoMovimientoPreview() {
    GestorGastosTheme {
        var tipo by remember { mutableStateOf(TipoMovimiento.GASTO) }
        SelectorTipoMovimiento(seleccionado = tipo, onSeleccionar = { tipo = it })
    }
}
