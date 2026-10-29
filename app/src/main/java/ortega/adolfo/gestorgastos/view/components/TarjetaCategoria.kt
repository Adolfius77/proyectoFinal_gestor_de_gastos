package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.Naranja
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.util.comoMoneda
import kotlin.math.roundToInt

// Es lo que hace que la barra se pinte de color cuando llega el 80%
private const val UMBRAL_ALERTA = 0.8f

@Composable
fun TarjetaCategoria(
    nombre: String,
    gastado: Double,
    presupuesto: Double,
    modifier: Modifier = Modifier,
    modoPorcentaje: Boolean = false,
    onEditar: (() -> Unit)? = null,
    onEliminar: (() -> Unit)? = null
) {
    val progreso = if (presupuesto > 0) (gastado / presupuesto).toFloat() else 0f
    val porcentaje = (progreso * 100).roundToInt()
    val excedido = gastado > presupuesto
    val color = when {
        excedido -> RojoError
        progreso >= UMBRAL_ALERTA -> Naranja
        else -> VerdePrimario
    }

    val textoIzquierda: String
    val textoDerecha: String
    if (modoPorcentaje) {
        textoIzquierda = stringResource(R.string.categoria_usado, "$porcentaje%")
        textoDerecha = if (excedido) {
            stringResource(R.string.categoria_excedido, "${porcentaje - 100}%")
        } else {
            stringResource(R.string.categoria_quedan, "${100 - porcentaje}%")
        }
    } else {
        textoIzquierda = stringResource(
            R.string.categoria_gastado_de,
            gastado.comoMoneda(),
            presupuesto.comoMoneda()
        )
        textoDerecha = if (excedido) {
            stringResource(R.string.categoria_excedido, (gastado - presupuesto).comoMoneda())
        } else {
            stringResource(R.string.categoria_quedan, (presupuesto - gastado).comoMoneda())
        }
    }

    TarjetaBase(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "$porcentaje%",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = color
            )
            if (onEditar != null || onEliminar != null) {
                Spacer(Modifier.width(4.dp))
            }
            if (onEditar != null) {
                IconButton(onClick = onEditar, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.categoria_editar),
                        tint = TextoPrincipal,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            if (onEliminar != null) {
                IconButton(onClick = onEliminar, modifier = Modifier.size(32.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.categoria_eliminar),
                        tint = RojoError,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        BarraProgreso(progreso = progreso, color = color)
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = textoIzquierda,
                fontSize = 12.sp,
                color = TextoSecundario
            )
            Text(
                text = textoDerecha,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (excedido) RojoError else VerdePrimario
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TarjetaCategoriaPreview() {
    Column(Modifier.padding(16.dp)) {
        TarjetaCategoria("Comida", gastado = 2975.0, presupuesto = 3500.0, onEditar = {}, onEliminar = {})
        Spacer(Modifier.height(12.dp))
        TarjetaCategoria("Transporte", gastado = 1100.0, presupuesto = 1800.0, onEditar = {}, onEliminar = {})
        Spacer(Modifier.height(12.dp))
        TarjetaCategoria(
            "Ocio",
            gastado = 1620.0,
            presupuesto = 1500.0,
            modoPorcentaje = true,
            onEditar = {},
            onEliminar = {}
        )
    }
}
