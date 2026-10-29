package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.model.domain.MetodoPago
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.ui.theme.Azul
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.Morado
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.utilities.formatoDinero
import ortega.adolfo.gestorgastos.utilities.formatoFechaCorta
import java.time.LocalDate
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme


fun iconoCategoria(categoria: String): String = when (categoria) {
    "Vivienda" -> "🏠"
    "Alimentación" -> "🛒"
    "Transporte" -> "🚗"
    "Entretenimiento" -> "🎬"
    "Salud" -> "💊"
    "Sueldo", "Salario" -> "💼"
    "Inversiones" -> "📈"
    "Freelance" -> "💻"
    else -> "🛍️"
}


@Composable
fun ItemMovimiento(
    movimiento: Movimiento,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val esGasto = movimiento.tipo == TipoMovimiento.GASTO

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(FondoClaro),
            contentAlignment = Alignment.Center
        ) {
            Text(iconoCategoria(movimiento.categoria), fontSize = 20.sp)
        }
        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = movimiento.descripcion,
                fontFamily = Manrope,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = formatoFechaCorta(movimiento.fecha),
                    fontFamily = Manrope,
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
                Spacer(Modifier.width(6.dp))
                val (iconoPago, colorPago) = when (movimiento.metodoPago) {
                    MetodoPago.TARJETA -> Icons.Outlined.CreditCard to Azul
                    MetodoPago.EFECTIVO -> Icons.Outlined.Payments to VerdePrimario
                    MetodoPago.TRANSFERENCIA -> Icons.Outlined.SwapHoriz to Morado
                }
                Icon(
                    imageVector = iconoPago,
                    contentDescription = movimiento.metodoPago.etiqueta,
                    tint = colorPago,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Spacer(Modifier.width(8.dp))

        Text(
            text = (if (esGasto) "-" else "+") + formatoDinero(movimiento.monto),
            fontFamily = SpaceGrotesk,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (esGasto) RojoError else VerdePrimario
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ItemMovimientoPreview() {
    GestorGastosTheme {
        ItemMovimiento(
            Movimiento(
                id = 1,
                descripcion = "Supermercado",
                monto = 2100.0,
                fecha = LocalDate.of(2026, 10, 13),
                categoria = "Alimentación",
                tipo = TipoMovimiento.GASTO,
                metodoPago = MetodoPago.TARJETA
            )
        )
    }
}
