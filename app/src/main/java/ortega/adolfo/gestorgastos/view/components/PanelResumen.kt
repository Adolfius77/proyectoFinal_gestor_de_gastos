package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario

@Composable
fun PanelResumen(
    totalGastado: String,
    modifier: Modifier = Modifier,
    categoriaMayor: String = "Vivienda"
){
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Total gastado",
            fontFamily = Manrope,
            color = TextoSecundario,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column {
            Text(
                text = totalGastado,
                fontFamily = SpaceGrotesk,
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextoPrincipal
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            ChipCategoriaPrecio(categoria = categoriaMayor, precio = "$5,550")
        }
        Spacer(Modifier.height(10.dp))
        Image(
            painter = painterResource(id = R.drawable.graficas),
            contentDescription = "Gráfica de gastos",
            modifier = Modifier.size(160.dp)
        )
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(FondoClaro)
                .padding(12.dp)
        ) {
            Column {
                Text(
                    text = "Tu gasto más alto en este periodo es $categoriaMayor.",
                    color = TextoPrincipal,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Manrope
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Toca una barra para ver el detalle.",
                    color = TextoPrincipal,
                    fontSize = 10.sp,
                    fontFamily = Manrope
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PanelResumenPreview(){
    PanelResumen(totalGastado = "$9,000")
}
