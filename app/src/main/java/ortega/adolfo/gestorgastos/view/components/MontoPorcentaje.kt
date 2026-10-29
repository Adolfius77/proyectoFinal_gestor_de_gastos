package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun <T> MontoPorcentaje(
    opciones: List<T>,
    seleccion: T,
    etiqueta: (T) -> String,
    onSeleccion: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(50)

    Row(
        modifier = modifier
            .clip(forma)
            .background(Color.White)
            .border(1.dp, Color(0xFFDADDD9), forma)
            .padding(2.dp)
            .selectableGroup()
    ) {
        opciones.forEach { opcion ->
            val activo = opcion == seleccion
            Box(
                modifier = Modifier
                    .clip(forma)
                    .background(if (activo) VerdePrimario else Color.Transparent)
                    .selectable(
                        selected = activo,
                        onClick = { onSeleccion(opcion) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = etiqueta(opcion),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (activo) Color.White else TextoSecundario
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MontoPorcentajePreview() {
    GestorGastosTheme {
        var seleccion by remember { mutableStateOf("Monto") }
        MontoPorcentaje(
            opciones = listOf("Monto", "Porcentaje"),
            seleccion = seleccion,
            etiqueta = { it },
            onSeleccion = { seleccion = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}
