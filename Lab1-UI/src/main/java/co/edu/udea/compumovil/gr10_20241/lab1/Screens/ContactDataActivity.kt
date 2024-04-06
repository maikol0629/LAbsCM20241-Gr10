package co.edu.udea.compumovil.gr10_20241.lab1.Screens

import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr10_20241.lab1.ContactData
import co.edu.udea.compumovil.gr10_20241.lab1.Navigation.AppScreens
import co.edu.udea.compumovil.gr10_20241.lab1.R
import co.edu.udea.compumovil.gr10_20241.lab1.hasNullOrEmptyFields


@Composable
fun dataContactPortrait(
                        navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .padding(25.dp)
            ) {
            Column (modifier = Modifier.padding(15.dp)) {

                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Información de Contacto",


                        )
                }

                Box(modifier = Modifier
                    .padding(30.dp)) {

                    Column {


                        var phone by remember {
                            mutableStateOf("")

                        }
                        var email by remember {
                            mutableStateOf("")

                        }
                        var country by remember {
                            mutableStateOf("")

                        }
                        var city by remember {
                            mutableStateOf("")

                        }
                        var adress by remember {
                            mutableStateOf("")

                        }
                        val snackbarVisibleState = remember { mutableStateOf(false) }
                        val correct = remember { mutableStateOf(false) }
                        TextField(
                            value = phone,
                            onValueChange = {
                                phone = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                            },
                            placeholder = { Text(text = "Phone") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )


                        )

                        TextField(
                            value = email,
                            onValueChange = {
                                email = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp),
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Email, contentDescription = null)
                            },
                            placeholder = { Text(text = "Email") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )

                        )
                        TextField(
                            value = country,
                            onValueChange = {
                                country = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                                ,
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.country), contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)

                                    )
                            },
                            placeholder = { Text(text = "Country") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )

                        )

                        TextField(
                            value = city,
                            onValueChange = {
                                city = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.ciudad), contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)

                                )
                            },
                            placeholder = { Text(text = "City") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )

                        )
                        TextField(
                            value = adress,
                            onValueChange = {
                                adress = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.direccion), contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)

                                )
                            },
                            placeholder = { Text(text = "Address") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Ascii,
                                imeAction = ImeAction.Next
                            )

                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        ButtonNext(text = "Next") {

                            if(phone.isNullOrEmpty()||email.isNullOrEmpty()||country.isNullOrEmpty()){
                                snackbarVisibleState.value = true
                            }else{
                                correct.value = true
                            }


                        }

                        if (snackbarVisibleState.value) {
                            ShortMessageSnackbar(message = "¡Campos Vacíos!") {
                                snackbarVisibleState.value = false
                            }
                        } else if (correct.value) {
                            navController.navigate(route = AppScreens.personalData.route)
                            Log.i("CON","INFORMACION DE CONTACTO: "+"/n"+
                                    "Télefono: ${phone} " + "/n"+
                                    "Dirección: ${adress}" + "/n"+
                                    "Email: ${email}"+ "/n" +
                                    "País: ${country}"+"/n"+
                                    "Ciudad: ${city}")
                            correct.value = false
                        }

                    }
                }
            }
        }
    }

}


@Composable
fun dataContactLandscape(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .padding(25.dp)
        ) {
            Column (modifier = Modifier.padding(15.dp)) {

                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Información de Contacto",


                        )
                }

                Box(modifier = Modifier
                    .padding(30.dp)) {

                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {


                        var phone by remember {
                            mutableStateOf("")

                        }
                        var email by remember {
                            mutableStateOf("")

                        }
                        var country by remember {
                            mutableStateOf("")

                        }
                        var city by remember {
                            mutableStateOf("")

                        }
                        var adress by remember {
                            mutableStateOf("")

                        }
                        val snackbarVisibleState = remember { mutableStateOf(false) }
                        val correct = remember { mutableStateOf(false) }
                        TextField(
                            value = phone,
                            onValueChange = {
                                phone = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                            },
                            placeholder = { Text(text = "Phone") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            )


                        )

                        TextField(
                            value = email,
                            onValueChange = {
                                email=it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp),
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Email, contentDescription = null)
                            },
                            placeholder = { Text(text = "Email") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )

                        )
                        TextField(
                            value = country,
                            onValueChange = {
                                country = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.country), contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)

                                )
                            },
                            placeholder = { Text(text = "Country") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Ascii,
                                imeAction = ImeAction.Next
                            )

                        )

                        TextField(
                            value = city,
                            onValueChange = {
                                city = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.ciudad), contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)

                                )
                            },
                            placeholder = { Text(text = "City") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )

                        )
                        TextField(
                            value = adress,
                            onValueChange = {
                                adress = it

                            },
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(15.dp)
                            ,
                            leadingIcon = {
                                Icon(painter = painterResource(id = R.drawable.direccion), contentDescription = null,
                                    modifier = Modifier
                                        .height(30.dp)

                                )
                            },
                            placeholder = { Text(text = "Address") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )

                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        ButtonNext(text = "Next") {

                            if(phone.isNullOrEmpty()||email.isNullOrEmpty()||country.isNullOrEmpty()){
                                snackbarVisibleState.value = true
                            }else{
                                correct.value = true
                            }


                        }

                        if (snackbarVisibleState.value) {
                            ShortMessageSnackbar(message = "¡Campos Vacíos!") {
                                snackbarVisibleState.value = false
                            }
                        } else if (correct.value) {
                            navController.navigate(route = AppScreens.personalData.route)
                            Log.i("CON","INFORMACION DE CONTACTO: "+"/n"+
                                    "Télefono: ${phone} " + "/n"+
                                    "Dirección: ${adress}" + "/n"+
                                    "Email: ${email}"+ "/n" +
                                    "País: ${country}"+"/n"+
                                    "Ciudad: ${city}")
                            correct.value = false
                        }

                    }
                }
            }
        }
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen2(
    navController: NavController
){
    val config = LocalConfiguration.current
    val mode = remember {
        mutableStateOf(config.orientation)
    }

    if(mode.value == Configuration.ORIENTATION_PORTRAIT){
        dataContactPortrait(navController)
    }else{
        dataContactLandscape(navController)
    }

}

