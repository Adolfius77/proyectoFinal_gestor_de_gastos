package ortega.adolfo.gestorgastos.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.view.components.BarraNavegacion
import ortega.adolfo.gestorgastos.view.components.screens.AgregarGastoScreen
import ortega.adolfo.gestorgastos.view.components.screens.CrearGrupoScreen
import ortega.adolfo.gestorgastos.view.components.screens.DetalleMovimientoScreen
import ortega.adolfo.gestorgastos.view.components.screens.GrupoDetalleScreen
import ortega.adolfo.gestorgastos.view.components.screens.GrupoInfoScreen
import ortega.adolfo.gestorgastos.view.components.screens.GruposScreen
import ortega.adolfo.gestorgastos.view.components.screens.HomeScreen
import ortega.adolfo.gestorgastos.view.components.screens.NewMovementScreen
import ortega.adolfo.gestorgastos.view.components.screens.PresupuestoAgregarCategoriaScreen
import ortega.adolfo.gestorgastos.view.components.screens.PresupuestoGastoFijoScreen
import ortega.adolfo.gestorgastos.view.components.screens.PresupuestoIngresoFijoScreen
import ortega.adolfo.gestorgastos.view.components.screens.PresupuestoScreen
import ortega.adolfo.gestorgastos.view.components.screens.ResumenSreen

@Composable
fun NavegacionApp(
    navController: NavHostController = rememberNavController()
) {
    val entradaActual by navController.currentBackStackEntryAsState()
    val destinoActual = seccionDe(entradaActual?.destination)

    Scaffold(
        containerColor = FondoClaro,
        bottomBar = {
            if (destinoActual != null) {
                BarraNavegacion(
                    actual = destinoActual,
                    onNavegar = { destino ->
                        if (destino == destinoActual) {
                            navController.popBackStack(destino.ruta, inclusive = false)
                        } else {
                            navController.navegarA(destino)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Ruta.Inicio,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Ruta.Inicio> {
                HomeScreen(
                    onVerGraficas = { navController.navegarA(Destino.GRAFICAS) },
                    onAgregar = { navController.navigate(Ruta.NuevoMovimiento) },
                    onVerMovimiento = { id -> navController.navigate(Ruta.DetalleMovimiento(id)) }
                )
            }
            composable<Ruta.NuevoMovimiento> {
                NewMovementScreen(
                    onRegresar = { navController.popBackStack() },
                    onGuardado = { navController.popBackStack() }
                )
            }
            composable<Ruta.DetalleMovimiento> {
                DetalleMovimientoScreen(
                    onRegresar = { navController.popBackStack() },
                    onEditar = {}
                )
            }

            composable<Ruta.Graficas> { ResumenSreen() }

            composable<Ruta.Grupos> {
                GruposScreen(
                    onCrearGrupo = { navController.navigate(Ruta.CrearGrupo) },
                    onAbrirGrupo = { navController.navigate(Ruta.DetalleGrupo(it.id)) }
                )
            }

            composable<Ruta.Presupuesto> {
                PresupuestoScreen(
                    onAgregarIngreso = { navController.navigate(Ruta.PresupuestoAgregarIngreso) },
                    onAgregarGastoFijo = { navController.navigate(Ruta.PresupuestoGastoFijo) },
                    onAgregarCategoria = { navController.navigate(Ruta.PresupuestoAgregarCategoria) }
                )
            }
            composable<Ruta.PresupuestoAgregarIngreso> {
                PresupuestoIngresoFijoScreen(onVolver = { navController.popBackStack() })
            }
            composable<Ruta.PresupuestoGastoFijo> {
                PresupuestoGastoFijoScreen(onVolver = { navController.popBackStack() })
            }
            composable<Ruta.PresupuestoAgregarCategoria> {
                PresupuestoAgregarCategoriaScreen(onVolver = { navController.popBackStack() })
            }

            composable<Ruta.Perfil> { PantallaPendiente("Perfil") }

            composable<Ruta.CrearGrupo> {
                CrearGrupoScreen(
                    onVolver = { navController.popBackStack() },
                    onCrear = { nombre, tipo, imagen ->
                        navController.popBackStack()
                    }
                )
            }

            composable<Ruta.DetalleGrupo> { entrada ->
                val id = entrada.toRoute<Ruta.DetalleGrupo>().id
                val grupo = DatosMock.grupos.firstOrNull { it.id == id }
                if (grupo != null) {
                    GrupoDetalleScreen(
                        grupo = grupo,
                        gastos = DatosMock.gastosGrupo,
                        onVolver = { navController.popBackStack() },
                        onAbrirInfo = { navController.navigate(Ruta.InfoGrupo(grupo.id)) },
                        onAgregarGasto = { navController.navigate(Ruta.AgregarGasto(grupo.id)) }
                    )
                }
            }

            composable<Ruta.InfoGrupo> { entrada ->
                val id = entrada.toRoute<Ruta.InfoGrupo>().id
                val grupo = DatosMock.grupos.firstOrNull { it.id == id }
                if (grupo != null) {
                    GrupoInfoScreen(
                        grupo = grupo,
                        onVolver = { navController.popBackStack() },
                        onAbandonar = {
                            navController.popBackStack(Ruta.Grupos, inclusive = false)
                        }
                    )
                }
            }

            composable<Ruta.AgregarGasto> { entrada ->
                val id = entrada.toRoute<Ruta.AgregarGasto>().id
                val grupo = DatosMock.grupos.firstOrNull { it.id == id }
                if (grupo != null) {
                    AgregarGastoScreen(
                        grupo = grupo,
                        onVolver = { navController.popBackStack() },
                        onGuardar = { categoria, monto, pagadoPor, fecha ->
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

private fun seccionDe(pantalla: NavDestination?): Destino? {
    if (pantalla == null) return null

    Destino.entries.find { pantalla.hasRoute(it.ruta::class) }?.let { return it }

    return when {
        pantalla.hasRoute(Ruta.DetalleMovimiento::class) ||
            pantalla.hasRoute(Ruta.NuevoMovimiento::class) -> Destino.INICIO

        pantalla.hasRoute(Ruta.CrearGrupo::class) ||
            pantalla.hasRoute(Ruta.DetalleGrupo::class) ||
            pantalla.hasRoute(Ruta.InfoGrupo::class) ||
            pantalla.hasRoute(Ruta.AgregarGasto::class) -> Destino.GRUPOS

        pantalla.hasRoute(Ruta.PresupuestoAgregarIngreso::class) ||
            pantalla.hasRoute(Ruta.PresupuestoGastoFijo::class) ||
            pantalla.hasRoute(Ruta.PresupuestoAgregarCategoria::class) -> Destino.PRESUPUESTO

        else -> null
    }
}

private fun NavHostController.navegarA(destino: Destino) {
    navigate(destino.ruta) {
        popUpTo<Ruta.Inicio> { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun PantallaPendiente(nombre: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            "$nombre (pendiente)",
            style = MaterialTheme.typography.bodyLarge,
            color = TextoSecundario
        )
    }
}