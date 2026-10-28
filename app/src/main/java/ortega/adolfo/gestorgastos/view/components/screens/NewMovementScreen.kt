package ortega.adolfo.gestorgastos.view.components.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CajaRecibo
import ortega.adolfo.gestorgastos.view.components.CampoDesplegable
import ortega.adolfo.gestorgastos.view.components.CampoFecha
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.view.components.EncabezadoPantalla
import ortega.adolfo.gestorgastos.view.components.EnlaceTexto
import ortega.adolfo.gestorgastos.view.components.SelectorTipoMovimiento
import ortega.adolfo.gestorgastos.viewModel.NuevoMovimientoViewModel
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.navigation.Destino
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.view.components.BarraNavegacion

@Composable
fun NewMovementScreen(
    onRegresar: () -> Unit,
    onGuardado: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NuevoMovimientoViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(estado.guardadoExitoso) {
        if (estado.guardadoExitoso) {
            onGuardado()
            viewModel.onGuardadoConsumido()
        }
    }

    val selectorImagen = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let { viewModel.onReciboSeleccionado(it.toString()) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            EncabezadoPantalla(
                titulo = "Nuevo movimiento",
                onRegresar = onRegresar,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                // Tipo: Ingreso / Gasto
                Text(
                    text = "Tipo",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = TextoPrincipal
                )
                Spacer(Modifier.height(6.dp))
                SelectorTipoMovimiento(
                    seleccionado = estado.tipo,
                    onSeleccionar = viewModel::onTipoCambio
                )

                Spacer(Modifier.height(16.dp))
                CampoTexto(
                    etiqueta = "Monto",
                    valor = estado.monto,
                    onValorCambio = viewModel::onMontoCambio,
                    placeholder = "$0.00",
                    error = estado.errorMonto,
                    tipoTeclado = KeyboardType.Decimal
                )

                Spacer(Modifier.height(16.dp))
                CampoTexto(
                    etiqueta = "Descripción",
                    valor = estado.descripcion,
                    onValorCambio = viewModel::onDescripcionCambio,
                    placeholder = "Descripción del movimiento",
                    error = estado.errorDescripcion
                )

                Spacer(Modifier.height(16.dp))
                CampoDesplegable(
                    etiqueta = "Categoría",
                    seleccion = estado.categoria,
                    opciones = estado.categorias,
                    onSeleccion = viewModel::onCategoriaCambio,
                    placeholder = "Selecciona una categoría",
                    error = estado.errorCategoria
                )

                // Tipo de pago y Tarjeta: solo se muestran en Gasto
                if (estado.tipo == TipoMovimiento.GASTO) {
                    Spacer(Modifier.height(16.dp))
                    CampoDesplegable(
                        etiqueta = "Tipo de pago",
                        seleccion = estado.tipoPago,
                        opciones = DatosMock.tiposPago,
                        onSeleccion = viewModel::onTipoPagoCambio,
                        placeholder = "Selecciona el tipo de pago"
                    )

                    if (estado.pagaConTarjeta) {
                        Spacer(Modifier.height(16.dp))
                        CampoDesplegable(
                            etiqueta = "Tarjeta",
                            seleccion = estado.tarjeta,
                            opciones = estado.tarjetas,
                            onSeleccion = viewModel::onTarjetaCambio,
                            placeholder = "Selecciona una tarjeta",
                            error = estado.errorTarjeta
                        )
                        EnlaceTexto(
                            texto = "Agregar nueva tarjeta +",
                            onClick = viewModel::abrirDialogoTarjeta
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))
                CampoTexto(
                    etiqueta = "Ubicación",
                    valor = estado.ubicacion,
                    onValorCambio = viewModel::onUbicacionCambio,
                    placeholder = "Escribe o usa tu ubicación",
                    iconoFinal = {
                        IconButton(onClick = viewModel::onUsarUbicacionActual) {
                            Icon(
                                imageVector = Icons.Outlined.LocationOn,
                                contentDescription = "Usar mi ubicación"
                            )
                        }
                    }
                )

                Spacer(Modifier.height(16.dp))
                CajaRecibo(
                    etiqueta = "Foto del recibo (opcional)",
                    adjunto = estado.reciboUri != null,
                    onClick = {
                        selectorImagen.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                )

                Spacer(Modifier.height(16.dp))
                CampoFecha(
                    etiqueta = "Fecha",
                    fecha = estado.fecha,
                    onFechaSeleccionada = viewModel::onFechaCambio,
                    error = estado.errorFecha,
                    iconoFinal = {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = null
                        )
                    }
                )

                Spacer(Modifier.height(24.dp))
                BotonPrimario(
                    texto = "Guardar",
                    onClick = viewModel::guardar,
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
                Spacer(Modifier.height(16.dp))
            }
        }

        // Barra de navegacion (solo visual, sin funcionalidad por ahora)
        BarraNavegacion(
            actual = Destino.INICIO,
            onNavegar = {}
        )
    }

    if (estado.mostrarDialogoTarjeta) {
        AlertDialog(
            onDismissRequest = viewModel::cerrarDialogoTarjeta,
            title = { Text("Nueva tarjeta") },
            text = {
                CampoTexto(
                    etiqueta = "Nombre de la tarjeta",
                    valor = estado.nuevaTarjeta,
                    onValorCambio = viewModel::onNuevaTarjetaCambio,
                    placeholder = "Ej. BBVA 7789",
                    error = estado.errorNuevaTarjeta
                )
            },
            confirmButton = {
                TextButton(onClick = viewModel::agregarTarjeta) { Text("Agregar") }
            },
            dismissButton = {
                TextButton(onClick = viewModel::cerrarDialogoTarjeta) {
                    Text(stringResource(R.string.cancelar))
                }
            }
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 1100)
fun NewMovementScreenPreview() {
    NewMovementScreen(onRegresar = {}, onGuardado = {})
}