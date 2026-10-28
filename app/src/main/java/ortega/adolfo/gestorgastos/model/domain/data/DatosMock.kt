package ortega.adolfo.gestorgastos.model.domain.data

import ortega.adolfo.gestorgastos.model.domain.Categoria
import ortega.adolfo.gestorgastos.model.domain.Frecuencia
import ortega.adolfo.gestorgastos.model.domain.GastoFijo
import ortega.adolfo.gestorgastos.model.domain.IngresoFijo
import ortega.adolfo.gestorgastos.model.domain.TipoGasto
import ortega.adolfo.gestorgastos.model.domain.Usuario
import java.time.LocalDate

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

    // Opciones para filtrar el resumen por categoria
    val categoriasFiltro = listOf(
        "Todas", "Vivienda", "Alimentación", "Transporte",
        "Entretenimiento", "Salud", "Otros"
    )

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
}


