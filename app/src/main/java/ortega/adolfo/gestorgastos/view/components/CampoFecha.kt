package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

private val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")

//este componente se puede usar para cualquier parte donde se use una fecha
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampoFecha(
    etiqueta: String,
    fecha: LocalDate?,
    onFechaSeleccionada: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    error: String? = null
){
    var mostrarCalendario by rememberSaveable { mutableStateOf(false) }

    CampoTexto(
        etiqueta = etiqueta,
        valor = fecha?.format(formatoFecha) ?: "",
        onValorCambio = {},
        placeholder = "dd/mm/aaaa",
        modifier = modifier,
        error = error,
        soloLectura = true,
        onClick = { mostrarCalendario = true }
    )
    if (mostrarCalendario) {
        val estadoCalendario = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { mostrarCalendario = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        estadoCalendario.selectedDateMillis?.let { millis ->
                            onFechaSeleccionada(
                                Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                            )
                        }
                        mostrarCalendario = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { mostrarCalendario = false }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = estadoCalendario)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CampoFechaPreview() {
    var fecha by remember { mutableStateOf<LocalDate?>(null) }
    Column(Modifier.padding(16.dp)) {
        CampoFecha(
            etiqueta = "Fecha de nacimiento",
            fecha = fecha,
            onFechaSeleccionada = { fecha = it }
        )
        Spacer(Modifier.height(12.dp))
    }
}
