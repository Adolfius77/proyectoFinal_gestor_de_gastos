package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CampoDesplegable
import ortega.adolfo.gestorgastos.view.components.CampoFecha
import ortega.adolfo.gestorgastos.view.components.CampoPassword
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.viewModel.RegisterState
import ortega.adolfo.gestorgastos.viewModel.RegisterViewModel
import java.time.LocalDate
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun RegisterScreen(
    onRegisterExitoso: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado.registroExitoso) {
        if (estado.registroExitoso) {
            onRegisterExitoso()
            viewModel.onRegistroConsumido()
        }
    }

    RegisterContenido(
        estado = estado,
        onNombreCambio = viewModel::onNombreCambio,
        onCorreoCambio = viewModel::onCorreoCambio,
        onFechaCambio = viewModel::onFechaCambio,
        onGeneroCambio = viewModel::onGeneroCambio,
        onPasswordCambio = viewModel::onPasswordCambio,
        onConfirmarCambio = viewModel::onConfirmarCambio,
        onRegistrar = viewModel::registrar
    )
}

@Composable
fun RegisterContenido(
    estado: RegisterState,
    onNombreCambio: (String) -> Unit,
    onCorreoCambio: (String) -> Unit,
    onFechaCambio: (LocalDate) -> Unit,
    onGeneroCambio: (String) -> Unit,
    onPasswordCambio: (String) -> Unit,
    onConfirmarCambio: (String) -> Unit,
    onRegistrar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .systemBarsPadding()
            .imePadding()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.crea_tu_cuenta_titulo),
                style = MaterialTheme.typography.headlineMedium,
                color = TextoPrincipal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(20.dp))

            CampoTexto(
                etiqueta = stringResource(R.string.como_te_llamas),
                valor = estado.nombre,
                onValorCambio = onNombreCambio,
                placeholder = stringResource(R.string.nombre_y_apellido),
                error = estado.errorNombre
            )
            Spacer(Modifier.height(16.dp))

            CampoTexto(
                etiqueta = stringResource(R.string.correo_electr_nico),
                valor = estado.correo,
                onValorCambio = onCorreoCambio,
                placeholder = stringResource(R.string.tucorreo_ejemplo_com),
                error = estado.errorCorreo,
                tipoTeclado = KeyboardType.Email
            )
            Spacer(Modifier.height(16.dp))

            CampoFecha(
                etiqueta = stringResource(R.string.fecha_de_nacimiento),
                fecha = estado.fechaNacimiento,
                onFechaSeleccionada = onFechaCambio,
                error = estado.errorFecha
            )
            Spacer(Modifier.height(16.dp))

            CampoDesplegable(
                etiqueta = stringResource(R.string.genero),
                seleccion = estado.genero,
                opciones = DatosMock.generos,
                onSeleccion = onGeneroCambio,
                placeholder = stringResource(R.string.genero),
                error = estado.errorGenero
            )
            Spacer(Modifier.height(16.dp))

            CampoPassword(
                etiqueta = stringResource(R.string.contrase_a),
                valor = estado.password,
                onValorCambio = onPasswordCambio,
                placeholder = stringResource(R.string.crea_una_contrase_a),
                error = estado.errorPassword
            )
            Spacer(Modifier.height(16.dp))

            CampoPassword(
                etiqueta = stringResource(R.string.confirmar_contrase_a),
                valor = estado.confirmarPassword,
                onValorCambio = onConfirmarCambio,
                placeholder = stringResource(R.string.repite_tu_contrase_a),
                error = estado.errorConfirmar
            )

            estado.errorGeneral?.let { mensaje ->
                Spacer(Modifier.height(12.dp))
                Text(
                    text = mensaje,
                    color = RojoError,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(Modifier.height(16.dp))
        }

        BotonPrimario(
            texto = stringResource(R.string.registrarse),
            onClick = onRegistrar,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    GestorGastosTheme {
        RegisterContenido(
            estado = RegisterState(),
            onNombreCambio = {},
            onCorreoCambio = {},
            onFechaCambio = {},
            onGeneroCambio = {},
            onPasswordCambio = {},
            onConfirmarCambio = {},
            onRegistrar = {}
        )
    }
}