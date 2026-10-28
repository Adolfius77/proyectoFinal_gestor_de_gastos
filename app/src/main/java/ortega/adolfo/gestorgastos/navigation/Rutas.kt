package ortega.adolfo.gestorgastos.navigation

import kotlinx.serialization.Serializable


sealed interface Ruta {
    @Serializable data object Grupos : Ruta
    @Serializable data object Graficas : Ruta
    @Serializable data object Inicio : Ruta
    @Serializable data object Presupuesto : Ruta
    @Serializable data object Perfil : Ruta
    @Serializable data object CrearGrupo : Ruta

}
