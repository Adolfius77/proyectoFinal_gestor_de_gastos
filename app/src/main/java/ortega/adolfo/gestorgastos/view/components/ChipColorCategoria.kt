package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.gestorgastos.ui.theme.VerdeWacho


@Composable
fun ChipColorCategoria(
    color: Color,
    modifier: Modifier = Modifier
){

    Box(modifier = modifier){
        Row(
            modifier = Modifier
                .height(20.dp)
                .width(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

        }
    }
}
@Composable
@Preview(showBackground = true)
fun ChipColorCategoriaPreview(){
    ChipColorCategoria(color = Color.Black)
}