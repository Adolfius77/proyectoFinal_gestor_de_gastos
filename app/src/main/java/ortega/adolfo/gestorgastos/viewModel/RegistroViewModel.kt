package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.Usuario
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import java.time.LocalDate

class RegistroViewModel : ViewModel() {

    private val _estado = MutableStateFlow(RegistroState())

    val estado: StateFlow<RegistroState> = _estado.asStateFlow()

    fun onNombreCambio(valor: String) {
        _estado.update { it.copy(nombre = valor, errorNombre = null, errorGeneral = null) }
    }

    fun onCorreoCambio(valor: String) {
        _estado.update { it.copy(correo = valor, errorCorreo = null, errorGeneral = null) }
    }

    fun onFechaCambio(valor: LocalDate) {
        _estado.update { it.copy(fechaNacimiento = valor, errorFecha = null, errorGeneral = null) }
    }

    fun onGeneroCambio(valor: String) {
        _estado.update { it.copy(genero = valor, errorGenero = null, errorGeneral = null) }
    }

    fun onPasswordCambio(valor: String) {
        _estado.update { it.copy(password = valor, errorPassword = null, errorGeneral = null) }
    }

    fun onConfirmarCambio(valor: String) {
        _estado.update { it.copy(confirmarPassword = valor, errorConfirmar = null, errorGeneral = null) }
    }


    fun onRegistroConsumido() {
        _estado.update { it.copy(registroExitoso = false) }
    }

    fun registrar() {
        val actual = _estado.value

        val errorNombre = validarNombre(actual.nombre)
        val errorCorreo = validarCorreo(actual.correo)
        val errorFecha = validarFecha(actual.fechaNacimiento)
        val errorGenero = if (actual.genero.isBlank()) "Selecciona tu género" else null
        val errorPassword = validarPassword(actual.password)
        val errorConfirmar = validarConfirmacion(actual.password, actual.confirmarPassword)

        val hayError = listOf(
            errorNombre, errorCorreo, errorFecha, errorGenero, errorPassword, errorConfirmar
        ).any { it != null }

        if (hayError) {
            _estado.update {
                it.copy(
                    errorNombre = errorNombre,
                    errorCorreo = errorCorreo,
                    errorFecha = errorFecha,
                    errorGenero = errorGenero,
                    errorPassword = errorPassword,
                    errorConfirmar = errorConfirmar
                )
            }
            return
        }

        val correoLimpio = actual.correo.trim()
        val yaExiste = DatosMock.usuarios.any { it.correo.equals(correoLimpio, ignoreCase = true) }
        if (yaExiste) {
            _estado.update { it.copy(errorCorreo = "Este correo ya está registrado") }
            return
        }

        val nuevoUsuario = Usuario(
            nombre = actual.nombre.trim(),
            correo = correoLimpio,
            fechaNacimiento = requireNotNull(actual.fechaNacimiento),
            genero = actual.genero,
            password = actual.password
        )
        DatosMock.usuarios.add(nuevoUsuario)
        DatosMock.usuarioActual = nuevoUsuario

        _estado.update { it.copy(registroExitoso = true) }
    }

    // Validaciones: funciones puras que son mas fáciles de probar con las unit tests

    private fun validarNombre(nombre: String): String? = when {
        nombre.isBlank() -> "Escribe tu nombre"
        nombre.trim().length < 3 -> "El nombre es muy corto"
        else -> null
    }

    private fun validarCorreo(correo: String): String? = when {
        correo.isBlank() -> "Escribe tu correo"
        !REGEX_CORREO.matches(correo.trim()) -> "Correo no válido"
        else -> null
    }

    private fun validarFecha(fecha: LocalDate?): String? = when {
        fecha == null -> "Selecciona tu fecha"
        fecha.isAfter(LocalDate.now()) -> "La fecha no puede ser futura"
        else -> null
    }

    private fun validarPassword(password: String): String? = when {
        password.isBlank() -> "Escribe tu contraseña"
        password.length < LONGITUD_MINIMA_PASSWORD -> "Mínimo $LONGITUD_MINIMA_PASSWORD caracteres"
        else -> null
    }

    private fun validarConfirmacion(password: String, confirmacion: String): String? = when {
        confirmacion.isBlank() -> "Confirma tu contraseña"
        password != confirmacion -> "Las contraseñas no coinciden"
        else -> null
    }

    private companion object {
        const val LONGITUD_MINIMA_PASSWORD = 6
        val REGEX_CORREO = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    }
}