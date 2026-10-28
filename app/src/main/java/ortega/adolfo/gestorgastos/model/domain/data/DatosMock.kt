package ortega.adolfo.gestorgastos.model.domain.data

import ortega.adolfo.gestorgastos.model.domain.Usuario
import java.time.LocalDate
import ortega.adolfo.gestorgastos.model.domain.Movimiento

object DatosMock {
    val usuarios = mutableListOf(
        Usuario(
            "adolfo",
            "adolfo@gmail.com",
            LocalDate.of(2005,10,3),
            "hombre","123456789")

    )
    var usuarioActual: Usuario? = null
    val generos = listOf("Mujer", "Hombre", "Otro", "Prefiero no decirlo")

    // Nuevo movimiento
    const val PAGO_TARJETA = "Tarjeta"

    val categoriasGasto = listOf(
        "Vivienda", "Alimentación", "Transporte",
        "Entretenimiento", "Salud", "Otros"
    )
    val categoriasIngreso = listOf("Salario", "Freelance", "Inversiones", "Otros")

    val tiposPago = listOf("Efectivo", PAGO_TARJETA, "Transferencia")
    val tarjetas = mutableListOf("BBVA 7789")

    const val UBICACION_MOCK = "Villa Fontana 85098"

    val movimientos = mutableListOf<Movimiento>()


    // Opciones para filtrar el resumen por categoria
    val categoriasFiltro = listOf(
        "Todas", "Vivienda", "Alimentación", "Transporte",
        "Entretenimiento", "Salud", "Otros"
    )
}


