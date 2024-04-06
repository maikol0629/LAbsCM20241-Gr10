package co.edu.udea.compumovil.gr10_20241.lab1.Screens
import android.app.DatePickerDialog
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr10_20241.lab1.Navigation.AppScreens
import co.edu.udea.compumovil.gr10_20241.lab1.PersonalDate
import co.edu.udea.compumovil.gr10_20241.lab1.hasNullOrEmptyFields
import kotlinx.coroutines.delay
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen1(
navController: NavController
){
    val config = LocalConfiguration.current
    val mode = remember {
        mutableStateOf(config.orientation)
    }

    if(mode.value == Configuration.ORIENTATION_PORTRAIT){

        MainPortrait(navController)
    }else{
       MainLandscape(navController)
    }

}
@Composable
fun DataName(
    modifier: Modifier = Modifier,
    onNameChange:  (String?) -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }
    TextField(
        value = name,
        onValueChange = {name = it
            onNameChange (it)

        },
        modifier = modifier
            .background(Color.Transparent),
        leadingIcon = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
        },
        placeholder = { Text(text = "Name")},
        singleLine = true

    )

}



@Composable
fun DataLastName(
    modifier: Modifier = Modifier,
    onLastNameChange: (String?) -> Unit
) {
    var lastName by remember {
        mutableStateOf("")
    }
    TextField(
        value = lastName,
        onValueChange = {
                        lastName = it
            onLastNameChange(it)
        },
        modifier = modifier,
        leadingIcon = {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
        },
        placeholder = { Text(text = "Last Name")},
        singleLine = true

    )

}

@Composable
fun DataSex(
    options: List<String>,
    selectedOption: MutableState<String>,
    modifier: Modifier,
    onSexSelected: (String)-> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sex",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            options.forEach { option ->
                Row(
                    modifier = Modifier.padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (option == selectedOption.value),
                        onClick = { selectedOption.value = option
                                  onSexSelected(option)},
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker1(
    dateSelected: (String)->Unit,
) {
    var date by remember {
        mutableStateOf("Select BirthDay")
    }
    val calendar = Calendar.getInstance()
    val year:Int
    val month:Int
    val day:Int
     year = calendar.get(Calendar.YEAR)
     month = calendar.get(Calendar.MONTH)
     day = calendar.get(Calendar.DAY_OF_MONTH)
    val dialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, year: Int, month: Int, day: Int ->
            date = "$day/${month+1}/$year"
            dateSelected(date)
        },year,month,day
    )
    Box(modifier = Modifier.fillMaxWidth()){
        Row (modifier = Modifier.align(Alignment.Center)) {
            OutlinedTextField(value = date, onValueChange ={date = it},
                readOnly = true,
                )
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .size(50.dp)
                    .clickable { dialog.show() }
            )
        }

    }

    }




