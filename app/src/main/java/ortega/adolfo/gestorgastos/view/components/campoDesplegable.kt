package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


//este componente se usara para las listas desplegables como genero o por si tenemos que seleccionar algo plebes
@Composable
fun CampoDesplegable(
    etiqueta: String,
    seleccion: String,
    opciones: List<String>,
    onSeleccion: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    error: String? = null
) {
    var expandido by remember { mutableStateOf(false) }
    var anchoCampo by remember { mutableStateOf(0.dp) }
    val densidad = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned { anchoCampo = with(densidad) { it.size.width.toDp() } }
    ) {
        CampoTexto(
            etiqueta = etiqueta,
            valor = seleccion,
            onValorCambio = {},
            placeholder = placeholder,
            error = error,
            soloLectura = true,
            iconoFinal = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(if (expandido) 180f else 0f)
                )
            },
            onClick = { expandido = true }
        )

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false },
            modifier = Modifier.width(anchoCampo)
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onSeleccion(opcion)
                        expandido = false
                    }
                )
            }
        }
    }
}