package ortega.adolfo.gestorgastos.model.domain.data

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

    val grupos = listOf(
        Grupo(1, "Japon 2026", "Angel Servin: Pago \$1000.00 MXN"),
        Grupo(2, "Vicam 2026", "Dana Chavez: Pago \$5000.00 MXN"),
        Grupo(3, "Yecorazo 2027", "Leonel Carballo: Pago 4,500.00"),
        Grupo(4, "Carro del año", "Adolfo Ruiz: Abono \$12,426.00 MXN"),
        Grupo(5, "Potro viaje - Mexicali", "Andrea Melendrez: Pago \$4,500.00"),
        Grupo(6, "Titulo Universitario", "Daniela Monclova: Pago \$189.00 MXN"),
        Grupo(7, "Casa Infonavit", "Dylan O'brien: Pago \$1,923,232.00 MXN"),
        Grupo(8, "Arreglar tinaco", "Daniela Monclova: Pago \$323.00 MXN")
    )
}


