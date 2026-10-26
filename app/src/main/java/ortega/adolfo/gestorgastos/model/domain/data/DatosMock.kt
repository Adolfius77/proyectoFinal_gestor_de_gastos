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
}
val generos = listOf("Mujer", "Hombre", "Otro", "Prefiero no decirlo")

var usuarioActual: Usuario? = null