package ortega.adolfo.gestorgastos.model.domain

import java.time.LocalDate

data class Usuario (
    val nombre: String,
    val correo: String,
    val fechaNacimiento: LocalDate,
    val genero: String,
    val password: String
)
