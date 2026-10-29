package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.GrisPlaceholder
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.util.comoMoneda
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.view.components.EncabezadoPantalla
import ortega.adolfo.gestorgastos.view.components.GrupoChips
import ortega.adolfo.gestorgastos.viewModel.PresupuestoAgregarCategoriaState
import ortega.adolfo.gestorgastos.viewModel.PresupuestoAgregarCategoriaViewModel

@Composable
fun PresupuestoAgregarCategoriaScreen(
    onVolver: () -> Unit,
    viewModel: PresupuestoAgregarCategoriaViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado.guardadoExitoso) {
        if (estado.guardadoExitoso) {
            viewModel.onGuardadoConsumido()
            onVolver()
        }
    }

    PresupuestoAgregarCategoriaContenido(
        estado = estado,
        onNombreCambio = viewModel::onNombreCambio,
        onPresupuestoCambio = viewModel::onPresupuestoCambio,
        onGuardar = viewModel::guardar,
        onVolver = onVolver
    )
}

@Composable
fun PresupuestoAgregarCategoriaContenido(
    estado: PresupuestoAgregarCategoriaState,
    onNombreCambio: (String) -> Unit,
    onPresupuestoCambio: (String) -> Unit,
    onGuardar: () -> Unit,
    onVolver: () -> Unit
) {
    val sugeridaActiva = estado.sugeridas.firstOrNull { it.equals(estado.nombre.trim(), ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(16.dp))

        IconButton(onClick = onVolver) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.volver),
                tint = TextoPrincipal
            )
        }

        EncabezadoPantalla(
            titulo = stringResource(R.string.nueva_categoria_titulo),
            subtitulo = stringResource(R.string.nueva_categoria_subtitulo, estado.mes)
        )
        Spacer(Modifier.height(20.dp))

        CampoTexto(
            etiqueta = stringResource(R.string.nueva_categoria_nombre_label),
            valor = estado.nombre,
            onValorCambio = onNombreCambio,
            placeholder = stringResource(R.string.nueva_categoria_nombre_placeholder)
        )
        MensajeError(estado.errorNombre)
        Spacer(Modifier.height(20.dp))

        Etiqueta(stringResource(R.string.nueva_categoria_sugeridas))
        Spacer(Modifier.height(8.dp))
        GrupoChips(
            opciones = estado.sugeridas,
            seleccion = sugeridaActiva,
            etiqueta = { it },
            onSeleccion = onNombreCambio
        )
        Spacer(Modifier.height(20.dp))

        Etiqueta(stringResource(R.string.nueva_categoria_presupuesto_label))
        Spacer(Modifier.height(8.dp))
        CampoPresupuesto(
            valor = estado.presupuesto,
            onValorCambio = onPresupuestoCambio
        )
        MensajeError(estado.errorPresupuesto)
        Spacer(Modifier.height(8.dp))

        if (estado.restante >= 0) {
            Text(
                text = stringResource(R.string.nueva_categoria_restan, estado.restante.comoMoneda()),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = VerdePrimario
            )
        } else {
            Text(
                text = stringResource(R.string.nueva_categoria_excedido, (-estado.restante).comoMoneda()),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = RojoError
            )
        }
        Spacer(Modifier.height(32.dp))

        BotonPrimario(
            texto = stringResource(R.string.nueva_categoria_guardar),
            onClick = onGuardar
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun Etiqueta(texto: String) {
    Text(
        text = texto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = TextoPrincipal
    )
}

@Composable
private fun MensajeError(mensaje: String?) {
    if (mensaje != null) {
        Spacer(Modifier.height(4.dp))
        Text(text = mensaje, fontSize = 12.sp, color = RojoError)
    }
}

@Composable
private fun CampoPresupuesto(
    valor: String,
    onValorCambio: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val forma = RoundedCornerShape(12.dp)
    val estiloMonto = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = TextoPrincipal
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White, forma)
            .border(1.dp, Color(0xFFDADDD9), forma)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$", style = estiloMonto)
        Spacer(Modifier.width(4.dp))
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
            if (valor.isEmpty()) {
                Text(
                    text = stringResource(R.string.nueva_categoria_presupuesto_placeholder),
                    style = estiloMonto.copy(color = GrisPlaceholder)
                )
            }
            BasicTextField(
                value = valor,
                onValueChange = onValorCambio,
                textStyle = estiloMonto,
                singleLine = true,
                cursorBrush = SolidColor(VerdePrimario),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = stringResource(R.string.nueva_categoria_presupuesto_sufijo),
            fontSize = 13.sp,
            color = TextoSecundario
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PresupuestoAgregarCategoriaPreview() {
    PresupuestoAgregarCategoriaContenido(
        estado = PresupuestoAgregarCategoriaState(
            nombre = "Mascotas",
            presupuesto = "800",
            ingresosFijos = 18000.0,
            gastosFijos = 7200.0
        ),
        onNombreCambio = {},
        onPresupuestoCambio = {},
        onGuardar = {},
        onVolver = {}
    )
}
