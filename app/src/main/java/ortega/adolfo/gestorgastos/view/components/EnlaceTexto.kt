package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme


@Composable
fun EnlaceTexto(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        color = VerdePrimario,
        fontSize = 13.sp,
        modifier = modifier
            .clickable { onClick() }
            .padding(vertical = 4.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun EnlaceTextoPreview() {
    GestorGastosTheme {
        EnlaceTexto(texto = "Agregar nueva tarjeta +", onClick = {})
    }
}