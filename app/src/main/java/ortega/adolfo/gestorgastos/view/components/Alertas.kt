package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.GrisPlaceholder
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
@Composable
fun Alertas(
    titulo: String,
    descripcion: String,
    activo: Boolean,
    onCambio: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    TarjetaBase(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = descripcion,
                    fontSize = 13.sp,
                    color = TextoSecundario
                )
            }
            Spacer(Modifier.width(12.dp))
            Switch(
                checked = activo,
                onCheckedChange = onCambio,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = VerdePrimario,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = GrisPlaceholder,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AlertasPreview() {
    var activo by remember { mutableStateOf(true) }
    Column(Modifier.padding(16.dp)) {
        Alertas(
            titulo = "Alertas",
            descripcion = "Avísame al superar el 80% de cada categoría",
            activo = activo,
            onCambio = { activo = it }
        )
    }
}