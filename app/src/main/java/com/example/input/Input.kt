package com.example.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDataDiri(modifier: Modifier){
    //variable untuk mengingat nilai masukan dari keyboard
    var textNama by remember { mutableStateOf("") }
    var textAlamat by remember { mutableStateOf("") }
    var textJK by remember { mutableStateOf("") }
    var textSP by remember { mutableStateOf("")}

    //variable untuk menyimpan data yang diperoleh dari komponen UI
    var nama by remember {mutableStateOf("")}
    var alamat by remember {mutableStateOf("")}
    var jenis by remember {mutableStateOf("")}
    var status by remember {mutableStateOf("")}

    val gender:List<String> = listOf("Laki-laki", "Perempuan")
    val perkawinan:List<String> = listOf("Janda", "Lajang", "Duda")

    var showDialog by remember { mutableStateOf(false) }


    Column(modifier = Modifier
        .padding(top = 50.dp)
        .background(color = Color.LightGray)
        .fillMaxSize(),
    ){
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.purple_200),
            titleContentColor = colorResource(R.color.white)
        ),
            title = {
                Text(stringResource(R.string.form_name))
            })
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            Text("NAMA LENGKAP",
                modifier = Modifier.padding(top = 10.dp, start = 10.dp))
            OutlinedTextField(
                value = textNama,
                singleLine = true,
                shape = MaterialTheme.shapes.small,
                label = {Text("Isian Nama Lengkap")},
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                onValueChange = {
                    textNama = it
                }
            )
            Text("JENIS KELAMIN",
                modifier = Modifier.padding(top = 10.dp, start = 10.dp))
            Column {
                gender.forEach { item ->
                    Row(modifier = Modifier.selectable(
                        selected = textJK == item,
                        onClick = { textJK = item}
                    ), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = textJK == item,
                            onClick = {
                                textJK = item
                            }
                        )
                        Text(item)
                    }
                }
            }
            Text("STATUS PERKAWINAN",
                modifier = Modifier.padding(top = 10.dp, start = 10.dp))
            Column{
                perkawinan.forEach { item ->
                    Row(modifier = Modifier.selectable(
                        selected = textSP == item,
                        onClick = { textSP = item}
                    ), verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = textSP == item,
                            onClick = {
                                textSP = item
                            }
                        )
                        Text(item)
                    }
                }
            }
            Text("Alamat",
                modifier = Modifier.padding(top = 10.dp, start = 10.dp))
            OutlinedTextField(
                value = textAlamat,
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                label = { Text(text = "Alamat Lengkap") },
                onValueChange = {
                    textAlamat = it
                }
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.purple_500),
                    contentColor = colorResource(R.color.white)
                ),
                // the button is enable when the user makes a selection
                enabled = textAlamat.isNotEmpty(),
                onClick = {
                    nama = textNama
                    jenis = textJK
                    status = textSP
                    alamat = textAlamat
                    showDialog = true
                }
            ) {
                Text(stringResource(R.string.submit))
            }

            if (showDialog) {
                Dialog(onDismissRequest = { showDialog = false }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Nama   : "+nama)
                            Text(text = "Gender   : "+jenis)
                            Text(text = "Status   : "+status)
                            Text(text = "Alamat   : "+alamat)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { showDialog = false }) {
                                Text("Close")
                            }
                        }
                    }
                }
            }
        }
    }
}