package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.GrisPlaceholder
import ortega.adolfo.gestorgastos.ui.theme.RojoError
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario


@Composable
fun CampoTexto(
    etiqueta: String,
    valor: String,
    onValorCambio: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    error: String? = null,
    tipoTeclado: KeyboardType = KeyboardType.Text,
    transformacion: VisualTransformation = VisualTransformation.None,
    soloLectura: Boolean = false,
    iconoFinal: @Composable (() -> Unit)? = null,
    onClick: (()-> Unit)? = null
){
    val interaccion = remember { MutableInteractionSource() }
    val onClickActual by rememberUpdatedState(onClick)

    LaunchedEffect(interaccion) {
        interaccion.interactions.collect { evento ->
            if(evento is PressInteraction.Release) onClickActual?.invoke()
        }
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = etiqueta,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            color = TextoPrincipal
        )
        Spacer(Modifier.size(6.dp))
        OutlinedTextField(
            value = valor,
            onValueChange = onValorCambio,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = GrisPlaceholder, fontSize = 14.sp) },
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = TextoPrincipal),
            singleLine = true,
            readOnly = soloLectura,
            isError = error != null,
            visualTransformation = transformacion,
            keyboardOptions = KeyboardOptions(keyboardType = tipoTeclado),
            trailingIcon = iconoFinal,
            interactionSource = interaccion,
            shape = RoundedCornerShape(10.dp),


            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = FondoClaro,
                errorContainerColor = FondoClaro,
                unfocusedContainerColor = FondoClaro,
                focusedBorderColor = VerdePrimario,
                unfocusedBorderColor = GrisPlaceholder,
                errorBorderColor = RojoError,
                cursorColor = VerdePrimario,
                focusedTrailingIconColor = TextoSecundario,
                unfocusedTrailingIconColor = TextoSecundario
            )
        )
        if (error != null) {
            Text(
                text = error,
                color = RojoError,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun CampoTextoPreview(){
    Column(Modifier.padding(16.dp)) {
        CampoTexto("Correo electrónico", "", {}, "tucorreo@ejemplo.com")
        Spacer(Modifier.height(12.dp))

    }
}

