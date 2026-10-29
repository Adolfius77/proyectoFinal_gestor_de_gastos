package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario

// Fila con icono a la izquierda, texto (y subtitulo opcional) y flecha a la derecha
@Composable
fun FilaOpcion(
    icono: ImageVector,
    titulo: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    subtitulo: String? = null,
    mostrarFlecha: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = TextoPrincipal,
            modifier = Modifier.size(28.dp)
        )
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = titulo,
                fontFamily = Manrope,
                fontSize = 17.sp,
                color = TextoPrincipal
            )
            if (subtitulo != null) {
                Text(
                    text = subtitulo,
                    fontFamily = Manrope,
                    fontSize = 14.sp,
                    color = TextoSecundario,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
        if (mostrarFlecha) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = null,
                tint = TextoSecundario,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FilaOpcionPreview() {
    Column(Modifier.padding(16.dp)) {
        FilaOpcion(
            icono = Icons.Outlined.Badge,
            titulo = "Andrea Samantha Melendrez Macias",
            subtitulo = "Nombre y apellido.",
            onClick = {}
        )
        FilaOpcion(
            icono = Icons.Outlined.Badge,
            titulo = "Sin flecha",
            subtitulo = "Fila sin flecha a la derecha",
            mostrarFlecha = false,
            onClick = {}
        )
    }
}