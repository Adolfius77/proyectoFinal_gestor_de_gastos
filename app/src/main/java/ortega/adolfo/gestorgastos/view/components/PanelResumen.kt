package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    categoriaMayor: String,
    precioMayor: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.total_gastado),
            fontFamily = Manrope,
            color = TextoSecundario,
            fontSize = 13.sp
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = totalGastado,
            fontFamily = SpaceGrotesk,
            color = TextoPrincipal,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(Modifier.height(8.dp))
        ChipCategoriaPrecio(categoria = categoriaMayor, precio = precioMayor)
        Spacer(Modifier.height(12.dp))


        Image(
            painter = painterResource(id = R.drawable.graficas),
            contentDescription = stringResource(R.string.gr_fica_de_gastos_por_categor_a),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(FondoClaro)
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.tu_gasto_m_s_alto_en_este_periodo_es, categoriaMayor),
                color = TextoSecundario,
                fontSize = 13.sp,
                fontFamily = Manrope
            )
            Text(
                text = stringResource(R.string.toca_una_barra_para_ver_el_detalle),
                color = TextoSecundario,
                fontSize = 13.sp,
                fontFamily = Manrope
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PanelResumenPreview() {
    PanelResumen(
        totalGastado = "$9,550",
        categoriaMayor = "Vivienda",
        precioMayor = "$4,500",
        modifier = Modifier.background(Color.White)
    )
}
