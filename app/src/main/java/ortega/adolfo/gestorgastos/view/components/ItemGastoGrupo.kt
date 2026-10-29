package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale
import ortega.adolfo.gestorgastos.model.domain.GastoGrupo
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk

fun formatoMonto(monto: Double): String =
    "\$" + "%,.2f".format(Locale.US, monto)

@Composable
fun ItemGastoGrupo(
    gasto: GastoGrupo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        shadowElevation = 1.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = gasto.titulo,
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = TextoPrincipal
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "${gasto.fecha} · ${gasto.categoria}",
                    fontFamily = Manrope,
                    fontSize = 11.sp,
                    color = TextoSecundario
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = formatoMonto(gasto.monto),
                    fontFamily = SpaceGrotesk,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    color = TextoPrincipal
                )
                if (gasto.pagadoPor != null) {
                    Text(
                        text = "Pagado por ${gasto.pagadoPor}",
                        fontFamily = Manrope,
                        fontSize = 11.sp,
                        color = TextoSecundario
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = TextoPrincipal
            )
        }
    }
}