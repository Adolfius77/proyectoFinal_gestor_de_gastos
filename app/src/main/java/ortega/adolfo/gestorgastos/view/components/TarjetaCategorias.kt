package ortega.adolfo.gestorgastos.view.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.model.domain.ResumenCategoria
import ortega.adolfo.gestorgastos.ui.theme.Azul
import ortega.adolfo.gestorgastos.ui.theme.GrisPlaceholder
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.Naranja
import ortega.adolfo.gestorgastos.ui.theme.SpaceGrotesk
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.utilities.formatoPorcentaje
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme


private val colores = listOf(Azul, VerdePrimario, Naranja, GrisPlaceholder)


@Composable
fun TarjetaCategorias(
    categorias: List<ResumenCategoria>,
    onVerGraficas: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.grafica_rueda),
                contentDescription = stringResource(R.string.gr_fica_de_gastos_por_categoria),
                modifier = Modifier.size(110.dp)
            )
            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categorias.forEachIndexed { indice, categoria ->
                    FilaLeyenda(
                        nombre = categoria.nombre,
                        porcentaje = formatoPorcentaje(categoria.porcentaje),
                        color = colores.getOrElse(indice) { GrisPlaceholder }
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.ver_gr_ficas),
                    fontFamily = Manrope,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = VerdePrimario,
                    modifier = Modifier.clickable(onClick = onVerGraficas)
                )
            }
        }
    }
}

@Composable
private fun FilaLeyenda(nombre: String, porcentaje: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = nombre,
            fontFamily = Manrope,
            fontSize = 13.sp,
            color = TextoPrincipal,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = porcentaje,
            fontFamily = SpaceGrotesk,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = TextoPrincipal
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TarjetaCategoriasPreview() {
    GestorGastosTheme {
        TarjetaCategorias(
            categorias = listOf(
                ResumenCategoria("Vivienda", 6605.0, 0.357f),
                ResumenCategoria("Alimentación", 4477.0, 0.242f),
                ResumenCategoria("Transporte", 1443.0, 0.078f),
                ResumenCategoria("Otras 3", 5975.0, 0.323f)
            ),
            onVerGraficas = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
