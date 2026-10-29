package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.BordeGris
import ortega.adolfo.gestorgastos.ui.theme.GrisPlaceholder
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.Superficie



@Composable
fun BarraBusqueda(
    texto: String,
    onTextoCambio: (String) -> Unit,
    placeholder: String,
    onCalendario: () -> Unit,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(12.dp)

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = texto,
            onValueChange = onTextoCambio,
            placeholder = { Text(placeholder, color = GrisPlaceholder) },
            trailingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Buscar", tint = TextoPrincipal) },
            singleLine = true,
            shape = forma,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Superficie,
                unfocusedContainerColor = Superficie,
                focusedBorderColor = VerdePrimario,
                unfocusedBorderColor = BordeGris,
                cursorColor = VerdePrimario
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(forma)
                .background(Superficie)
                .border(1.dp, BordeGris, forma)
                .clickable(onClick = onCalendario),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.CalendarMonth,
                contentDescription = stringResource(R.string.filtrar_por_fecha),
                tint = TextoPrincipal
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BarraBusquedaPreview() {
    GestorGastosTheme {
        var texto by remember { mutableStateOf("") }
        BarraBusqueda(
            texto = texto,
            onTextoCambio = { texto = it },
            placeholder = "Buscar gastos",
            onCalendario = {}
        )
    }
}
