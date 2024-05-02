package com.example.jetpackcompose

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    App()
                }
            }
        }
    }
}
/*
//State Variabel
@Composable
fun App()
{
    var title by remember{
        mutableStateOf("")
    }
    val hellos = listOf("Garp", "Gandalf", "Master Roshi","Deckard Cain", "Oldman Logan", "Yoda")
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = title)
        for(hello in hellos)
        {
            Greeting(name = hello)
            Button(onClick = { title = hello }) {
                Text(text = "BUTTON")
            }
        }
        OutlinedTextField(value = title, placeholder = {Text("GANDLF SUGER")}, onValueChange = { title = it })
    }
}*/

@Composable
fun App()
{
    var employees by remember {
        mutableStateOf(listOf<Employee>())
    }
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddEmployee(employees) { result ->
                    employees = result
        }
        ShowEmployee(employees = employees)
       }
}
/*
// List
@Composable
fun App()
{
    val hellos = listOf("Garp", "Gandalf", "Master Roshi","Deckard Cain", "Oldman Logan", "Yoda")
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
       ) {
            for(hello in hellos)
            {
                Greeting(name = hello)
            }
    }}*/
/*
basic stuff
@Composable
fun App()
{
    val hellos = listOf("GirlyMcGirlface", "ScrubyMcScrubface", "maxyMcMaxface","Deckard Cain")
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {

        Greeting("GirlyMcGirlFace")
        Greeting("SrubyMcScrubFace")
        Greeting("MaxyMcMaxFace")
        Greeting("NameyMcNameFace")
        Text(text = "im a text")
        Button(onClick = { /*TODO*/ }) {
        Text(text = "BUTTON")
}
    }
}*/
@Composable
fun ShowEmployee( employees: List<Employee>)
{
    LazyColumn {
            items(employees, ){
                    EmployeeItem(employee = it)
                Spacer(modifier = Modifier.height(5.dp))
            }
    }
}
@Composable
fun EmployeeItem(employee: Employee)
{
    Column ( modifier = Modifier.background(Color(0xFFAAC2DC)).
    fillMaxWidth().
    clip(shape = RoundedCornerShape(5.dp)).
    padding(10.dp)
   ){
        Text(text = "\nName: ${employee.name}", modifier = Modifier.padding(3.dp))
        Text(text = "age: ${employee.age}", modifier = Modifier.padding(3.dp))
        Text(text = "salary:${employee.salary}\n", modifier = Modifier.padding(3.dp))
    }
}
@Composable
fun AddEmployee( employees:  List<Employee>, updatedList: (List<Employee>) -> Unit) {
    var name by remember{
        mutableStateOf("")
    }
    var salary by remember {
        mutableStateOf(0)
    }
    var age by remember {
        mutableStateOf(0)
    }
Column {
    OutlinedTextField(value = name, placeholder = {Text("name")}, onValueChange = { text -> name = text })

    OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = age.toString(),
            placeholder = { Text("age") },
            onValueChange = { text -> age = text.toInt()})

    OutlinedTextField(keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = salary.toString(),
            placeholder = { Text("salary") },
            onValueChange = { text -> salary = text.toInt() })


    Button(onClick = {
           var newEmployee = Employee(name, age, salary)
           // employees.add(newEmployee)
        val newlist = employees + newEmployee
        println(employees)
        updatedList(newlist)
    }) {
            Text(text = "Add Employee")
        }
}
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackcomposeTheme {
        App()
    }
}