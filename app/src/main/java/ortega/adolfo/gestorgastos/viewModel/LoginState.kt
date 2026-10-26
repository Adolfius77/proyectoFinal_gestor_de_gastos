package ortega.adolfo.gestorgastos.viewModel

data class LoginState(
    val correo: String = "",
    val password: String = "",
    val errorCorreo: String? = null,
    val errorPassword: String? = null,
    val errorGeneral : String? = null,
    val loginExitoso: Boolean = false
    
)
