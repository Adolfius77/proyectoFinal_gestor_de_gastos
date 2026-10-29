package ortega.adolfo.gestorgastos.model.domain.data

import ortega.adolfo.gestorgastos.model.domain.GastoGrupo
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.model.domain.Miembro
import ortega.adolfo.gestorgastos.model.domain.Categoria
import ortega.adolfo.gestorgastos.model.domain.Frecuencia
import ortega.adolfo.gestorgastos.model.domain.GastoFijo
import ortega.adolfo.gestorgastos.model.domain.IngresoFijo
import ortega.adolfo.gestorgastos.model.domain.TipoGasto
import ortega.adolfo.gestorgastos.model.domain.MetodoPago
import ortega.adolfo.gestorgastos.model.domain.Movimiento
import ortega.adolfo.gestorgastos.model.domain.Tarjeta
import ortega.adolfo.gestorgastos.model.domain.TipoMovimiento
import ortega.adolfo.gestorgastos.model.domain.Usuario
import java.time.LocalDate
import java.time.LocalTime
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


    // Registro de movimientos (Registrar gasto o ingreso)
    const val PAGO_TARJETA = "Tarjeta"

    val categoriasGasto = listOf(
        "Vivienda", "Alimentación", "Transporte",
        "Entretenimiento", "Salud", "Otros"
    )
    val categoriasIngreso = listOf("Salario", "Freelance", "Inversiones", "Otros")

    val tiposPago = listOf("Efectivo", PAGO_TARJETA, "Transferencia")
    val tarjetas = mutableListOf("Débito 5778", "BBVA 7789")

    const val UBICACION_MOCK = "Villa Fontana 85098"

    // Opciones para filtrar el resumen por categoria
    val categoriasFiltro = listOf(
        "Todas", "Vivienda", "Alimentación", "Transporte",
        "Entretenimiento", "Salud", "Otros"
    )

    val miembros = listOf(
        Miembro(1, "Dana Chavez Gutierrez"),
        Miembro(2, "Angel Servin de la Mora"),
        Miembro(3, "Leonel Alfredo Carballo"),
        Miembro(4, "Andrea Melendrez Macias"),
        Miembro(5, "Adolfo Ortega Ruiz"),
    )

    val grupos = listOf(
        Grupo(1, "Japon 2026", "Angel Servin: Pago \$1000.00 MXN", miembros.take(5), codigo = "34RY6"),
        Grupo(2, "Vicam 2026", "Dana Chavez: Pago \$5000.00 MXN", miembros.take(4), codigo = "K8M2P"),
        Grupo(3, "Yecorazo 2027", "Leonel Carballo: Pago 4,500.00", miembros.take(3), codigo = "Q7TX1"),
        Grupo(4, "Carro del año", "Adolfo Ruiz: Abono \$12,426.00 MXN", miembros.take(2), codigo = "B4N9Z"),
        Grupo(5, "Potro viaje - Mexicali", "Andrea Melendrez: Pago \$4,500.00", miembros.take(5), codigo = "W3H6D"),
        Grupo(6, "Titulo Universitario", "Daniela Monclova: Pago \$189.00 MXN", miembros.take(2), codigo = "R2V8C"),
        Grupo(7, "Casa Infonavit", "Dylan O'brien: Pago \$1,923,232.00 MXN", miembros.take(2), codigo = "L5J7F"),
        Grupo(8, "Arreglar tinaco", "Daniela Monclova: Pago \$323.00 MXN", miembros.take(3), codigo = "T9Y4A")
    )

    val gastosGrupo = listOf(
        GastoGrupo(1, "Boletos de avion", "02 de abril 2026", "transporte", 150000.0, "Dana"),
        GastoGrupo(2, "Hotel en Tokio", "18 de junio 2025", "hospedaje", 30000.0),
        GastoGrupo(3, "Comida", "7 de agosto 2025", "comida", 10000.0, "Andrea"),
        GastoGrupo(4, "Transporte", "22 de noviembre 2025", "transporte", 8500.0)
    )

    val categoriasGastoGrupo = listOf(
        "Transporte", "Hospedaje", "Comida", "Entretenimiento", "Salud", "Otros"
    )

    // Datos del presupuesto mensual (Gestionar presupuesto)
    val categorias = mutableListOf(
        Categoria(id = 1, nombre = "Comida", presupuesto = 3500.0, gastado = 2975.0),
        Categoria(id = 2, nombre = "Transporte", presupuesto = 1800.0, gastado = 1100.0),
        Categoria(id = 3, nombre = "Ocio", presupuesto = 1500.0, gastado = 1620.0)
    )

    val ingresosFijos = mutableListOf(
        IngresoFijo(
            id = 1,
            nombre = "Sueldo",
            monto = 20000.0,
            frecuencia = Frecuencia.MENSUAL,
            diaCobro = 30
        )
    )

    val gastosFijos = mutableListOf(
        GastoFijo(id = 1, nombre = "Renta", monto = 5500.0, tipo = TipoGasto.VIVIENDA, diaPago = 1, recordar = true),
        GastoFijo(id = 2, nombre = "Servicios", monto = 1200.0, tipo = TipoGasto.SERVICIOS, diaPago = 1, recordar = true),
        GastoFijo(id = 3, nombre = "Celular", monto = 500.0, tipo = TipoGasto.OTRO, diaPago = 1, recordar = true)
    )

    val meses = listOf("Agosto 2026", "Septiembre 2026", "Octubre 2026")

    private val mes = YearMonth.now()
    private fun dia(d: Int): LocalDate = mes.atDay(d)
    private val debito = Tarjeta("Débito", "5778")

    val movimientos = mutableListOf(

        Movimiento(1, "Renta departamento", 4500.0, dia(1), "Vivienda", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito, hora = LocalTime.of(10, 0), nota = "Renta del mes"),
        Movimiento(2, "Luz", 600.0, dia(4), "Vivienda", TipoMovimiento.GASTO, MetodoPago.EFECTIVO, hora = LocalTime.of(12, 30), ubicacion = "CFE", nota = "Recibo bimestral", fotoRecibo = "recibo_cfe.jpg"),
        Movimiento(3, "Agua", 350.0, dia(5), "Vivienda", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(4, "Internet", 600.0, dia(6), "Vivienda", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(5, "Gas", 555.0, dia(8), "Vivienda", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),

        Movimiento(6, "Supermercado", 2100.0, dia(13), "Alimentación", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito, hora = LocalTime.of(18, 42), ubicacion = "Soriana Villa Fontana", nota = "Despensa de la semana", fotoRecibo = "ticket_soriana.jpg"),
        Movimiento(7, "Café", 320.0, dia(12), "Alimentación", TipoMovimiento.GASTO, MetodoPago.EFECTIVO, hora = LocalTime.of(9, 15), ubicacion = "Café Combate", nota = "Café antes de clase"),
        Movimiento(8, "Dogos el Carballo", 150.0, dia(9), "Alimentación", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(9, "Restaurante", 1200.0, dia(7), "Alimentación", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(10, "Despensa", 707.0, dia(3), "Alimentación", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),

        Movimiento(11, "Uber", 450.0, dia(12), "Transporte", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito, hora = LocalTime.of(22, 5), ubicacion = "Centro, Cd. Obregón", nota = "Regreso a casa"),
        Movimiento(12, "Gasolina", 800.0, dia(10), "Transporte", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(13, "Camión", 193.0, dia(2), "Transporte", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),

        Movimiento(14, "Cine", 480.0, dia(11), "Entretenimiento", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(15, "Farmacia", 895.0, dia(6), "Salud", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),
        Movimiento(16, "Ropa", 2600.0, dia(5), "Otros", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(17, "Gimnasio", 600.0, dia(2), "Salud", TipoMovimiento.GASTO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(18, "Regalo", 1400.0, dia(9), "Otros", TipoMovimiento.GASTO, MetodoPago.EFECTIVO),


        Movimiento(19, "Sueldo primera quincena", 9000.0, dia(1), "Salario", TipoMovimiento.INGRESO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(20, "Sueldo segunda quincena", 9000.0, dia(15), "Salario", TipoMovimiento.INGRESO, MetodoPago.TARJETA, tarjeta = debito),
        Movimiento(21, "Proyecto freelance", 2000.0, dia(10), "Freelance", TipoMovimiento.INGRESO, MetodoPago.EFECTIVO)
    )
}
