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
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.view.components.BarraNavegacion
import ortega.adolfo.gestorgastos.view.components.screens.ResumenSreen

@Composable
fun NavegacionApp(
    navController: NavHostController = rememberNavController()
) {
    val entradaActual by navController.currentBackStackEntryAsState()
    val destinoActual = Destino.entries.find { destino ->
        entradaActual?.destination?.hasRoute(destino.ruta::class) == true
    }

    Scaffold(
        containerColor = FondoClaro,
        bottomBar = {
            if (destinoActual != null) {
                BarraNavegacion(
                    actual = destinoActual,
                    onNavegar = { navController.navegarA(it) }
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


            composable<Ruta.Grupos> { PantallaPendiente("Grupos") }
            composable<Ruta.Inicio> { PantallaPendiente("Inicio") }
            composable<Ruta.Presupuesto> { PantallaPendiente("Presupuesto") }
            composable<Ruta.Perfil> { PantallaPendiente("Perfil") }
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
