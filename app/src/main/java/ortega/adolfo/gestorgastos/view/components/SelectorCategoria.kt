package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
@Composable
fun SelectorCategoria(
    seleccion: String,
    opciones: List<String>,
    onSeleccion: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFDADDD9), RoundedCornerShape(10.dp))
                .clickable { expandido = true }
                .padding(start = 12.dp, end = 6.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = seleccion,
                style = MaterialTheme.typography.labelLarge,
                color = TextoPrincipal,
                textAlign = TextAlign.Center,

                maxLines = 1,


            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
                tint = TextoPrincipal,
                modifier = Modifier
                    .size(20.dp)
                    .rotate(if (expandido) 180f else 0f)
            )
        }

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            opciones.forEach { opcion ->
                val activo = opcion == seleccion
                DropdownMenuItem(
                    text = {
                        Text(
                            text = opcion,
                            color = if (activo) VerdePrimario else TextoPrincipal,
                            fontWeight = if (activo) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    onClick = {
                        onSeleccion(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SelectorCategoriaPreview() {
    var categoria by remember { mutableStateOf("Todas") }
    SelectorCategoria(
        seleccion = categoria,
        opciones = DatosMock.categoriasFiltro,
        onSeleccion = { categoria = it }
    )
}
