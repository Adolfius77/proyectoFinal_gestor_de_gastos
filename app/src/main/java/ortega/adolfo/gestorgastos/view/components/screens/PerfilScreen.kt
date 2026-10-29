package ortega.adolfo.gestorgastos.view.components.screens

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ortega.adolfo.gestorgastos.navigation.Destino
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.view.components.BarraNavegacion
import ortega.adolfo.gestorgastos.view.components.FilaOpcion
import ortega.adolfo.gestorgastos.view.components.FilaSwitch
import ortega.adolfo.gestorgastos.view.components.FotoPerfil
import ortega.adolfo.gestorgastos.view.components.TarjetaBase
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.BordeGris

@Composable
fun PerfilScreen(
    onInformacionPerfil: () -> Unit,
    modifier: Modifier = Modifier,
    nombre: String = "Andrea Samantha Melendrez Macias",
    correo: String = "andrea.melendrez@gmail.com",
    modoOscuro: Boolean = false,
    onModoOscuroCambio: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    var fotoUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var biometria by rememberSaveable { mutableStateOf(true) }

    val foto by produceState<ImageBitmap?>(initialValue = null, fotoUri) {
        value = fotoUri?.let { uri ->
            withContext(Dispatchers.IO) { cargarImagen(context, uri) }
        }
    }

    val selectorImagen = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) fotoUri = uri
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))

            FotoPerfil(
                imagen = foto,
                onClick = {
                    selectorImagen.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            )

            Spacer(Modifier.height(16.dp))
            Text(
                text = nombre,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Nunito,
                fontSize = 24.sp,
                color = TextoPrincipal
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = correo,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = Manrope,
                fontSize = 16.sp,
                color = TextoSecundario
            )

            Spacer(Modifier.height(28.dp))
            TarjetaBase {
                FilaOpcion(
                    icono = Icons.Outlined.ManageAccounts,
                    titulo = "Información de tu perfil",
                    onClick = onInformacionPerfil
                )
            }

            Spacer(Modifier.height(16.dp))
            TarjetaBase {
                FilaSwitch(
                    icono = Icons.Outlined.Fingerprint,
                    titulo = "Biometría",
                    activo = biometria,
                    onCambio = { biometria = it }
                )
                HorizontalDivider(color = BordeGris)
                FilaSwitch(
                    icono = Icons.Outlined.DarkMode,
                    titulo = "Modo oscuro",
                    activo = modoOscuro,
                    onCambio = onModoOscuroCambio
                )
            }

            Spacer(Modifier.height(24.dp))
        }

    }
}

private fun cargarImagen(context: Context, uri: Uri): ImageBitmap? = try {
    val opciones = BitmapFactory.Options().apply { inSampleSize = 4 }
    context.contentResolver.openInputStream(uri)?.use { entrada ->
        BitmapFactory.decodeStream(entrada, null, opciones)
    }?.asImageBitmap()
} catch (e: Exception) {
    null
}

@Composable
@Preview(showBackground = true, heightDp = 800)
fun PerfilScreenPreview() {
    GestorGastosTheme {
        PerfilScreen(onInformacionPerfil = {})
    }
}