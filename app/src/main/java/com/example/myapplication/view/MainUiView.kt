package com.example.myapplication.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.components.UiComponents
import com.example.myapplication.room.EventDatabase
import com.example.myapplication.room.EventModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.*
import java.util.*

class MainUiView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainUi()
            }
        }
    }
}

suspend fun submitClick(context: Context, title: String, desc: String, date: String) {
    val eventModel: EventModel
    eventModel = EventModel(0, title, desc, date)
    CoroutineScope(Dispatchers.IO).launch {
        EventDatabase.getInstance(context).eventDao().insertData(eventModel)
//        Toast.makeText(context,"Save Data",Toast.LENGTH_SHORT).show()
    }
}


@Composable
fun MainUi() {
    val context = LocalContext.current
    val headerText by remember { mutableStateOf("Create Event") }
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
   // val date by remember { mutableStateOf("Date") }

    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("Date") }
    val mDatePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            }, mYear, mMonth, mDay
        )

    Column(modifier = Modifier.fillMaxSize()) {
        UiComponents.HeaderText(text = headerText)
        UiComponents.TitleText(text = title, onValueChange = { title = it }, label = "Title")
        UiComponents.TitleText(
            text = desc,
            onValueChange = { desc = it },
            label = "Description"
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            UiComponents.NormalText(text = mDate.value, onTextClick = {

            })
            UiComponents.IconClick(onIconClick = {mDatePickerDialog.show()})
        }

        UiComponents.ButtonClick(
            text = "Submit",
            onButtonClick = {
                CoroutineScope(Dispatchers.IO).launch { submitClick(context, title, desc, mDate.value) }
            })
    }
}

@Preview
@Composable
fun MainUiPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        MainUi()
    }
}
        
