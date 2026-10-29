package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun <T> GrupoChips(
    opciones: List<T>,
    seleccion: T?,
    etiqueta: (T) -> String,
    onSeleccion: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        opciones.forEach { opcion ->
            val activo = opcion == seleccion
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(if (activo) VerdePrimario else Color.White)
                    .border(
                        border = BorderStroke(1.dp, if (activo) VerdePrimario else TextoPrincipal),
                        shape = RoundedCornerShape(50)
                    )
                    .selectable(
                        selected = activo,
                        onClick = { onSeleccion(opcion) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = etiqueta(opcion),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (activo) Color.White else TextoPrincipal
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GrupoChipsPreview() {
    GestorGastosTheme {
        var seleccion by remember { mutableStateOf("Mensual") }
        GrupoChips(
            opciones = listOf("Mensual", "Quincenal", "Semanal"),
            seleccion = seleccion,
            etiqueta = { it },
            onSeleccion = { seleccion = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}
