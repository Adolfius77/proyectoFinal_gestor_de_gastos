package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.PhoneInTalk
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.navigation.Destino
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.view.components.BarraNavegacion
import ortega.adolfo.gestorgastos.view.components.FilaOpcion
import ortega.adolfo.gestorgastos.view.components.TarjetaBase
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.BordeGris

@Composable
fun InformacionPerfilScreen(
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier,
    nombre: String = "Andrea Samantha Melendrez Macias",
    domicilio: String = "Domicilio",
    telefono: String = "6441434343",
    correo: String = "andrea.melendrez@gmail.com",
    onNombre: () -> Unit = {},
    onDomicilio: () -> Unit = {},
    onTelefono: () -> Unit = {}
) {
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
        ) {
            Spacer(Modifier.height(8.dp))
            IconButton(onClick = onRegresar) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.volver),
                    tint = TextoPrincipal
                )
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Información de tu perfil",
                    fontFamily = Nunito,
                    fontSize = 28.sp,
                    color = TextoPrincipal
                )

                Spacer(Modifier.height(24.dp))
                Text(
                    text = "Información personal",
                    fontFamily = Manrope,
                    fontSize = 18.sp,
                    color = TextoSecundario
                )

                Spacer(Modifier.height(12.dp))
                TarjetaBase {
                    FilaOpcion(
                        icono = Icons.Outlined.Badge,
                        titulo = nombre,
                        subtitulo = "Nombre y apellido.",
                        onClick = onNombre
                    )
                    DivisorFila()
                    FilaOpcion(
                        icono = Icons.Outlined.LocationOn,
                        titulo = domicilio,
                        subtitulo = "Domicilio particular.",
                        onClick = onDomicilio
                    )
                    DivisorFila()
                    FilaOpcion(
                        icono = Icons.Outlined.PhoneInTalk,
                        titulo = telefono,
                        subtitulo = "Número donde recibes códigos de verificación y comunicaciones",
                        onClick = onTelefono
                    )
                    DivisorFila()
                    FilaOpcion(
                        icono = Icons.Outlined.Email,
                        titulo = correo,
                        subtitulo = "E-mail donde recibes comunicaciones",
                        mostrarFlecha = false,
                        onClick = {}
                    )
                }

                Spacer(Modifier.height(24.dp))
            }
        }

    }
}

@Composable
private fun DivisorFila() {
    HorizontalDivider(
        modifier = Modifier.padding(start = 44.dp),
        color = BordeGris
    )
}

@Composable
@Preview(showBackground = true, heightDp = 800)
fun InformacionPerfilScreenPreview() {
    GestorGastosTheme {
        InformacionPerfilScreen(onRegresar = {})
    }
}