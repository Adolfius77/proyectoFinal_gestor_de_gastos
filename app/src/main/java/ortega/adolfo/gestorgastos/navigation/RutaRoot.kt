package ortega.adolfo.gestorgastos.navigation

import kotlinx.serialization.Serializable

sealed interface RutaRoot {
    @Serializable data object Login : RutaRoot
    @Serializable data object Registro : RutaRoot
    @Serializable data object Principal : RutaRoot
}