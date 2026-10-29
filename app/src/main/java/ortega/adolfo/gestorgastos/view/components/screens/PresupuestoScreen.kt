package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.Categoria
import ortega.adolfo.gestorgastos.model.domain.ModoVista
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.view.components.Alertas
import ortega.adolfo.gestorgastos.view.components.BotonAgregar
import ortega.adolfo.gestorgastos.view.components.MontoPorcentaje
import ortega.adolfo.gestorgastos.view.components.ResumenPresupuesto
import ortega.adolfo.gestorgastos.view.components.SelectorCategoria
import ortega.adolfo.gestorgastos.view.components.TarjetaCategoria
import ortega.adolfo.gestorgastos.viewModel.PresupuestoState
import ortega.adolfo.gestorgastos.viewModel.PresupuestoViewModel
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.Superficie

@Composable
fun PresupuestoScreen(
    onAgregarIngreso: () -> Unit,
    onAgregarGastoFijo: () -> Unit,
    onAgregarCategoria: () -> Unit,
    onEditarCategoria: (Int) -> Unit = {},
    viewModel: PresupuestoViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    PresupuestoContenido(
        estado = estado,
        onMesCambio = viewModel::onMesCambio,
        onModoCambio = viewModel::onModoCambio,
        onAlertasCambio = viewModel::onAlertasCambio,
        onAgregarIngreso = onAgregarIngreso,
        onAgregarGastoFijo = onAgregarGastoFijo,
        onAgregarCategoria = onAgregarCategoria,
        onEditarCategoria = { categoria -> onEditarCategoria(categoria.id) },
        onEliminarCategoria = viewModel::onEliminarCategoria,
        onEliminarConfirmado = viewModel::onEliminarConfirmado,
        onEliminarCancelado = viewModel::onEliminarCancelado
    )
}

@Composable
fun PresupuestoContenido(
    estado: PresupuestoState,
    onMesCambio: (String) -> Unit,
    onModoCambio: (ModoVista) -> Unit,
    onAlertasCambio: (Boolean) -> Unit,
    onAgregarIngreso: () -> Unit,
    onAgregarGastoFijo: () -> Unit,
    onAgregarCategoria: () -> Unit,
    onEditarCategoria: (Categoria) -> Unit,
    onEliminarCategoria: (Categoria) -> Unit,
    onEliminarConfirmado: () -> Unit,
    onEliminarCancelado: () -> Unit
) {

    estado.categoriaPorEliminar?.let { categoria ->
        AlertDialog(
            onDismissRequest = onEliminarCancelado,
            containerColor = Superficie,
            title = {
                Text(
                    text = stringResource(R.string.categoria_eliminar_titulo),
                    fontWeight = FontWeight.Bold,
                    color = TextoPrincipal
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.categoria_eliminar_mensaje, categoria.nombre),
                    color = TextoSecundario
                )
            },
            confirmButton = {
                TextButton(onClick = onEliminarConfirmado) {
                    Text(
                        text = stringResource(R.string.categoria_eliminar_boton),
                        color = RojoError,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onEliminarCancelado) {
                    Text(text = stringResource(R.string.cancelar), color = TextoPrincipal)
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.presupuesto_titulo),
                style = MaterialTheme.typography.headlineLarge,
                color = TextoPrincipal
            )
            SelectorCategoria(
                seleccion = estado.mes,
                opciones = DatosMock.meses,
                onSeleccion = onMesCambio,
                modifier = Modifier.width(150.dp)
            )
        }
        Spacer(Modifier.height(16.dp))

        ResumenPresupuesto(
            disponible = estado.disponible,
            ingresosFijos = estado.ingresosFijos,
            gastosFijos = estado.gastosFijos,
            onAgregarIngreso = onAgregarIngreso,
            onAgregarGastoFijo = onAgregarGastoFijo
        )
        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.presupuesto_categorias),
                style = MaterialTheme.typography.headlineMedium,
                color = TextoPrincipal
            )
            MontoPorcentaje(
                opciones = ModoVista.entries,
                seleccion = estado.modo,
                etiqueta = { it.etiqueta },
                onSeleccion = onModoCambio
            )
        }
        Spacer(Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            estado.categorias.forEach { categoria ->
                TarjetaCategoria(
                    nombre = categoria.nombre,
                    gastado = categoria.gastado,
                    presupuesto = categoria.presupuesto,
                    modoPorcentaje = estado.modo == ModoVista.PORCENTAJE,
                    onEditar = { onEditarCategoria(categoria) },
                    onEliminar = { onEliminarCategoria(categoria) }
                )
            }
            BotonAgregar(
                texto = stringResource(R.string.presupuesto_agregar_categoria),
                onClick = onAgregarCategoria
            )
        }
        Spacer(Modifier.height(24.dp))

        Alertas(
            titulo = stringResource(R.string.presupuesto_alertas_titulo),
            descripcion = stringResource(R.string.presupuesto_alertas_descripcion),
            activo = estado.alertasActivas,
            onCambio = onAlertasCambio
        )
        Spacer(Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PresupuestoScreenPreview() {
    GestorGastosTheme {
        PresupuestoContenido(
            estado = PresupuestoState(
                ingresosFijos = 20000.0,
                gastosFijos = 7200.0,
                categorias = DatosMock.categorias.toList()
            ),
            onMesCambio = {},
            onModoCambio = {},
            onAlertasCambio = {},
            onAgregarIngreso = {},
            onAgregarGastoFijo = {},
            onAgregarCategoria = {},
            onEditarCategoria = {},
            onEliminarCategoria = {},
            onEliminarConfirmado = {},
            onEliminarCancelado = {}
        )
    }
}