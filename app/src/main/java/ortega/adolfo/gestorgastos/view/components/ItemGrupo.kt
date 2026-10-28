package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario

@Composable
private fun AvatarGrupo(imagen: Int?, modifier: Modifier = Modifier) {
    if (imagen != null) {
        Image(
            painter = painterResource(imagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(44.dp)
                .clip(CircleShape)
        )
    } else {
        Box(
            modifier = modifier
                .size(44.dp)
                .border(BorderStroke(1.5.dp, TextoSecundario), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Groups,
                contentDescription = null,
                tint = TextoSecundario,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ItemGrupo(
    grupo: Grupo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarGrupo(grupo.imagen)
        Spacer(Modifier.width(14.dp))
        Column {
            Text(
                text = grupo.nombre,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = TextoPrincipal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = grupo.ultimoMovimiento,
                fontFamily = Manrope,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = TextoSecundario,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemGrupoPreview() {
    ItemGrupo(
        grupo = Grupo(1, "Vicam 2026", "Dana Chavez: Pago \$5000.00 MXN"),
        onClick = {}
    )
}