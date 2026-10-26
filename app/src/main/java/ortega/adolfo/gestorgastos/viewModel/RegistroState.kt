package ortega.adolfo.gestorgastos.viewModel

import java.time.LocalDate

data class RegistroState(
    val nombre: String = "",
    val correo: String = "",
    val fechaNacimiento: LocalDate? = null,
    val genero: String = "",
    val password: String = "",
    val confirmarPassword: String = "",

    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorFecha: String? = null,
    val errorGenero: String? = null,
    val errorPassword: String? = null,
    val errorConfirmar: String? = null,
    val errorGeneral: String? = null,

    val registroExitoso: Boolean = false
)
