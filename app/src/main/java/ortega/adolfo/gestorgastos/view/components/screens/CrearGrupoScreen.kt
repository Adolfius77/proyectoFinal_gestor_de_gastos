package ortega.adolfo.gestorgastos.view.components.screens

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Undo
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdeWacho
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CampoDesplegable
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun CrearGrupoScreen(
    modifier: Modifier = Modifier,
    tipos: List<String> = listOf("Viaje", "Casa", "Pareja", "Amigos", "Trabajo", "Otro"),
    onVolver: () -> Unit = {},
    onCrear: (nombre: String, tipo: String, imagen: Uri?) -> Unit = { _, _, _ -> }
) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var imagen by remember { mutableStateOf<ImageBitmap?>(null) }
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorTipo by remember { mutableStateOf<String?>(null) }

    // Selector de galería (no necesita permisos)
    val selectorFoto = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri -> if (uri != null) imagenUri = uri }

    // Carga la imagen elegida fuera del hilo principal
    LaunchedEffect(imagenUri) {
        imagen = imagenUri?.let { uri ->
            withContext(Dispatchers.IO) {
                runCatching {
                    context.contentResolver.openInputStream(uri)?.use {
                        BitmapFactory.decodeStream(it)
                    }?.asImageBitmap()
                }.getOrNull()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
    ) {
        // Flecha de regreso
        IconButton(
            onClick = onVolver,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Undo,
                contentDescription = "Volver",
                tint = TextoPrincipal,
                modifier = Modifier.size(28.dp)
            )
        }

        // Título
        Text(
            text = "Crear grupo",
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = TextoPrincipal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        HorizontalDivider(color = Color.Black.copy(alpha = 0.08f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(28.dp))

            // Foto del grupo
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, VerdeWacho, CircleShape)
                    .clickable {
                        selectorFoto.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imagen != null) {
                    Image(
                        bitmap = imagen!!,
                        contentDescription = "Foto del grupo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.CameraAlt,
                        contentDescription = "Elegir foto",
                        tint = TextoPrincipal,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Elegir foto",
                fontSize = 14.sp,
                color = TextoSecundario
            )

            Spacer(Modifier.height(24.dp))

            // Campos
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                CampoTexto(
                    etiqueta = "Nombre del grupo",
                    valor = nombre,
                    onValorCambio = {
                        nombre = it
                        errorNombre = null
                    },
                    placeholder = "San Carlos 2030",
                    error = errorNombre
                )
                CampoDesplegable(
                    etiqueta = "Tipo de grupo",
                    seleccion = tipo,
                    opciones = tipos,
                    onSeleccion = {
                        tipo = it
                        errorTipo = null
                    },
                    placeholder = "Viaje",
                    error = errorTipo
                )
            }

            Spacer(Modifier.height(40.dp))

            BotonPrimario(
                texto = "Crear",
                onClick = {
                    errorNombre = if (nombre.isBlank()) "Escribe el nombre del grupo" else null
                    errorTipo = if (tipo.isBlank()) "Elige un tipo de grupo" else null
                    if (errorNombre == null && errorTipo == null) {
                        onCrear(nombre.trim(), tipo, imagenUri)
                    }
                },
                modifier = Modifier.fillMaxWidth(0.6f)
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CrearGrupoScreenPreview() {
    GestorGastosTheme {
        CrearGrupoScreen()
    }
}