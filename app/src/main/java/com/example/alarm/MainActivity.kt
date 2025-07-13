package com.example.alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.LockClock
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alarm.ui.theme.AlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmTheme {
                MainScreen()
            }
        }
    }
}
data class BottomNavItem(val label: String,val icon: ImageVector)

@Composable
fun MainScreen(){
    val bottomNavItems = listOf(
        BottomNavItem("Alarm",Icons.Default.Alarm),
        BottomNavItem("Clock",Icons.Default.Watch),
        BottomNavItem("Timer",Icons.Default.Timer),
        BottomNavItem("World Clock",Icons.Default.LockClock)
    )
    var selectedItemIndex by remember { mutableStateOf(0)}
    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEachIndexed{index, item ->
                    NavigationBarItem(
                        icon = {Icon(item.icon, contentDescription = item.label)},
                        label = { Text(item.label) },
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index}
                    )
                }
            }
        }
    ) {
        innerPadding ->
        // Main Content
       Box(modifier = Modifier
           .padding(innerPadding)
           .fillMaxSize()){
           when(selectedItemIndex){
               0 -> AlarmScreen()
               1 -> WorldClockScreen()
               2 -> TimerScreen()
               3 -> StopWatchScreen()
           }
       }
    }
}
@Composable
fun AlarmScreen(){
    val alarms = remember{
        listOf(
            "Wake up - 6:30 AM",
            "Wake up - 6:30 AM",
            "Wake up - 6:30 AM",
            "Lunch - 6:30 AM"
        )
    }
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        items(alarms) {
            alarm ->
            Card (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
                ){
                Text(
                    text = alarm, modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }


}
@Composable
fun WorldClockScreen(){
    Text(text = "WORLDCLOCK", modifier = Modifier.padding(16.dp))
}
@Composable
fun TimerScreen(){
    Text(text = "TIMER", modifier = Modifier.padding(16.dp))
}
@Composable
fun StopWatchScreen(){
    Text(text = "STOPWATCH", modifier = Modifier.padding(16.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen(){
    AlarmTheme {
        MainScreen()
    }
}
