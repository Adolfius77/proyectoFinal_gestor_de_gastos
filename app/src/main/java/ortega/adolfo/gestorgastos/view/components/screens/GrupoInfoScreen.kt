package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Undo
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Manrope
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.view.components.AvatarGrupo
import ortega.adolfo.gestorgastos.view.components.BotonAbandonar
import ortega.adolfo.gestorgastos.view.components.CodigoGrupo
import ortega.adolfo.gestorgastos.view.components.ItemMiembro
import ortega.adolfo.gestorgastos.view.components.RojoAbandonar
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun GrupoInfoScreen(
    grupo: Grupo,
    modifier: Modifier = Modifier,
    onVolver: () -> Unit = {},
    onAbandonar: () -> Unit = {}
) {
    var confirmando by remember { mutableStateOf(false) }

    if (confirmando) {
        AlertDialog(
            onDismissRequest = { confirmando = false },
            title = { Text("¿Abandonar el grupo?") },
            text = { Text("Saldrás de ${grupo.nombre} y dejarás de ver sus gastos.") },
            confirmButton = {
                TextButton(onClick = {
                    confirmando = false
                    onAbandonar()
                }) {
                    Text("Abandonar", color = RojoAbandonar)
                }
            },
            dismissButton = {
                TextButton(onClick = { confirmando = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
    ) {
        IconButton(
            onClick = onVolver,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.Undo,
                contentDescription = "Volver",
                tint = TextoPrincipal,
                modifier = Modifier.size(28.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AvatarGrupo(imagen = grupo.imagen, tamano = 110.dp)
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = grupo.nombre,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        color = TextoPrincipal
                    )
                    if (grupo.codigo.isNotBlank()) {
                        Spacer(Modifier.height(14.dp))
                        CodigoGrupo(codigo = grupo.codigo)
                    }
                    Spacer(Modifier.height(32.dp))
                }
            }

            item {
                Text(
                    text = "Miembros(${grupo.miembros.size})",
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = TextoPrincipal,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            itemsIndexed(grupo.miembros, key = { _, m -> m.id }) { index, miembro ->
                Box(modifier = Modifier.background(Color.Black.copy(alpha = 0.08f))) {
                    ItemMiembro(miembro = miembro)
                }
                if (index < grupo.miembros.lastIndex) {
                    HorizontalDivider(color = Color.Black.copy(alpha = 0.1f))
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            BotonAbandonar(
                onClick = { confirmando = true },
                modifier = Modifier.fillMaxWidth(0.7f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GrupoInfoScreenPreview() {
    GestorGastosTheme {
        GrupoInfoScreen(grupo = DatosMock.grupos.first())
    }
}