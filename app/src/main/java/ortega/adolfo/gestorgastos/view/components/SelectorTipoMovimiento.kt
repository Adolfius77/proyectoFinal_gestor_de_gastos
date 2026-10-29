package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.GrisPlaceholder
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario

@Composable
fun SelectorTipoMovimiento(
    seleccionado: TipoMovimiento,
    onSeleccionar: (TipoMovimiento) -> Unit,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(10.dp)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TipoMovimiento.entries.forEach { tipo ->
            val activo = tipo == seleccionado
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .clip(forma)
                    .background(if (activo) VerdePrimario else FondoClaro)
                    .border(2.dp, if (activo) VerdePrimario else Color(0xFFDADDD9), forma)
                    .clickable { onSeleccionar(tipo) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tipo.etiqueta,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (activo) Color.White else GrisPlaceholder
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectorTipoMovimientoPreview() {
    var tipo by remember { mutableStateOf(TipoMovimiento.GASTO) }
    SelectorTipoMovimiento(seleccionado = tipo, onSeleccionar = { tipo = it })
}