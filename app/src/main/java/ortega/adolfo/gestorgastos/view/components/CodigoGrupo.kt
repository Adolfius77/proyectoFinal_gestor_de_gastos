package ortega.adolfo.gestorgastos.view.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun CodigoGrupo(
    codigo: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        onClick = {
            val portapapeles = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            portapapeles.setPrimaryClip(ClipData.newPlainText("Código del grupo", codigo))
            Toast.makeText(context, "Código copiado", Toast.LENGTH_SHORT).show()
        },
        shape = RoundedCornerShape(12.dp),
        color = Color.White.copy(alpha = 0.6f),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 26.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = codigo,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = TextoPrincipal
            )
            Spacer(Modifier.width(10.dp))
            Icon(
                imageVector = Icons.Outlined.ContentCopy,
                contentDescription = "Copiar código",
                tint = TextoPrincipal,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CodigoGrupoPreview() {
    GestorGastosTheme {
        CodigoGrupo("34RY6")
    }
}