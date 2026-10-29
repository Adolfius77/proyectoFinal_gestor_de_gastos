package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import ortega.adolfo.gestorgastos.model.domain.Frecuencia
import ortega.adolfo.gestorgastos.model.domain.IngresoFijo
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.util.comoMoneda
import ortega.adolfo.gestorgastos.view.components.BotonAgregar
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.view.components.EncabezadoPantalla
import ortega.adolfo.gestorgastos.view.components.GrupoChips
import ortega.adolfo.gestorgastos.view.components.TarjetaBase
import ortega.adolfo.gestorgastos.viewModel.PresupuestoIngresoFijoState
import ortega.adolfo.gestorgastos.viewModel.PresupuestoIngresoFijoViewModel

@Composable
fun PresupuestoIngresoFijoScreen(
    onVolver: () -> Unit,
    viewModel: PresupuestoIngresoFijoViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado.guardadoExitoso) {
        if (estado.guardadoExitoso) {
            viewModel.onGuardadoConsumido()
            onVolver()
        }
    }

    PresupuestoIngresoFijoContenido(
        estado = estado,
        onNombreCambio = viewModel::onNombreCambio,
        onMontoCambio = viewModel::onMontoCambio,
        onFrecuenciaCambio = viewModel::onFrecuenciaCambio,
        onDiaCobroCambio = viewModel::onDiaCobroCambio,
        onAgregarOtro = viewModel::agregarOtro,
        onEditar = viewModel::onEditar,
        onEliminar = viewModel::onEliminar,
        onGuardar = viewModel::guardar,
        onVolver = onVolver
    )
}

@Composable
fun PresupuestoIngresoFijoContenido(
    estado: PresupuestoIngresoFijoState,
    onNombreCambio: (String) -> Unit,
    onMontoCambio: (String) -> Unit,
    onFrecuenciaCambio: (Frecuencia) -> Unit,
    onDiaCobroCambio: (Int) -> Unit,
    onAgregarOtro: () -> Unit,
    onEditar: (IngresoFijo) -> Unit,
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
            titulo = stringResource(R.string.ingreso_titulo),
            subtitulo = stringResource(R.string.ingreso_subtitulo, estado.mes)
        )
        Spacer(Modifier.height(20.dp))

        CampoTexto(
            etiqueta = stringResource(R.string.ingreso_nombre_label),
            valor = estado.nombre,
            onValorCambio = onNombreCambio,
            placeholder = stringResource(R.string.ingreso_nombre_placeholder)
        )
        MensajeError(estado.errorNombre)
        Spacer(Modifier.height(20.dp))

        CampoTexto(
            etiqueta = stringResource(R.string.ingreso_monto_label),
            valor = estado.monto,
            onValorCambio = onMontoCambio,
            placeholder = stringResource(R.string.ingreso_monto_placeholder)
        )
        MensajeError(estado.errorMonto)
        Spacer(Modifier.height(20.dp))

        Etiqueta(stringResource(R.string.ingreso_frecuencia_label))
        Spacer(Modifier.height(8.dp))
        GrupoChips(
            opciones = Frecuencia.entries,
            seleccion = estado.frecuencia,
            etiqueta = { it.etiqueta },
            onSeleccion = onFrecuenciaCambio
        )
        Spacer(Modifier.height(20.dp))

        Etiqueta(stringResource(R.string.ingreso_dia_cobro_label))
        Spacer(Modifier.height(8.dp))
        SelectorDiaCobro(
            dia = estado.diaCobro,
            onDiaCambio = onDiaCobroCambio
        )
        Spacer(Modifier.height(24.dp))

        ListaIngresos(
            ingresos = estado.ingresos,
            total = estado.total,
            onEditar = onEditar,
            onEliminar = onEliminar
        )
        Spacer(Modifier.height(16.dp))

        BotonAgregar(
            texto = stringResource(R.string.ingreso_agregar_otro),
            onClick = onAgregarOtro
        )
        Spacer(Modifier.height(24.dp))

        BotonPrimario(
            texto = stringResource(R.string.ingreso_guardar),
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
private fun SelectorDiaCobro(
    dia: Int,
    onDiaCambio: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }
    val forma = RoundedCornerShape(12.dp)

    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(Color.White, forma)
                .border(1.dp, Color(0xFFDADDD9), forma)
                .clickable { expandido = true }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.ingreso_dia_cobro_valor, dia),
                fontSize = 15.sp,
                color = TextoPrincipal
            )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                tint = TextoPrincipal,
                modifier = Modifier.size(20.dp)
            )
        }
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            (1..31).forEach { d ->
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.ingreso_dia_cobro_valor, d)) },
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
private fun ListaIngresos(
    ingresos: List<IngresoFijo>,
    total: Double,
    onEditar: (IngresoFijo) -> Unit,
    onEliminar: (Int) -> Unit
) {
    TarjetaBase {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.ingreso_lista_titulo),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPrincipal
            )
            Text(
                text = "+" + total.comoMoneda(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = VerdePrimario
            )
        }
        Spacer(Modifier.height(8.dp))
        HorizontalDivider(color = Color(0xFFE4E6E3))

        ingresos.forEach { ingreso ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ingreso.nombre,
                    fontSize = 15.sp,
                    color = TextoPrincipal,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = ingreso.monto.comoMoneda(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                IconButton(onClick = { onEditar(ingreso) }, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.ingreso_editar),
                        tint = TextoPrincipal,
                        modifier = Modifier.size(18.dp)
                    )
                }
                IconButton(onClick = { onEliminar(ingreso.id) }, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.ingreso_eliminar),
                        tint = RojoError,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PresupuestoIngresoFijoPreview() {
    PresupuestoIngresoFijoContenido(
        estado = PresupuestoIngresoFijoState(
            ingresos = listOf(
                IngresoFijo(1, "Sueldo", 18000.0, Frecuencia.MENSUAL, 30)
            )
        ),
        onNombreCambio = {},
        onMontoCambio = {},
        onFrecuenciaCambio = {},
        onDiaCobroCambio = {},
        onAgregarOtro = {},
        onEditar = {},
        onEliminar = {},
        onGuardar = {},
        onVolver = {}
    )
}