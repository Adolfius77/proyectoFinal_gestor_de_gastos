package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal


@Composable
fun CabeceraInicio(
    nombre: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = null,
            tint = TextoPrincipal,
            modifier = Modifier.size(44.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = "Hola, $nombre",
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            color = TextoPrincipal
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CabeceraInicioPreview() {
    CabeceraInicio(nombre = "Andrea")
}
