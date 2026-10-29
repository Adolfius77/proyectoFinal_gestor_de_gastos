package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.ResumenCategoria
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.view.components.BarraBusqueda
import ortega.adolfo.gestorgastos.view.components.CabeceraInicio
import ortega.adolfo.gestorgastos.view.components.FiltrosCategoria
import ortega.adolfo.gestorgastos.view.components.SelectorTipoMovimiento
import ortega.adolfo.gestorgastos.view.components.TarjetaBalance
import ortega.adolfo.gestorgastos.view.components.TarjetaCategorias
import ortega.adolfo.gestorgastos.view.components.TarjetaMovimientos
import ortega.adolfo.gestorgastos.viewModel.FILTRO_TODOS
import ortega.adolfo.gestorgastos.viewModel.HomeState
import ortega.adolfo.gestorgastos.viewModel.HomeViewModel
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme


@Composable
fun HomeScreen(
    onVerGraficas: () -> Unit,
    onAgregar: () -> Unit,
    onVerMovimiento: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val estado by viewModel.estado.collectAsState()

    LaunchedEffect(Unit) { viewModel.recargar() }

    HomeContenido(
        estado = estado,
        onTipoCambio = viewModel::onTipoCambio,
        onBusquedaCambio = viewModel::onBusquedaCambio,
        onFiltroCambio = viewModel::onFiltroCambio,
        onVerGraficas = onVerGraficas,
        onAgregar = onAgregar,
        onVerTodos = {},
        onCalendario = {},
        onMovimientoClick = { onVerMovimiento(it.id) },
        modifier = modifier
    )
}


@Composable
fun HomeContenido(
    estado: HomeState,
    onTipoCambio: (TipoMovimiento) -> Unit,
    onBusquedaCambio: (String) -> Unit,
    onFiltroCambio: (String) -> Unit,
    onVerGraficas: () -> Unit,
    onAgregar: () -> Unit,
    onVerTodos: () -> Unit,
    onCalendario: () -> Unit,
    modifier: Modifier = Modifier,
    onMovimientoClick: (Movimiento) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item { CabeceraInicio(nombre = estado.nombreUsuario) }

            item {
                TarjetaBalance(
                    queda = estado.quedaEsteMes,
                    ingresos = estado.ingresosMes,
                    gastos = estado.gastosMes,
                    diasRestantes = estado.diasRestantes,
                    gastoDiario = estado.gastoDiarioSugerido,
                    porcentajeUsado = estado.porcentajeUsado,
                    onVerDetalles = onVerGraficas
                )
            }

            item {
                TarjetaCategorias(
                    categorias = estado.categoriasGasto,
                    onVerGraficas = onVerGraficas
                )
            }

            item {
                Column {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Tus movimientos",
                        style = MaterialTheme.typography.headlineMedium,
                        color = TextoPrincipal,
                        fontFamily = Manrope,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(12.dp))
                    SelectorTipoMovimiento(
                        seleccionado = estado.tipo,
                        onSeleccionar = onTipoCambio
                    )
                }
            }

            item {
                BarraBusqueda(
                    texto = estado.busqueda,
                    onTextoCambio = onBusquedaCambio,
                    placeholder = if (estado.tipo == TipoMovimiento.GASTO) "Buscar gastos" else "Buscar ingresos",
                    onCalendario = onCalendario
                )
            }

            item {
                FiltrosCategoria(
                    opciones = estado.filtros,
                    seleccionado = estado.filtroCategoria,
                    onSeleccionar = onFiltroCambio
                )
            }

            item {
                TarjetaMovimientos(
                    movimientos = estado.movimientos,
                    onVerTodos = onVerTodos,
                    onMovimientoClick = onMovimientoClick
                )
            }
        }

        ExtendedFloatingActionButton(
            onClick = onAgregar,
            containerColor = VerdePrimario,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+ Agregar", style = MaterialTheme.typography.labelLarge)
        }
    }
}

private val estadoPrueba = HomeState(
    nombreUsuario = "Andrea",
    ingresosMes = 20000.0,
    gastosMes = 18500.0,
    diasRestantes = 14,
    categoriasGasto = listOf(
        ResumenCategoria("Vivienda", 6605.0, 0.357f),
        ResumenCategoria("Alimentación", 4477.0, 0.242f),
        ResumenCategoria("Transporte", 1443.0, 0.078f),
        ResumenCategoria("Otras 3", 5975.0, 0.323f)
    ),
    filtros = listOf(FILTRO_TODOS, "Vivienda", "Alimentación", "Transporte"),
    movimientos = DatosMock.movimientos
        .filter { it.tipo == TipoMovimiento.GASTO }
        .sortedByDescending { it.fecha }
)

@Composable
private fun HomePrevia() {
    GestorGastosTheme {
        HomeContenido(
            estado = estadoPrueba,
            onTipoCambio = {},
            onBusquedaCambio = {},
            onFiltroCambio = {},
            onVerGraficas = {},
            onAgregar = {},
            onVerTodos = {},
            onCalendario = {}
        )
    }
}


@Composable
@Preview(name = "Telefono", showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    HomePrevia()
}

