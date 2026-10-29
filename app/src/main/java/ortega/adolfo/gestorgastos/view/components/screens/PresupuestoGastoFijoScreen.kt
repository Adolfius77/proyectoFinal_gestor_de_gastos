package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.GastoFijo
import ortega.adolfo.gestorgastos.model.domain.TipoGasto
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
import ortega.adolfo.gestorgastos.view.components.TarjetaBase
import ortega.adolfo.gestorgastos.viewModel.PresupuestoGastoFijoState
import ortega.adolfo.gestorgastos.viewModel.PresupuestoGastoFijoViewModel
import ortega.adolfo.gestorgastos.view.components.textoConNumeros
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.BordeGris
import ortega.adolfo.gestorgastos.ui.theme.VerdeAcento

@Composable
fun PresupuestoGastoFijoScreen(
    onVolver: () -> Unit,
    viewModel: PresupuestoGastoFijoViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado.guardadoExitoso) {
        if (estado.guardadoExitoso) {
            viewModel.onGuardadoConsumido()
            onVolver()
        }
    }

    PresupuestoGastoFijoContenido(
        estado = estado,
        onNombreCambio = viewModel::onNombreCambio,
        onMontoCambio = viewModel::onMontoCambio,
        onTipoCambio = viewModel::onTipoCambio,
        onDiaPagoCambio = viewModel::onDiaPagoCambio,
        onRecordarCambio = viewModel::onRecordarCambio,
        onEditar = viewModel::onEditar,
        onEliminar = viewModel::onEliminar,
        onGuardar = viewModel::guardar,
        onVolver = onVolver
    )
}

@Composable
fun PresupuestoGastoFijoContenido(
    estado: PresupuestoGastoFijoState,
    onNombreCambio: (String) -> Unit,
    onMontoCambio: (String) -> Unit,
    onTipoCambio: (TipoGasto) -> Unit,
    onDiaPagoCambio: (Int) -> Unit,
    onRecordarCambio: (Boolean) -> Unit,
    onEditar: (GastoFijo) -> Unit,
    onEliminar: (Int) -> Unit,
    onGuardar: () -> Unit,
    onVolver: () -> Unit
) {
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
            titulo = stringResource(R.string.gasto_titulo),

        )
        Spacer(Modifier.height(20.dp))

        CampoTexto(
            etiqueta = stringResource(R.string.gasto_nombre_label),
            valor = estado.nombre,
            onValorCambio = onNombreCambio,
            placeholder = stringResource(R.string.gasto_nombre_placeholder)
        )
        MensajeError(estado.errorNombre)
        Spacer(Modifier.height(20.dp))

        CampoTexto(
            etiqueta = stringResource(R.string.gasto_monto_label),
            valor = estado.monto,
            onValorCambio = onMontoCambio,
            placeholder = stringResource(R.string.gasto_monto_placeholder)
        )
        MensajeError(estado.errorMonto)
        Spacer(Modifier.height(20.dp))

        Etiqueta(stringResource(R.string.gasto_tipo_label))
        Spacer(Modifier.height(8.dp))
        GrupoChips(
            opciones = TipoGasto.entries,
            seleccion = estado.tipo,
            etiqueta = { it.etiqueta },
            onSeleccion = onTipoCambio
        )
        Spacer(Modifier.height(20.dp))

        SelectorDiaPago(
            dia = estado.diaPago,
            onDiaCambio = onDiaPagoCambio
        )
        Spacer(Modifier.height(12.dp))

        RecordatorioGasto(
            activo = estado.recordar,
            onCambio = onRecordarCambio
        )
        Spacer(Modifier.height(20.dp))

        ListaGastos(
            gastos = estado.gastos,
            total = estado.total,
            restante = estado.restante,
            onEditar = onEditar,
            onEliminar = onEliminar
        )
        Spacer(Modifier.height(24.dp))

        BotonPrimario(
            texto = stringResource(R.string.gasto_guardar),
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
private fun SelectorDiaPago(
    dia: Int,
    onDiaCambio: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxWidth()) {
        TarjetaBase(modifier = Modifier.clickable { expandido = true }) {
            Text(
                text = stringResource(R.string.gasto_dia_pago_label),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextoPrincipal
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = textoConNumeros(stringResource(R.string.gasto_dia_pago_valor, dia)),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeAcento
            )
        }
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            (1..31).forEach { d ->
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.gasto_dia_pago_valor, d)) },
                    onClick = {
                        onDiaCambio(d)
                        expandido = false
                    }
                )
            }
        }
    }
}

@Composable
private fun RecordatorioGasto(
    activo: Boolean,
    onCambio: (Boolean) -> Unit
) {
    TarjetaBase(modifier = Modifier.clickable { onCambio(!activo) }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.gasto_recordar),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextoPrincipal
            )
            Checkbox(
                checked = activo,
                onCheckedChange = onCambio,
                colors = CheckboxDefaults.colors(
                    checkedColor = VerdePrimario,
                    uncheckedColor = GrisPlaceholder,
                    checkmarkColor = Color.White
                )
            )
        }
    }
}

@Composable
private fun ListaGastos(
    gastos: List<GastoFijo>,
    total: Double,
    restante: Double,
    onEditar: (GastoFijo) -> Unit,
    onEliminar: (Int) -> Unit
) {
    TarjetaBase {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.gasto_lista_titulo),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
            Text(
                text = total.comoMoneda(),
                fontFamily = SpaceGrotesk,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
        }

        gastos.forEach { gasto ->
            Spacer(Modifier.height(4.dp))
            HorizontalDivider(color = BordeGris)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = gasto.nombre,
                    fontSize = 15.sp,
                    color = TextoPrincipal,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = gasto.monto.comoMoneda(),
                    fontFamily = SpaceGrotesk,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                IconButton(onClick = { onEditar(gasto) }, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.gasto_editar),
                        tint = TextoPrincipal,
                        modifier = Modifier.size(18.dp)
                    )
                }
                IconButton(onClick = { onEliminar(gasto.id) }, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.gasto_eliminar),
                        tint = RojoError,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(4.dp))
        Text(
            text = textoConNumeros(stringResource(R.string.gasto_restan, restante.comoMoneda())),
            fontSize = 12.sp,
            color = TextoSecundario
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PresupuestoGastoFijoPreview() {
    GestorGastosTheme {
        PresupuestoGastoFijoContenido(
            estado = PresupuestoGastoFijoState(
                ingresosFijos = 18000.0,
                gastos = listOf(
                    GastoFijo(1, "Renta", 5500.0, TipoGasto.VIVIENDA, 1, true),
                    GastoFijo(2, "Servicios", 1200.0, TipoGasto.SERVICIOS, 5, true),
                    GastoFijo(3, "Celular", 500.0, TipoGasto.OTRO, 10, false)
                )
            ),
            onNombreCambio = {},
            onMontoCambio = {},
            onTipoCambio = {},
            onDiaPagoCambio = {},
            onRecordarCambio = {},
            onEditar = {},
            onEliminar = {},
            onGuardar = {},
            onVolver = {}
        )
    }
}
