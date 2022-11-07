@file:OptIn(ExperimentalFoundationApi::class)

package com.example.myapplication.calender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scaleMatrix
import com.example.myapplication.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.absoluteValue

class CustomCalender : ComponentActivity() {
    var calender = Calendar.getInstance(Locale.ENGLISH)
    var dateFormat = SimpleDateFormat("MMM yyyy", Locale.ENGLISH)
    var monthFormat = SimpleDateFormat("MMMM", Locale.ENGLISH)
    var yearFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Calender()
        }
    }
/*

    fun SetUpcalender() {
        currentDate = dateFormat.format(calender.time)
    }
*/

    @ExperimentalFoundationApi
    @Composable
    private fun Calender() {
        var context = LocalContext.current
        var currentDate by remember { mutableStateOf(dateFormat.format(calender.time)) }
        var dateText by remember {
            mutableStateOf("")
        }
        var dates = remember { mutableStateListOf<Date>() }
        var SetUpCalender: () -> Unit = {
            currentDate = dateFormat.format(calender.time)
            dates.clear()
            var monthCalender: Calendar = calender.clone() as Calendar
            monthCalender.set(Calendar.DAY_OF_MONTH, 1)
            var FirstDayOfMonth = monthCalender.get(Calendar.DAY_OF_WEEK) - 1
            monthCalender.add(Calendar.DAY_OF_MONTH, -FirstDayOfMonth)
            while (dates.size <  42){
                dates.add(monthCalender.time)
                monthCalender.add(Calendar.DAY_OF_MONTH,1)
            }
        }
        var monthCalender: Calendar = calender.clone() as Calendar
        monthCalender.set(Calendar.DAY_OF_MONTH, 1)
        var FirstDayOfMonth = monthCalender.get(Calendar.DAY_OF_WEEK) - 1
        monthCalender.add(Calendar.DAY_OF_MONTH, -FirstDayOfMonth)
        while (dates.size <  42){
            dates.add(monthCalender.time)
            monthCalender.add(Calendar.DAY_OF_MONTH,1)
        }

        fun onNextClick() {
            calender.add(Calendar.MONTH, 1)
            //currentDate = dateFormat.format(calender.time)
            SetUpCalender()
        }

        fun onPreClick() {
            calender.add(Calendar.MONTH, -1)
            SetUpCalender()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(Color.DarkGray),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    onPreClick()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.black_back),
                        contentDescription = "Previous",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    text = currentDate,
                    style = TextStyle(Color.White),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = {
                    onNextClick()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.black_forward),
                        contentDescription = "Next",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Mon", style = TextStyle(Color.White), textAlign = TextAlign.Center)
                Text(text = "Tue", style = TextStyle(Color.White), textAlign = TextAlign.Center)
                Text(text = "Wed", style = TextStyle(Color.White), textAlign = TextAlign.Center)
                Text(text = "Thu", style = TextStyle(Color.White), textAlign = TextAlign.Center)
                Text(text = "Fri", style = TextStyle(Color.White), textAlign = TextAlign.Center)
                Text(text = "Sat", style = TextStyle(Color.White), textAlign = TextAlign.Center)
                Text(text = "Sun", style = TextStyle(Color.White), textAlign = TextAlign.Center)
            }
            LazyVerticalGrid(cells = GridCells.Fixed(7), modifier = Modifier.padding(2.dp)){
                items(dates.size){date->
                    monthCalender.time=dates.get(date)
                    val dayNo= monthCalender.get(Calendar.DAY_OF_MONTH).toString()
                    val displayMonth=monthCalender.get(Calendar.MONTH)+1
                    val displayYear=monthCalender.get(Calendar.YEAR)
                    val currentMonth=currentDate.get(Calendar.MONTH)+1
                    val currentYear= currentDate.get(Calendar.YEAR)
                    var bgColor=if (displayMonth.equals(currentMonth) && displayYear.equals(currentYear)) {Color.Green} else {Color.Gray}
                    Box(modifier = Modifier
                        .padding(2.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(bgColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = dayNo.toString(), style = TextStyle(textAlign = TextAlign.Center))
                    }
                }
            }
        }
    }

    @ExperimentalFoundationApi
    @Preview
    @Composable
    fun CalenderPreview() {
        Surface(modifier = Modifier.fillMaxSize()) {
            Calender()
        }
    }
}