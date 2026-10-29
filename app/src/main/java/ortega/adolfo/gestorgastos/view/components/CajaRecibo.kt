package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

// Caja para adjuntar una imagen (recibo, comprobante, etc.)
// Con adjunto = true muestra una palomita en lugar del icono de subir
@Composable
fun CajaRecibo(
    etiqueta: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    adjunto: Boolean = false
) {
    val forma = RoundedCornerShape(10.dp)

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = etiqueta,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = TextoPrincipal
        )
        Spacer(Modifier.size(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clip(forma)
                .background(FondoClaro)
                .border(1.dp, Color(0xFFDADDD9), forma)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = if (adjunto) Icons.Outlined.CheckCircle else Icons.Outlined.IosShare,
                    contentDescription = if (adjunto) "Recibo adjunto" else "Subir recibo",
                    tint = if (adjunto) VerdePrimario else TextoPrincipal,
                    modifier = Modifier.size(28.dp)
                )
                if (adjunto) {
                    Text("Recibo adjunto", fontSize = 13.sp, color = TextoSecundario)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CajaReciboPreview() {
    GestorGastosTheme {
        Column {
            CajaRecibo(etiqueta = "Foto del recibo (opcional)", onClick = {})
            Spacer(Modifier.height(12.dp))
            CajaRecibo(etiqueta = "Foto del recibo (opcional)", onClick = {}, adjunto = true)
        }
    }
}