package ortega.adolfo.gestorgastos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ortega.adolfo.gestorgastos.navigation.NavegacionApp
import ortega.adolfo.gestorgastos.ui.theme.GestorGastosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestorGastosTheme {
                NavegacionApp()
            }
        }
    }
}
