package atharv.garg.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import atharv.garg.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("centimeters") }
    var outputUnit by remember { mutableStateOf("feet") }
    var iExpanded by remember{ mutableStateOf(false) }
    var oExpanded by remember{ mutableStateOf(false) }
    val conversionFactor=remember{mutableStateOf(1.0)}
    val oconversionFactor=remember{mutableStateOf(1.0)}

    fun convertUnits(){
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result=((inputValueDouble*conversionFactor.value)/oconversionFactor.value)
        outputValue=result.toString()
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("UnitConverter",style= MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value=inputValue, onValueChange ={
            inputValue=it
        },label={Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box{
                Button(onClick={iExpanded=true}) {
                    Text(text=inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription="arrow down")
                }
                DropdownMenu(expanded=iExpanded, onDismissRequest={iExpanded=false}) {
                    DropdownMenuItem(
                    text={Text("feet")},onClick={
                        iExpanded=false
                            inputUnit="feet"
                            conversionFactor.value=0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text={Text("centimeters")},onClick={
                            iExpanded=false
                            inputUnit="centimeters"
                            conversionFactor.value=0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text={Text("meters")},onClick={
                            iExpanded=false
                            inputUnit="meters"
                            conversionFactor.value=1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text={Text("yard")},onClick={
                            iExpanded=false
                            inputUnit="yard"
                            conversionFactor.value=0.914
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                Button(onClick={oExpanded=true}) {
                    Text(text=outputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription="arrow down")
                }
                DropdownMenu(expanded=oExpanded, onDismissRequest={oExpanded=false}) {
                    DropdownMenuItem(
                        text={Text("feet")},onClick={
                            oExpanded=false
                            outputUnit="feet"
                            oconversionFactor.value=0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text={Text("centimeters")},onClick={
                            oExpanded=false
                            outputUnit="centimeters"
                            oconversionFactor.value=0.01
                            convertUnits()})
                    DropdownMenuItem(
                        text={Text("meters")},onClick={
                            oExpanded=false
                            outputUnit="meters"
                            oconversionFactor.value=1.0
                            convertUnits()})
                    DropdownMenuItem(
                        text={Text("yard")},onClick={
                            oExpanded=false
                            outputUnit="yard"
                            oconversionFactor.value=1.094
                            convertUnits()})
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",style= MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground =true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}