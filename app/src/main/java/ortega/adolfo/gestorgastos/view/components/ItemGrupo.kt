package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.VerdeSuave

@Composable
fun AvatarGrupo(
    imagen: Int?,
    modifier: Modifier = Modifier,
    tamano: Dp = 44.dp
) {
    if (imagen != null) {
        Image(
            painter = painterResource(imagen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(tamano)
                .clip(CircleShape)
        )
    } else {
        Box(
            modifier = modifier
                .size(tamano)
                .border(BorderStroke(1.5.dp, TextoSecundario), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Groups,
                contentDescription = null,
                tint = TextoSecundario,
                modifier = Modifier.size(tamano / 2)
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
    val interactionSource = remember { MutableInteractionSource() }
    val presionado by interactionSource.collectIsPressedAsState()
    val fondo by animateColorAsState(
        targetValue = if (presionado) VerdePrimario.copy(alpha = 0.18f) else Color.Transparent,
        animationSpec = if (presionado) snap() else tween(durationMillis = 350),
        label = "fondoItemGrupo"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(fondo)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
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