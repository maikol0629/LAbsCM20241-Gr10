package co.edu.udea.compumovil.gr10_20241.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.edu.udea.compumovil.gr10_20241.lab1.Navigation.AppNavigation
import co.edu.udea.compumovil.gr10_20241.lab1.theme.LabsCm20241Gr10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabsCm20241Gr10Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }


            }
        }
    }
}






