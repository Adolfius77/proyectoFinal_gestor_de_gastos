package ortega.adolfo.gestorgastos.model.domain.data

import ortega.adolfo.gestorgastos.model.domain.MetodoPago
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.model.domain.Usuario
import java.time.LocalDate
import java.time.YearMonth

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


    val categoriasFiltro = listOf(
        "Todas", "Vivienda", "Alimentación", "Transporte",
        "Entretenimiento", "Salud", "Otros"
    )


    private val mes = YearMonth.now()
    private fun dia(d: Int): LocalDate = mes.atDay(d)

    val movimientos = mutableListOf(

        Movimiento(1, "Renta departamento", 4500.0, dia(1), "Vivienda", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(2, "Luz", 600.0, dia(4), "Vivienda", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(3, "Agua", 350.0, dia(5), "Vivienda", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(4, "Internet", 600.0, dia(6), "Vivienda", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(5, "Gas", 555.0, dia(8), "Vivienda", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),

        Movimiento(6, "Supermercado", 2100.0, dia(13), "Alimentación", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(7, "Café", 320.0, dia(12), "Alimentación", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(8, "Dogos el Carballo", 150.0, dia(9), "Alimentación", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(9, "Restaurante", 1200.0, dia(7), "Alimentación", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(10, "Despensa", 707.0, dia(3), "Alimentación", TipoMovimiento.GASTO, MetodoPago.TARJETA),

        Movimiento(11, "Uber", 450.0, dia(12), "Transporte", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(12, "Gasolina", 800.0, dia(10), "Transporte", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(13, "Camión", 193.0, dia(2), "Transporte", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),

        Movimiento(14, "Cine", 480.0, dia(11), "Entretenimiento", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(15, "Farmacia", 895.0, dia(6), "Salud", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(16, "Ropa", 2600.0, dia(5), "Otros", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(17, "Gimnasio", 600.0, dia(2), "Salud", TipoMovimiento.GASTO, MetodoPago.TARJETA),
        Movimiento(18, "Regalo", 1400.0, dia(9), "Otros", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),


        Movimiento(19, "Sueldo primera quincena", 9000.0, dia(1), "Sueldo", TipoMovimiento.INGRESO, MetodoPago.TARJETA),
        Movimiento(20, "Sueldo segunda quincena", 9000.0, dia(15), "Sueldo", TipoMovimiento.INGRESO, MetodoPago.TARJETA),
        Movimiento(21, "Proyecto freelance", 2000.0, dia(10), "Freelance", TipoMovimiento.INGRESO, MetodoPago.EFECTIVO)
    )
}
