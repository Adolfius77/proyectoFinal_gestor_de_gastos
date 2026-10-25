package ortega.adolfo.gestorgastos.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotonHuella(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icono: ImageVector? = null
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, Black),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Black
        ),
        modifier = modifier.fillMaxWidth().height(52.dp)
    ) {
        if (icono != null) {
            Icon(icono, contentDescription = null)
            Spacer(Modifier.width(8.dp))
        }
        Text(texto, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BotonHuellaPreview() {
    BotonHuella("Entrar con mi huella", onClick = {}, icono = Icons.Filled.Fingerprint)
}