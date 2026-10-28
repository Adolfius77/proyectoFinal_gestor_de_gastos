package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.VerdeSuave

// Fila con icono, texto y un switch. Toda la fila es tocable, no solo el switch.
@Composable
fun FilaSwitch(
    icono: ImageVector,
    titulo: String,
    activo: Boolean,
    onCambio: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .toggleable(
                value = activo,
                role = Role.Switch,
                onValueChange = onCambio
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = TextoPrincipal,
            modifier = Modifier.size(28.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = titulo,
            fontFamily = Manrope,
            fontSize = 20.sp,
            color = TextoPrincipal,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = activo,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = TextoPrincipal,
                checkedTrackColor = VerdePrimario,
                checkedBorderColor = TextoPrincipal,
                uncheckedThumbColor = TextoPrincipal,
                uncheckedTrackColor = VerdeSuave,
                uncheckedBorderColor = TextoPrincipal
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FilaSwitchPreview() {
    var biometria by remember { mutableStateOf(true) }
    var oscuro by remember { mutableStateOf(false) }
    Column(Modifier.padding(16.dp)) {
        FilaSwitch(
            icono = Icons.Outlined.Fingerprint,
            titulo = "Biometría",
            activo = biometria,
            onCambio = { biometria = it }
        )
        FilaSwitch(
            icono = Icons.Outlined.DarkMode,
            titulo = "Modo oscuro",
            activo = oscuro,
            onCambio = { oscuro = it }
        )
    }
}