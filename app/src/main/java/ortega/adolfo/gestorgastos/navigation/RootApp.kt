package ortega.adolfo.gestorgastos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.view.components.screens.LoginScreen
import ortega.adolfo.gestorgastos.view.components.screens.RegisterScreen
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.viewModel.AjustesViewModel

@Composable
fun RootApp(
    rootController: NavHostController = rememberNavController(),
    ajustes: AjustesViewModel = viewModel()
) {
    val estadoAjustes by ajustes.estado.collectAsState()
    val modoOscuro = estadoAjustes.modoOscuro ?: isSystemInDarkTheme()

    GestorGastosTheme(darkTheme = modoOscuro) {
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
                    modoOscuro = modoOscuro,
                    onModoOscuroCambio = ajustes::onModoOscuroCambio,
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
}

private fun NavHostController.irAPrincipal() {
    navigate(RutaRoot.Principal) {
        popUpTo<RutaRoot.Login> { inclusive = true }
    }
}