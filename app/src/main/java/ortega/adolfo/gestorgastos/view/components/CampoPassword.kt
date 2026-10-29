package ortega.adolfo.gestorgastos.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp


//componente para la contrasena del login se puede usar tambien en el registro sin
//problemas tenganlo en cuenta chavales
@Composable
fun CampoPassword(
    etiqueta: String,
    valor: String,
    onValorCambio: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    error: String? = null

){
    var visible by rememberSaveable { mutableStateOf(false) }

    CampoTexto(
        etiqueta = etiqueta,
        valor = valor,
        onValorCambio = onValorCambio,
        placeholder = placeholder,
        modifier = modifier,
        error = error,
        tipoTeclado = KeyboardType.Password,
        transformacion = if(visible) VisualTransformation.None else PasswordVisualTransformation(),
        iconoFinal = {
            IconButton(onClick = {visible = !visible}) {
                Icon(
                    imageVector = if(visible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                    contentDescription =  if(visible) "ocultar contraseña" else "Mostrar contraseña"
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun CampoPasswordPreview(){
    var password by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        CampoPassword(
            etiqueta = "Contraseña",
            valor = password,
            onValorCambio = { password = it },
            placeholder = "••••••••"
        )
    }
}