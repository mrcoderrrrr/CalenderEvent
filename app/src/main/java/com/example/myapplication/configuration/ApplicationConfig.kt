package com.example.myapplication.configuration

import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ApplicationConfig : ComponentActivity() {
    @Composable
    fun HeaderText(text: String) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = Color.Black,
                fontStyle = FontStyle.Normal,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            ),
        )
    }
    @Composable
    fun NormalText(text: String,onTextClick: ()-> Unit) {
        Text(
            text = text,
            style = TextStyle(color = Color.Black, fontStyle = FontStyle.Normal, fontSize = 20.sp),
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp).clickable{onTextClick()}
        )
    }

    @Composable
    fun TitleText(text: String, onValueChange: (String) -> Unit, label: String) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                .clickable { },
            label = {
                Text(text = label)
            }
        )
    }

    @Composable
    fun ButtonClick(text: String, onButtonClick: () -> Unit) {
        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, top = 5.dp, bottom = 5.dp)
        ) {
            Text(text = text)
        }
    }
}

