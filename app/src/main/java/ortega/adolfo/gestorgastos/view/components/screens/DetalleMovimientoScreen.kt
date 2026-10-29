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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.MetodoPago
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.Tarjeta
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.ui.theme.Azul
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.RojoSuave
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.VerdeSuave
import ortega.adolfo.gestorgastos.utilities.formatoDineroCentavos
import ortega.adolfo.gestorgastos.utilities.formatoFechaCorta
import ortega.adolfo.gestorgastos.utilities.formatoFechaLarga
import ortega.adolfo.gestorgastos.utilities.formatoHora
import ortega.adolfo.gestorgastos.view.components.iconoCategoria
import ortega.adolfo.gestorgastos.viewModel.DetalleMovimientoViewModel
import java.time.LocalDate
import java.time.LocalTime




@Composable
fun DetalleMovimientoScreen(
    onRegresar: () -> Unit,
    onEditar: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetalleMovimientoViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()


    LaunchedEffect(estado.eliminado) {
        if (estado.eliminado) onRegresar()
    }

    val movimiento = estado.movimiento
    if (movimiento == null) {
        MovimientoNoEncontrado(onRegresar = onRegresar, modifier = modifier)
        return
    }

    DetalleMovimientoContenido(
        movimiento = movimiento,
        onRegresar = onRegresar,
        onEditar = { onEditar(movimiento.id) },
        onEliminar = viewModel::onEliminarClick,
        onVerFoto = {},
        modifier = modifier
    )

    if (estado.mostrarConfirmacion) {
        ConfirmarEliminar(
            descripcion = movimiento.descripcion,
            onConfirmar = viewModel::onConfirmarEliminar,
            onCancelar = viewModel::onCancelarEliminar
        )
    }
}


@Composable
fun DetalleMovimientoContenido(
    movimiento: Movimiento,
    onRegresar: () -> Unit,
    onEditar: () -> Unit,
    onEliminar: () -> Unit,
    onVerFoto: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        IconButton(onClick = onRegresar) {
            Icon(
                Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Regresar",
                tint = TextoPrincipal
            )
        }
        Text(
            text = stringResource(R.string.detalle_del_movimiento),
            style = MaterialTheme.typography.headlineMedium,
            color = TextoPrincipal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        TarjetaEncabezado(movimiento)
        Spacer(Modifier.height(14.dp))

        TarjetaDatos(movimiento)
        Spacer(Modifier.height(14.dp))

        TarjetaRecibo(foto = movimiento.fotoRecibo, onVerFoto = onVerFoto)
        Spacer(Modifier.height(18.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(
                onClick = onEditar,
                shape = RoundedCornerShape(14.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, VerdePrimario),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = VerdePrimario
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Icon(Icons.Outlined.Edit, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Editar", style = MaterialTheme.typography.labelLarge)
            }
            Button(
                onClick = onEliminar,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RojoSuave,
                    contentColor = RojoError
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Icon(Icons.Outlined.DeleteOutline, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.eliminar), style = MaterialTheme.typography.labelLarge)
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}


@Composable
private fun TarjetaEncabezado(movimiento: Movimiento) {
    val esGasto = movimiento.tipo == TipoMovimiento.GASTO

    TarjetaBlanca {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 22.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(VerdeSuave),
                contentAlignment = Alignment.Center
            ) {
                Text(iconoCategoria(movimiento.categoria), fontSize = 30.sp)
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = movimiento.descripcion,
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = TextoPrincipal
            )
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(VerdeSuave)
                    .padding(horizontal = 12.dp, vertical = 3.dp)
            ) {
                Text(
                    text = movimiento.categoria,
                    fontFamily = Manrope,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = VerdePrimario
                )
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = (if (esGasto) "-" else "+") + formatoDineroCentavos(movimiento.monto),
                fontFamily = SpaceGrotesk,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                color = if (esGasto) RojoError else VerdePrimario
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = (if (esGasto) "Gasto" else "Ingreso") + " · " + formatoFechaLarga(movimiento.fecha),
                fontFamily = Manrope,
                fontSize = 12.sp,
                color = TextoSecundario
            )
        }
    }
}

@Composable
private fun TarjetaDatos(movimiento: Movimiento) {
    val sinDato = "—"
    val esTarjeta = movimiento.metodoPago == MetodoPago.TARJETA
    val textoPago = when {
        esTarjeta && movimiento.tarjeta != null ->
            "${movimiento.tarjeta.tipo} •••• ${movimiento.tarjeta.terminacion}"
        esTarjeta -> "Tarjeta"
        else -> "Efectivo"
    }

    val filas = listOf(
        "Fecha" to formatoFechaCorta(movimiento.fecha),
        "Hora" to (movimiento.hora?.let { formatoHora(it) } ?: sinDato),
        "Ubicación" to (movimiento.ubicacion ?: sinDato),
        "Descripción" to (movimiento.nota ?: sinDato)
    )

    TarjetaBlanca {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            FilaDato(
                etiqueta = "Método de pago",
                valor = textoPago,
                icono = if (esTarjeta) Icons.Outlined.CreditCard else Icons.Outlined.Payments,
                colorIcono = if (esTarjeta) Azul else VerdePrimario
            )
            filas.forEach { (etiqueta, valor) ->
                HorizontalDivider(color = FondoClaro)
                FilaDato(etiqueta = etiqueta, valor = valor)
            }
        }
    }
}

