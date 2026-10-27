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
}


