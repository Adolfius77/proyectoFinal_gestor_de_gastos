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
import ortega.adolfo.gestorgastos.view.components.screens.CrearGrupoScreen
import ortega.adolfo.gestorgastos.view.components.screens.GrupoDetalleScreen
import ortega.adolfo.gestorgastos.view.components.screens.GrupoInfoScreen
import ortega.adolfo.gestorgastos.view.components.screens.GruposScreen
import ortega.adolfo.gestorgastos.view.components.screens.ResumenSreen

@Composable
fun NavegacionApp(
    navController: NavHostController = rememberNavController()
) {
    val entradaActual by navController.currentBackStackEntryAsState()

    val enSubpantallaDeGrupos = entradaActual?.destination?.let {
        it.hasRoute(Ruta.CrearGrupo::class) ||
                it.hasRoute(Ruta.DetalleGrupo::class) ||
                it.hasRoute(Ruta.InfoGrupo::class)
    } == true

    val destinoActual = Destino.entries.find { destino ->
        entradaActual?.destination?.hasRoute(destino.ruta::class) == true
    } ?: if (enSubpantallaDeGrupos) Destino.GRUPOS else null

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
            startDestination = Ruta.Graficas,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable<Ruta.Graficas> { ResumenSreen() }

            composable<Ruta.Grupos> {
                GruposScreen(
                    onCrearGrupo = { navController.navigate(Ruta.CrearGrupo) },
                    onAbrirGrupo = { navController.navigate(Ruta.DetalleGrupo(it.id)) }
                )
            }

            composable<Ruta.Inicio> { PantallaPendiente("Inicio") }
            composable<Ruta.Presupuesto> { PantallaPendiente("Presupuesto") }
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
                        onAbrirInfo = { navController.navigate(Ruta.InfoGrupo(grupo.id)) }
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
        }
    }
}

private fun NavHostController.navegarA(destino: Destino) {
    navigate(destino.ruta) {
        popUpTo<Ruta.Graficas> { saveState = true }
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