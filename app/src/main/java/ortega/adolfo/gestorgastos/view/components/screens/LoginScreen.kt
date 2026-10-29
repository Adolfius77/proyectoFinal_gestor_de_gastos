package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.view.components.BotonHuella
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CabeceraLogin
import ortega.adolfo.gestorgastos.view.components.CampoPassword
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.view.components.DivisorTexto
import ortega.adolfo.gestorgastos.viewModel.LoginState
import ortega.adolfo.gestorgastos.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginExitoso: () -> Unit,
    onIrARegistro: () -> Unit,
    viewModel: LoginViewModel = viewModel()
){
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado.loginExitoso) {
        if(estado.loginExitoso) onLoginExitoso()
    }

    LoginContenido(
        estado = estado,
        onCorreoCambio = viewModel::onCorreoCambio,
        onPasswordCambio = viewModel::onPasswordCambio,
        onIniciarSesion = viewModel::iniciarSesion,
        onIrARegistro = onIrARegistro
    )
}

@Composable
fun LoginContenido(
    estado: LoginState,
    onCorreoCambio: (String) -> Unit,
    onPasswordCambio: (String) -> Unit,
    onIniciarSesion: () -> Unit,
    onIrARegistro: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .systemBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))
            CabeceraLogin()
            Spacer(Modifier.height(20.dp))

            Text(
                text = stringResource(R.string.hola_de_nuevo),
                style = MaterialTheme.typography.headlineMedium,
                color = TextoPrincipal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.entra_para_ver_los_gastos_de_este_mes),
                fontSize = 14.sp,
                color = TextoSecundario
            )
            Spacer(Modifier.height(28.dp))

            CampoTexto(
                etiqueta = stringResource(R.string.correo_electr_nico),
                valor = estado.correo,
                onValorCambio = onCorreoCambio,
                placeholder = stringResource(R.string.tucorreo_ejemplo_com),
                error = estado.errorCorreo,
                tipoTeclado = KeyboardType.Email
            )
            Spacer(Modifier.height(16.dp))

            CampoPassword(
                etiqueta = stringResource(R.string.contrase_a),
                valor = estado.password,
                onValorCambio = onPasswordCambio,
                placeholder = stringResource(R.string.tu_contrase_a),
                error = estado.errorPassword
            )
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    stringResource(R.string.olvidaste_tu_contrase_a),
                    color = VerdePrimario,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            estado.errorGeneral?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = RojoError,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            BotonPrimario(stringResource(R.string.iniciar_sesi_n), onClick = onIniciarSesion)
            Spacer(Modifier.height(24.dp))
            DivisorTexto(stringResource(R.string.o_entra_m_s_r_pido))
            Spacer(Modifier.height(24.dp))
            BotonHuella(
                stringResource(R.string.entrar_con_mi_huella),
                onClick = {  },
                icono = Icons.Filled.Fingerprint
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(stringResource(R.string.eres_nuevo_aqu), fontSize = 14.sp, color = TextoPrincipal)
            Text(
                stringResource(R.string.crea_tu_cuenta),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = VerdePrimario,
                modifier = Modifier.clickable(onClick = onIrARegistro)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginContenido(
        estado = LoginState(),
        onCorreoCambio = {},
        onPasswordCambio = {},
        onIniciarSesion = {},
        onIrARegistro = {}
    )
}
