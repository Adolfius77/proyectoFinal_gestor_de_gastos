package ortega.adolfo.gestorgastos.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock

class LoginViewModel: ViewModel() {
    private val _estado = MutableStateFlow(LoginState())
    val estado: StateFlow<LoginState> = _estado.asStateFlow()

    fun onCorreoCambio(valor: String){
        _estado.update { it.copy(password = valor, errorPassword = null, errorGeneral = null, ) }
    }

    fun onPasswordCambio(valor: String){
        _estado.update { it.copy(password = valor, errorPassword = null, errorGeneral = null) }
    }

    fun iniciarSesion(){
        val actual = _estado.value
        val errorCorreo = if(actual.correo.isBlank()) "escribe tu correo" else null
        val errorPassword = if(actual.password.isBlank()) "escribe tu contrasena " else null

        if(errorCorreo != null || errorPassword != null){
            _estado.update { it.copy(errorCorreo = errorCorreo, errorPassword = errorPassword ) }
            return
        }
        val usuario = DatosMock.usuarios.find {
            it.correo.equals(actual.correo, ignoreCase = true) && it.password == actual.password
        }

        if (usuario == null) {
            _estado.update { it.copy(errorGeneral = "Correo o contraseña incorrectos") }
        } else {
            DatosMock.usuarioActual = usuario
            _estado.update { it.copy(loginExitoso = true) }
        }
    }
}