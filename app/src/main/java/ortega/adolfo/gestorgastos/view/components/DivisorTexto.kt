package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario


//este componente se puede utilizar para dividir secciones
@Composable
fun DivisorTexto(texto: String, modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = TextoPrincipal)
        Text(
            text = texto,
            fontSize = 12.sp,
            color = TextoSecundario,
            modifier = Modifier.padding(horizontal = 10.dp)

        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = TextoPrincipal)
    }
}