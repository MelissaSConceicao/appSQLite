package com.example.appsqlite.ui.components

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsqlite.db.DBHandler

@Composable
fun Form(
    context : Context
) {
    val activity = context as Activity

    val name = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    val duration = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    val tracks = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }
    val description = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue("", TextRange(0, 7)))
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val dbHandler: DBHandler = DBHandler(context)

            Text(textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                text ="SQLite Database in Android")

            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Enter your course name") }
            )

            TextField(
                value = duration.value,
                onValueChange = { duration.value = it },
                label = { Text("Enter your course duration") }
            )

            TextField(
                value = tracks.value,
                onValueChange = { tracks.value = it },
                label = { Text("Enter your course tracks") }
            )

            TextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Enter your course description") }
            )

            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    dbHandler.addNewCourse(
                        name.value.text,
                        duration.value.text,
                        description.value.text,
                        tracks.value.text
                    )
                    Toast.makeText(context, "Course Added to Database", Toast.LENGTH_SHORT).show()
                })
                {
                    Text("Add Course to Database", color = Color.White)
                }
                Button(onClick = { /* Do something! */ }) { Text("Read Courses to Database") }
            }

        }

    }
}
