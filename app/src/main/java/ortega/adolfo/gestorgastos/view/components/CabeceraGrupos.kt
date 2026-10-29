package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.VerdeWacho
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.VerdeAcento

@Composable
fun CabeceraGrupos(
    onUnirseGrupo: () -> Unit,
    onCrearGrupo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Grupos",
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = TextoPrincipal
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onUnirseGrupo) {
                Icon(
                    imageVector = Icons.Filled.Groups,
                    contentDescription = "Unirse a un grupo",
                    tint = VerdeAcento,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(Modifier.width(4.dp))
            Surface(
                onClick = onCrearGrupo,
                shape = CircleShape,
                color = VerdeAcento,
                border = BorderStroke(1.5.dp, VerdeWacho),
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Crear grupo",
                    tint = Color.White,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CabeceraGruposPreview() {
    GestorGastosTheme {
        CabeceraGrupos(onUnirseGrupo = {}, onCrearGrupo = {})
    }
}