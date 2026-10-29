package ortega.adolfo.gestorgastos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.view.components.screens.LoginScreen
import ortega.adolfo.gestorgastos.view.components.screens.RegisterScreen

@Composable
fun RootApp(
    rootController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = rootController,
        startDestination = RutaRoot.Login
    ) {
        composable<RutaRoot.Login> {
            LoginScreen(
                onLoginExitoso = { rootController.irAPrincipal() },
                onIrARegistro = { rootController.navigate(RutaRoot.Registro) }
            )
        }

        composable<RutaRoot.Registro> {
            RegisterScreen(
                onRegisterExitoso = { rootController.irAPrincipal() }
            )
        }

        composable<RutaRoot.Principal> {
            NavegacionApp(
                onCerrarSesion = {
                    DatosMock.usuarioActual = null
                    rootController.navigate(RutaRoot.Login) {
                        popUpTo<RutaRoot.Principal> { inclusive = true }
                    }
                }
            )
        }
    }
}

// Entra a la app y borra Login (y Registro) de la pila
private fun NavHostController.irAPrincipal() {
    navigate(RutaRoot.Principal) {
        popUpTo<RutaRoot.Login> { inclusive = true }
    }
}