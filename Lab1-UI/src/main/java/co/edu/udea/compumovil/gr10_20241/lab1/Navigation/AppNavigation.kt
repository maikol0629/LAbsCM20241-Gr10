package co.edu.udea.compumovil.gr10_20241.lab1.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr10_20241.lab1.Screens.HomeScreen1
import co.edu.udea.compumovil.gr10_20241.lab1.Screens.HomeScreen2

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.personalData.route){
        composable(route = AppScreens.personalData.route){

            HomeScreen1(navController)

        }
        composable(route = AppScreens.contactData.route){

            HomeScreen2(navController)
        }

    }
}
