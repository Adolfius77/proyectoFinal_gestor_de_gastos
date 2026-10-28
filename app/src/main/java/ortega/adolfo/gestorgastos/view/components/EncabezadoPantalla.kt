package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Undo
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal

@Composable
fun EncabezadoPantalla(
    titulo: String,
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        IconButton(onClick = onRegresar) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Undo,
                contentDescription = "Regresar",
                tint = TextoPrincipal
            )
        }
        Text(
            text = titulo,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EncabezadoPantallaPreview() {
    EncabezadoPantalla(titulo = "Nuevo movimiento", onRegresar = {})
}