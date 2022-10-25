package com.furthergrow.ra1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furthergrow.ra1.Model.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val objeto_view_model : MainViewModel by viewModels()
        setContent {



            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center)
                {

                    TextShadow()

                }
                Spacer(modifier = Modifier.padding(5.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    //Agregar Nombre

                    Text(text = "Nombre ", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Image(painterResource(id = R.drawable.ic_nombre), contentDescription =null )

                    nombre(objeto_view_model)
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    Spinner1(objeto_view_model)
                }

                Spacer(modifier = Modifier.padding(5.dp))

                Row(modifier = Modifier.padding(5.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween)
                {

                    Spinner2(objeto_view_model)
                }

                Spacer(modifier = Modifier.padding(5.dp))


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween)
                {

                    Spinner3(objeto_view_model)
                }
                
                Spacer(modifier = Modifier.padding(10.dp))


                Divider()

                mostrar_datos(objeto_view_model)


                Divider()

                Spacer(modifier = Modifier.padding(15.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {

                    cotizar(objeto_view_model)
                    //Spacer(modifier = Modifier.padding(80.dp))
                    reset(objeto_view_model)

                }

            }



        }
    }
}




@Composable
fun nombre(objeto_view_Model: MainViewModel)
{

    var nombre = remember { mutableStateOf("") }
    Row(

        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,

    )
    {
        TextField(
            value = nombre.value,
            onValueChange =
            {
                nombre.value = it


            },

            Modifier.width(170.dp),
            keyboardOptions = KeyboardOptions(KeyboardCapitalization.None, true, KeyboardType.Text),

        )


        Button(onClick = { objeto_view_Model.definirnombre(nombre.value.toString())
        },
                colors = ButtonDefaults.buttonColors(Color.Gray)

        )
        {
         Text(text = "Agregar", color = Color.White)
        }
    }
}






@Composable
fun Spinner1(objeto_view_Model: MainViewModel)
{
    Row(

        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    )
    {

        Text(text = "Marca", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        generarSpinner1(objeto_view_Model)
    }

}

@Composable
fun generarSpinner1(objeto_view_Model: MainViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Honda Accord   $678,026.22 " , "Vw Touareg   $879,266.16" , "BMW X5   $1,025,366.87", "Mazda CX7   988,641.02")


    Box {
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        )
        {
            Text ("Modelos")
            Spacer(modifier = Modifier.padding(3.dp))
            Image(painterResource(id = R.drawable.ic_carro), contentDescription =null )

        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    //action for click
                    expanded = false
                    objeto_view_Model.selectmarca(suggestions.indexOf(label))
                    
                })
                {
                    Text(text = label)
                }
            }

        }
    }
}


@Composable
fun Spinner2(objeto_view_Model: MainViewModel)
{
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {

        Text(text = "Enganche", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        generarSpinner2(objeto_view_Model)
    }

}



@Composable
fun generarSpinner2(objeto_view_Model: MainViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("20%", "40%", "60%")



    Box {
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        )
        {
            Text ("Seleccione %")
            Spacer(modifier = Modifier.padding(3.dp))
            Image(painterResource(id = R.drawable.ic_money), contentDescription =null )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = true },
        )
        {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    //action for click
                    expanded = false
                    objeto_view_Model.selectporcentaje(suggestions.indexOf(label))
                })
                {
                    Text(text = label)
                }
            }

        }
    }
}

@Composable
fun Spinner3(objeto_view_Model: MainViewModel)
{
    Row(

        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {

        Text(text = "Financiamiento Anual",fontWeight = FontWeight.Bold, fontSize = 15.sp)
        generarSpinner3(objeto_view_Model)
    }

}



@Composable
fun generarSpinner3(objeto_view_Model: MainViewModel)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("1 año 7.5%", "2 años 9.5%", "3 años 10.3%", "4 años 12.6%", "5 años 13.5%" )



    Box {
        Button(onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        )
        {
            Text ("Seleccione Plazo",)
            Spacer(modifier = Modifier.padding(3.dp))
            Image(painterResource(id = R.drawable.ic_calend), contentDescription =null )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = true },
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    //action for click
                    expanded = false
                    objeto_view_Model.selectinanciamiento(suggestions.indexOf(label))
                }) {
                    Text(text = label)
                }
            }

        }
    }
}

@Composable
fun cotizar(objeto_view_Model: MainViewModel)
{
    Button(onClick = {

    },
        colors = ButtonDefaults.buttonColors(Color.Gray)
    )
    {
        Text(text = "Cotizar", color = Color.White)
    }

}


@Composable
fun mostrar_datos(objeto_view_Model: MainViewModel)
{
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        horizontalAlignment = Alignment.Start
    )
    {

        Text(text = "Cliente :  " + objeto_view_Model.nombre.value.toString(), fontStyle = FontStyle.Italic )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Modelo de Auto : " + objeto_view_Model.modelocarro.value.toString(), fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Enganche de  :  % " + objeto_view_Model.descuento.value.toString() + "  de  " + objeto_view_Model.enganche.value.toString(), fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Monto a financiar :  $ " + objeto_view_Model.financiamiento.value.toString(), fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Financiamiento a  : $ " + objeto_view_Model.plazo.value.toString(), fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Monto de interes por  $ " + objeto_view_Model.anual.value.toString() + "  años ",fontStyle = FontStyle.Italic)
        Text(text = "$ " + objeto_view_Model.interes.value.toString() , fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Monto a finanziar + interes = ",fontStyle = FontStyle.Italic)
        Text(text =" $ ${ objeto_view_Model.financiamiento.value }  +  $ ${objeto_view_Model.interes.value.toString()}  =  $ ${objeto_view_Model.total.value.toString()} ", fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Pagos mensuales por =  $  " + objeto_view_Model.pagomensual.value.toString(), fontStyle = FontStyle.Italic)
        Spacer(modifier = Modifier.padding(5.dp))
        Text(text = "Costo TOTAL  =  ",fontStyle = FontStyle.Italic)
        Text(text = "$${objeto_view_Model.enganche.value.toString()} + $${objeto_view_Model.total.value.toString()} = $${objeto_view_Model.enganche.value + objeto_view_Model.total.value}", fontStyle = FontStyle.Italic)


    }
}

@Composable
fun reset(objeto_view_Model:MainViewModel)
{
    Button(onClick = { objeto_view_Model.reset() },
        colors = ButtonDefaults.buttonColors(Color.Gray)
    )
    {
        Text(text = "Reiniciar", color = Color.White)
    }

}

//Texto con sombra
@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "Cotizador de AUTOS NUEVOS",
        style = TextStyle(
            fontSize = 25.sp,
            shadow = Shadow( //para darle sombra
                color = Color.Gray,
                offset = offset,
                blurRadius = 3f
            )
        )
    )
}

