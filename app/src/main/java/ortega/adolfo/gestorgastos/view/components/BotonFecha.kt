package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale


private val formatoMes = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("es", "MX"))

private fun YearMonth.aTexto(): String =
    format(formatoMes).replaceFirstChar { it.uppercase() }


@Composable
fun BotonFecha(
    mes: YearMonth,
    onSeleccionar: (YearMonth) -> Unit,
    modifier: Modifier = Modifier,
    cantidadMeses: Int = 6
) {

    var expandido by remember { mutableStateOf(false) }


    val meses = remember(cantidadMeses) {
        (0 until cantidadMeses).map { YearMonth.now().minusMonths(it.toLong()) }
    }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFDADDD9), RoundedCornerShape(10.dp))
                .clickable { expandido = true }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = null,
                tint = TextoPrincipal,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = mes.aTexto(),
                style = MaterialTheme.typography.labelLarge,
                color = TextoPrincipal
            )
        }

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            meses.forEach { opcion ->
                val activo = opcion == mes
                DropdownMenuItem(
                    text = {
                        Text(
                            text = opcion.aTexto(),
                            color = if (activo) VerdePrimario else TextoPrincipal,
                            fontWeight = if (activo) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    onClick = {
                        onSeleccionar(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BotonFechaPreview() {
    var mes by remember { mutableStateOf(YearMonth.of(2026, 10)) }
    BotonFecha(mes = mes, onSeleccionar = { mes = it })
}
