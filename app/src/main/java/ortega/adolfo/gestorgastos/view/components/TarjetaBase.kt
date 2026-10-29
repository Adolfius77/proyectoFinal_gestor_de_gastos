package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.BordeGris
import ortega.adolfo.gestorgastos.ui.theme.Colores
import ortega.adolfo.gestorgastos.ui.theme.Superficie
import androidx.compose.foundation.BorderStroke
@Composable
fun TarjetaBase(
    modifier: Modifier = Modifier,
    contenido: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Superficie),
        border = if (Colores.esOscuro) BorderStroke(1.dp, BordeGris) else null,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = contenido
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TarjetaBasePreview() {
    GestorGastosTheme {
        Column(Modifier.padding(16.dp)) {
            TarjetaBase {
                Text("Contenido de ejemplo")
            }
        }
    }
}