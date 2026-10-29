package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
@Composable
fun FotoPerfil(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imagen: ImageBitmap? = null
) {
    Box(
        modifier = modifier
            .size(96.dp)
            .clip(CircleShape)
            .border(2.dp, TextoPrincipal, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (imagen != null) {
            Image(
                bitmap = imagen,
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Cambiar foto de perfil",
                tint = TextoPrincipal,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FotoPerfilPreview() {
    GestorGastosTheme {
        FotoPerfil(onClick = {})
    }
}