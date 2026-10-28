package ortega.adolfo.gestorgastos.model.domain.data

import ortega.adolfo.gestorgastos.model.domain.GastoGrupo
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.model.domain.Miembro
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

    val miembros = listOf(
        Miembro(1, "Angel Servin"),
        Miembro(2, "Dana Chavez"),
        Miembro(3, "Andrea Melendrez"),
        Miembro(4, "Leonel Carballo"),
        Miembro(5, "Adolfo Ruiz"),
    )

    val grupos = listOf(
        Grupo(1, "Japon 2026", "Angel Servin: Pago \$1000.00 MXN", miembros.take(5)),
        Grupo(2, "Vicam 2026", "Dana Chavez: Pago \$5000.00 MXN", miembros.take(4)),
        Grupo(3, "Yecorazo 2027", "Leonel Carballo: Pago 4,500.00", miembros.take(3)),
        Grupo(4, "Carro del año", "Adolfo Ruiz: Abono \$12,426.00 MXN", miembros.take(2)),
        Grupo(5, "Potro viaje - Mexicali", "Andrea Melendrez: Pago \$4,500.00", miembros.take(5)),
        Grupo(6, "Titulo Universitario", "Daniela Monclova: Pago \$189.00 MXN", miembros.take(2)),
        Grupo(7, "Casa Infonavit", "Dylan O'brien: Pago \$1,923,232.00 MXN", miembros.take(2)),
        Grupo(8, "Arreglar tinaco", "Daniela Monclova: Pago \$323.00 MXN", miembros.take(3))
    )

    val gastosGrupo = listOf(
        GastoGrupo(1, "Boletos de avion", "02 de abril 2026", "transporte", 150000.0, "Dana"),
        GastoGrupo(2, "Hotel en Tokio", "18 de junio 2025", "hospedaje", 30000.0),
        GastoGrupo(3, "Comida", "7 de agosto 2025", "comida", 10000.0, "Andrea"),
        GastoGrupo(4, "Transporte", "22 de noviembre 2025", "transporte", 8500.0)
    )
}