@Composable
fun DataEducation(
    modifier: Modifier,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selecItem by remember {
        mutableStateOf("Nivel Educativo")
    }
    Column (

    ) {
        OutlinedTextField(
            value = selecItem,
            onValueChange = {
            },
            modifier = modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            enabled = false
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.Transparent
                )
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item)
                    expanded = false
                    selecItem = item
                },
                    text = {Text(text = item, color = Color.White)},
                    modifier = Modifier.background(Color.LightGray)

                    )
            }
        }
    }
}
@Composable
fun ButtonNext(
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp) // Espacio a la derecha
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End) // Alineado a la derecha
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text)
        }
    }
}
@Composable
fun ShortMessageSnackbar(
    message: String,
    durationMillis: Int = 5000, // Duración predeterminada del mensaje en milisegundos
    onDismiss: () -> Unit = {}
) {
    var showMessage by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        showMessage = true
        delay(durationMillis.toLong())
        showMessage = false
        onDismiss()
    }

    if (showMessage) {
        Snackbar(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(message)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPortrait(
    navController: NavController
) {
    val sexOption = remember { mutableStateOf("") }
    val options = listOf("Men", "Women")
    val user = remember { PersonalDate() }
    val correct = remember { mutableStateOf(false) }
    val snackbarVisibleState = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "INFORMACION PERSONAL",
                modifier = Modifier.align(Alignment.Top

                    )
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = TextUnit.Unspecified

                )
        }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(35.dp),
        contentAlignment = Alignment.Center

    )  {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DataName(modifier = Modifier.fillMaxWidth()) { newName ->
                    user.name = newName ?: ""
                }
                DataLastName(modifier = Modifier.fillMaxWidth()) { newLastName ->
                    user.lastName = newLastName ?: ""
                }
                DataSex(
                    modifier = Modifier.fillMaxWidth(),
                    options = options,
                    selectedOption = sexOption
                ) {
                    user.sex = it.toString()
                }
                DataEducation(
                    modifier = Modifier.fillMaxWidth(),
                    items = listOf("Primaria", "Secundaria", "Universidad", "Otro")
                ) {
                    user.education = it
                }
                DatePicker1() { date ->
                    user.birthdatDate = date
                }
                Spacer(modifier = Modifier.height(50.dp))
                ButtonNext(text = "Siguiente") {
                    if (user.hasNullOrEmptyFields()) {
                        snackbarVisibleState.value = true
                    } else {
                        correct.value = true
                    } 
                }
                if (snackbarVisibleState.value) {
                    ShortMessageSnackbar(message = "¡Campos Vacíos!") {
                        snackbarVisibleState.value = false
                    }
                } else if (correct.value) {
                        navController.navigate(route = AppScreens.contactData.route)
                    Log.i("INFORMACION PERSONAL/n",
                        "${user.name} ${user.lastName} /n" +
                                "${user.sex} /n" +
                                "${user.birthdatDate} /n" +
                                "${user.education}")
                    correct.value = false
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainLandscape(navController: NavController) {
    val sexOption = remember { mutableStateOf("") }
    val options = listOf("Men", "Women")
    val user = remember { PersonalDate() }
    val correct = remember { mutableStateOf(false) }
    val snackbarVisibleState = remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Text(text = "INFORMACION PERSONAL",
                modifier = Modifier.align(Alignment.Top

                )
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = TextUnit.Unspecified

            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    DataName(modifier = Modifier.padding(8.dp)) { newName ->
                        user.name = newName ?: ""
                    }
                    DataLastName(modifier = Modifier.padding(8.dp)) { newLastName ->
                        user.lastName = newLastName ?: ""
                    }
                }

                DataSex(
                    modifier = Modifier.padding(8.dp),
                    options = options,
                    selectedOption = sexOption
                ) {
                    user.sex = it ?: ""
                }

                DatePicker1() { date ->
                    user.birthdatDate = date
                }

                DataEducation(
                    modifier = Modifier.fillMaxWidth(),
                    items = listOf("Primaria", "Secundaria", "Universidad", "Otro")
                ) {
                    user.education = it
                }
                Spacer(modifier = Modifier.height(50.dp))
                ButtonNext(text = "Siguiente") {
                    if (user.hasNullOrEmptyFields()) {
                        snackbarVisibleState.value = true
                    } else {
                        correct.value = true
                    }
                }
                if (snackbarVisibleState.value) {
                    ShortMessageSnackbar(message = "¡Campos Vacíos!") {
                        snackbarVisibleState.value = false
                    }
                } else if (correct.value) {
                    navController.navigate(route = AppScreens.contactData.route)
                    Log.i(
                        "INFORMACION PERSONAL/n",
                        "${user.name} ${user.lastName} /n" +
                                "${user.sex} /n" +
                                "${user.birthdatDate} /n" +
                                "${user.education}"
                    )
                    correct.value = false
                }
            }
        }
    }
}






@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DataPickerPreview() {
    DatePicker1(){}
}
@Preview
@Composable
fun DataEducationPreview() {
    DataEducation(items = listOf("primaria","secundaria","universidad", "otro"),  onItemSelected = {it}, modifier = Modifier)
}

@Preview
@Composable
fun DataNamePreview() {
    val (name, setName) = remember { mutableStateOf<String?>(null) }

    // En la vista previa, simulamos la función de devolución de llamada
    DataName(modifier = Modifier, onNameChange = setName)
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun DataLastNamePreview() {
    val (lastName, setLastName) = remember { mutableStateOf<String?>(null) }

    // En la vista previa, simulamos la función de devolución de llamada
    DataLastName(modifier = Modifier, onLastNameChange = setLastName)
}
@Preview
@Composable
fun DataSexPreview() {
    val options = listOf("Men", "Women")

    DataSex( options = options, selectedOption = remember {
        mutableStateOf("Men")
    }, modifier = Modifier, onSexSelected = {}  )
}