package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun GastoResumen(
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Gasto contra estimado",
                fontFamily = Manrope,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextoPrincipal
            )
            Spacer(Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChipColorCategoria(color = Color.Black)
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Gastado",
                    fontFamily = Manrope,
                    fontSize = 12.sp,
                    color = TextoSecundario
                )
                Spacer(Modifier.width(20.dp))
                ChipColorCategoria(color = TextoSecundario)
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Estimado del mes",
                    fontSize = 12.sp,
                    fontFamily = Manrope,
                    color = TextoSecundario
                )
            }
            Spacer(Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.barras),
                contentDescription = "Gráfica de gasto contra estimado",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GastoResumenPreview(){
    GestorGastosTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            GastoResumen()
        }
    }
}
