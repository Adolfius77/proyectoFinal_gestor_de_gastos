package ortega.adolfo.gestorgastos.navigation

import kotlinx.serialization.Serializable


sealed interface Ruta {

    @Serializable data object Grupos : Ruta
    @Serializable data object Graficas : Ruta
    @Serializable data object Inicio : Ruta
    @Serializable data object Presupuesto : Ruta
    @Serializable data object Perfil : Ruta
    @Serializable data object CrearGrupo : Ruta

    @Serializable data class DetalleGrupo(val id: Int) : Ruta

    @Serializable data class InfoGrupo(val id: Int) : Ruta

    @Serializable data class AgregarGasto(val id: Int) : Ruta


    @Serializable data object PresupuestoAgregarIngreso : Ruta
    @Serializable data object PresupuestoGastoFijo : Ruta
    @Serializable data object PresupuestoAgregarCategoria : Ruta


    @Serializable data class DetalleMovimiento(val id: Int) : Ruta
}