@Composable
private fun FilaDato(
    etiqueta: String,
    valor: String,
    icono: ImageVector? = null,
    colorIcono: Color = TextoPrincipal
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = etiqueta,
            fontFamily = Manrope,
            fontSize = 13.sp,
            color = TextoSecundario,
            modifier = Modifier.weight(1f)
        )
        if (icono != null) {
            Icon(icono, contentDescription = null, tint = colorIcono, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(6.dp))
        }
        Text(
            text = valor,
            fontFamily = Manrope,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun TarjetaRecibo(foto: String?, onVerFoto: () -> Unit) {
    TarjetaBlanca {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.foto_del_recibo),
                    fontFamily = Manrope,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
                if (foto != null) {
                    Text(
                        text = stringResource(R.string.ver),
                        fontFamily = Manrope,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = VerdePrimario,
                        modifier = Modifier.clickable(onClick = onVerFoto)
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(FondoClaro),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.ReceiptLong,
                    contentDescription = null,
                    tint = TextoSecundario,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = foto ?: stringResource(R.string.sin_foto_del_recibo),
                    fontFamily = Manrope,
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
            }
        }
    }
}

@Composable
private fun TarjetaBlanca(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        content()
    }
}

@Composable
private fun ConfirmarEliminar(
    descripcion: String,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text(stringResource(R.string.eliminar_movimiento), fontFamily = Manrope, fontWeight = FontWeight.Bold) },
        text = {
            Text(
                "Se va a eliminar \"$descripcion\". Esta acción no se puede deshacer.",
                fontFamily = Manrope
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text("Eliminar", color = RojoError, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar", color = TextoPrincipal)
            }
        },
        containerColor = Color.White
    )
}

@Composable
private fun MovimientoNoEncontrado(onRegresar: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.este_movimiento_ya_no_existe),
            fontFamily = Manrope,
            fontSize = 16.sp,
            color = TextoSecundario
        )
        Spacer(Modifier.height(12.dp))
        TextButton(onClick = onRegresar) {
            Text(stringResource(R.string.regresar), color = VerdePrimario, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetalleMovimientoPreview() {
    GestorGastosTheme {
        DetalleMovimientoContenido(
            movimiento = Movimiento(
                id = 6,
                descripcion = "Supermercado",
                monto = 2100.0,
                fecha = LocalDate.of(2025, 10, 13),
                categoria = "Alimentación",
                tipo = TipoMovimiento.GASTO,
                metodoPago = MetodoPago.TARJETA,
                tarjeta = Tarjeta("Débito", "5778"),
                hora = LocalTime.of(18, 42),
                ubicacion = "Soriana Villa Fontana",
                nota = "Despensa de la semana",
                fotoRecibo = "ticket_soriana.jpg"
            ),
            onRegresar = {},
            onEditar = {},
            onEliminar = {},
            onVerFoto = {}
        )
    }
}
