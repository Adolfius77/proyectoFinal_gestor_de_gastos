package ortega.adolfo.gestorgastos.viewModel

import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import java.time.LocalDate

data class NuevoMovimientoState(val tipo: TipoMovimiento = TipoMovimiento.GASTO,
                                val monto: String = "",
                                val descripcion: String = "",
                                val categoria: String = "",
                                val tipoPago: String = DatosMock.PAGO_TARJETA,
                                val tarjeta: String = DatosMock.tarjetas.firstOrNull() ?: "",
                                val ubicacion: String = DatosMock.UBICACION_MOCK,
                                val reciboUri: String? = null,
                                val fecha: LocalDate? = null,

                                val tarjetas: List<String> = DatosMock.tarjetas.toList(),
                                val mostrarDialogoTarjeta: Boolean = false,
                                val nuevaTarjeta: String = "",
                                val errorNuevaTarjeta: String? = null,

                                val errorMonto: String? = null,
                                val errorDescripcion: String? = null,
                                val errorCategoria: String? = null,
                                val errorTarjeta: String? = null,
                                val errorFecha: String? = null,

                                val guardadoExitoso: Boolean = false){

    // Las categorias dependen de si es Gasto o Ingreso
    val categorias: List<String>
        get() = if (tipo == TipoMovimiento.GASTO) DatosMock.categoriasGasto
        else DatosMock.categoriasIngreso

    // El campo Tarjeta solo se muestra cuando el pago es con tarjeta
    val pagaConTarjeta: Boolean
        get() = tipoPago == DatosMock.PAGO_TARJETA

}
