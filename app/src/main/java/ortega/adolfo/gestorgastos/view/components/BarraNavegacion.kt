package ortega.adolfo.gestorgastos.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.navigation.Destino
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.TextoSecundario
import ortega.adolfo.gestorgastos.ui.theme.VerdePrimario
import ortega.adolfo.gestorgastos.ui.theme.VerdeSuave
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme
import ortega.adolfo.gestorgastos.ui.theme.Superficie
import ortega.adolfo.gestorgastos.ui.theme.Colores

private fun Destino.icono(): ImageVector = when (this) {
    Destino.GRUPOS -> Icons.Outlined.Group
    Destino.GRAFICAS -> Icons.Outlined.BarChart
    Destino.INICIO -> Icons.Outlined.Home
    Destino.PRESUPUESTO -> Icons.Outlined.AccountBalanceWallet
    Destino.PERFIL -> Icons.Outlined.Person
}

@Composable
fun BarraNavegacion(
    actual: Destino,
    onNavegar: (Destino) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Superficie
    ) {
        Destino.entries.forEach { destino ->
            NavigationBarItem(
                selected = destino == actual,
                onClick = { onNavegar(destino) },
                icon = { Icon(destino.icono(), contentDescription = destino.etiqueta) },
                label = {
                    Text(
                        destino.etiqueta,
                        fontFamily = Manrope,
                        fontSize = 11.sp,
                        maxLines = 1
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Colores.iconoNavActivo,
                    selectedTextColor = Colores.iconoNavActivo,
                    indicatorColor = Colores.indicadorNav,
                    unselectedIconColor = TextoSecundario,
                    unselectedTextColor = TextoSecundario
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BarraNavegacionPreview() {
    GestorGastosTheme {
        var actual by remember { mutableStateOf(Destino.GRAFICAS) }
        BarraNavegacion(actual = actual, onNavegar = { actual = it })
    }
}
