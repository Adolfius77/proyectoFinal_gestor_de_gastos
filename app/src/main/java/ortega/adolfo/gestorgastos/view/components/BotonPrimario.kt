package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

//este boton se puede reutilizar en toda la app para acciones
@Composable
fun BotonPrimario(texto: String, onClick: () -> Unit, modifier: Modifier = Modifier ){
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = VerdePrimario,
            contentColor = White
        ),
        modifier = modifier.fillMaxWidth().height(52.dp),



    ) {
        Text(
           text =  texto,
            fontSize = 16.sp,
            fontFamily = Manrope
        )

    }
}
@Preview(showBackground = true)
@Composable
fun BotonPreview(){
    GestorGastosTheme {
        BotonPrimario("Iniciar sesión", onClick = {})
    }
}