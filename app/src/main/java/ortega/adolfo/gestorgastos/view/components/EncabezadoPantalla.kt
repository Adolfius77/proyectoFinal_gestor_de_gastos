package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
@Composable
fun EncabezadoPantalla(
    titulo: String,
    subtitulo: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = subtitulo,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextoSecundario
        )
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineMedium,
            color = TextoPrincipal
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EncabezadoPantallaPreview() {
    Column(Modifier.padding(16.dp)) {
        EncabezadoPantalla(
            titulo = "Agregar ingreso fijo",
            subtitulo = "Presupuesto · Octubre"
        )
    }
}