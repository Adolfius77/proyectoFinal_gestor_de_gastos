package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.view.components.CabeceraGrupos
import ortega.adolfo.gestorgastos.view.components.ItemGrupo

@Composable
fun GruposScreen(
    modifier: Modifier = Modifier,
    grupos: List<Grupo> = DatosMock.grupos,
    onCrearGrupo: () -> Unit = {},
    onUnirseGrupo: () -> Unit = {},
    onAbrirGrupo: (Grupo) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
    ) {
        CabeceraGrupos(
            onUnirseGrupo = onUnirseGrupo,
            onCrearGrupo = onCrearGrupo,
            modifier = Modifier.padding(start = 24.dp, end = 16.dp, top = 24.dp)
        )
        Spacer(Modifier.height(12.dp))
        HorizontalDivider(color = Color.Black.copy(alpha = 0.08f))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            itemsIndexed(grupos, key = { _, g -> g.id }) { index, grupo ->
                ItemGrupo(grupo = grupo, onClick = { onAbrirGrupo(grupo) })
                if (index < grupos.lastIndex) {
                    HorizontalDivider(color = Color.Black.copy(alpha = 0.08f))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GruposScreenPreview() {
    GruposScreen()
}