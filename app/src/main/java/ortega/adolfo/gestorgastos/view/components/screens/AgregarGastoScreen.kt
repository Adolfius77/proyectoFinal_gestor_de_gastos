package ortega.adolfo.gestorgastos.view.components.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Undo
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import ortega.adolfo.gestorgastos.model.domain.Grupo
import ortega.adolfo.gestorgastos.model.domain.Miembro
import ortega.adolfo.gestorgastos.model.domain.data.DatosMock
import ortega.adolfo.gestorgastos.ui.theme.FondoClaro
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal
import ortega.adolfo.gestorgastos.view.components.BotonPrimario
import ortega.adolfo.gestorgastos.view.components.CampoDesplegable
import ortega.adolfo.gestorgastos.view.components.CampoFecha
import ortega.adolfo.gestorgastos.view.components.CampoTexto
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

@Composable
fun AgregarGastoScreen(
    grupo: Grupo,
    modifier: Modifier = Modifier,
    categorias: List<String> = DatosMock.categoriasGastoGrupo,
    onVolver: () -> Unit = {},
    onGuardar: (categoria: String, monto: Double, pagadoPor: Miembro, fecha: LocalDate) -> Unit =
        { _, _, _, _ -> }
) {
    var categoria by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var nombrePagador by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf<LocalDate?>(null) }

    var errorCategoria by remember { mutableStateOf<String?>(null) }
    var errorMonto by remember { mutableStateOf<String?>(null) }
    var errorPagador by remember { mutableStateOf<String?>(null) }
    var errorFecha by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(FondoClaro)
            .verticalScroll(rememberScrollState())
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

        Text(
            text = "Gasto ${grupo.nombre}",
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,
            color = TextoPrincipal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            CampoDesplegable(
                etiqueta = "Categoria",
                seleccion = categoria,
                opciones = categorias,
                onSeleccion = {
                    categoria = it
                    errorCategoria = null
                },
                placeholder = "Selecciona una categoría",
                error = errorCategoria
            )

            CampoTexto(
                etiqueta = "Monto",
                valor = monto,
                onValorCambio = { nuevo ->
                    // Solo números y un punto decimal con máximo 2 decimales
                    if (nuevo.matches(Regex("""\d{0,9}(\.\d{0,2})?"""))) {
                        monto = nuevo
                        errorMonto = null
                    }
                },
                placeholder = "$ 0.00",
                tipoTeclado = KeyboardType.Decimal,
                error = errorMonto
            )

            CampoDesplegable(
                etiqueta = "Miembro que pago",
                seleccion = nombrePagador,
                opciones = grupo.miembros.map { it.nombre },
                onSeleccion = {
                    nombrePagador = it
                    errorPagador = null
                },
                placeholder = "Selecciona un miembro",
                error = errorPagador
            )

            CampoFecha(
                etiqueta = "Fecha",
                fecha = fecha,
                onFechaSeleccionada = {
                    fecha = it
                    errorFecha = null
                },
                error = errorFecha,
                iconoFinal = {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = "Elegir fecha",
                        tint = TextoPrincipal
                    )
                }
            )
        }

        Spacer(Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            BotonPrimario(
                texto = "Guardar",
                onClick = {
                    val montoNumero = monto.toDoubleOrNull()
                    val pagador = grupo.miembros.firstOrNull { it.nombre == nombrePagador }

                    errorCategoria = if (categoria.isBlank()) "Elige una categoría" else null
                    errorMonto = if (montoNumero == null || montoNumero <= 0.0) "Escribe un monto mayor a 0" else null
                    errorPagador = if (pagador == null) "Elige quién pagó" else null
                    errorFecha = if (fecha == null) "Elige una fecha" else null

                    if (errorCategoria == null && errorMonto == null &&
                        errorPagador == null && errorFecha == null
                    ) {
                        onGuardar(categoria, montoNumero!!, pagador!!, fecha!!)
                    }
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AgregarGastoScreenPreview() {
    GestorGastosTheme {
        AgregarGastoScreen(grupo = DatosMock.grupos.first())
    }
}