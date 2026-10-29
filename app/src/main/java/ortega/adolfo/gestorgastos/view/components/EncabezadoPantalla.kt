package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun EncabezadoPantalla(
    titulo: String,
    modifier: Modifier = Modifier,
    subtitulo: String? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = titulo,
            fontFamily = Nunito,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )
        if (subtitulo != null) {
            Text(
                text = subtitulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                color = TextoSecundario
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun EncabezadoPantallaPreview() {
    GestorGastosTheme {
        EncabezadoPantalla(
            titulo = "Nuevo movimiento",
            subtitulo = "Registra tus ingresos y gastos"
        )
    }
}