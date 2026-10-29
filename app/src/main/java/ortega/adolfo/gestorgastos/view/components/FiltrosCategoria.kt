package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.BordeControl
import ortega.adolfo.gestorgastos.ui.theme.Superficie


@Composable
fun FiltrosCategoria(
    opciones: List<String>,
    seleccionado: String,
    onSeleccionar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(opciones) { opcion ->
            val activo = opcion == seleccionado
            val forma = RoundedCornerShape(50)
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .clip(forma)
                    .background(if (activo) VerdePrimario else Superficie)
                    .then(if (activo) Modifier else Modifier.border(1.dp, BordeControl, forma))
                    .clickable { onSeleccionar(opcion) }
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = opcion,
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
fun FiltrosCategoriaPreview() {
    GestorGastosTheme {
        var actual by remember { mutableStateOf("Todos") }
        FiltrosCategoria(
            opciones = listOf("Todos", "Vivienda", "Alimentación", "Transporte"),
            seleccionado = actual,
            onSeleccionar = { actual = it }
        )
    }
}
