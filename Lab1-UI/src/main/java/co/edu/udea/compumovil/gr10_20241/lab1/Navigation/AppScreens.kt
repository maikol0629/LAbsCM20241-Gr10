package co.edu.udea.compumovil.gr10_20241.lab1.Navigation

sealed class AppScreens (val route: String){

    object personalData: AppScreens("HomeScreen1")
     object contactData: AppScreens("HomeScreen2")

}