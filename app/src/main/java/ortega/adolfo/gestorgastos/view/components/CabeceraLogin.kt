package ortega.adolfo.gestorgastos.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import ortega.adolfo.gestorgastos.R
import ortega.adolfo.gestorgastos.ui.theme.Nunito
import ortega.adolfo.gestorgastos.ui.theme.TextoPrincipal

@Composable
fun CabeceraLogin(modifier: Modifier = Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(48.dp)
        )
        Spacer(Modifier.width(10.dp))

        Text(
            "NeuroBag",
            fontFamily = Nunito,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = TextoPrincipal
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CabeceraPreview(){
    CabeceraLogin(modifier = Modifier.padding(16.dp))
}