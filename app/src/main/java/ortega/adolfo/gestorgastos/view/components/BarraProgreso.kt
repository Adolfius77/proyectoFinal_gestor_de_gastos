package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.ui.theme.Naranja
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun BarraProgreso(
    progreso: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(50))
            .background(Color(0xFFE4E6E3))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progreso.coerceIn(0f, 1f))
                .background(color)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BarraProgresoPreview() {
    GestorGastosTheme {
        Column(Modifier.padding(16.dp)) {
            BarraProgreso(progreso = 0.61f, color = VerdePrimario)
            Spacer(Modifier.height(12.dp))
            BarraProgreso(progreso = 0.85f, color = Naranja)
            Spacer(Modifier.height(12.dp))
            BarraProgreso(progreso = 1.08f, color = RojoError)
        }
    }
}
