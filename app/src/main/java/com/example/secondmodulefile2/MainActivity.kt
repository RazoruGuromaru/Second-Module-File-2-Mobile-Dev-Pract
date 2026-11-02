package com.example.secondmodulefile2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondmodulefile2.ui.theme.SecondModuleFile2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondModuleFile2Theme {
                WaterTracker()
            }
        }
    }
}

data class Contact (
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val phone: String,
    val address: String
)

val contacts = listOf(
    Contact(
        "Евгений",
        "Андреевич",
        "Лукашин",
        "+7 495 495 95 95",
        "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
    ),
    Contact(
        "Василий",
        "Егорович",
        "Кузякин",
        "---",
        "Ивановская область, дер. Крутово, д. 4",
    ),
    Contact(
        "Людмила",
        "Прокофьевна",
        "Калугина",
        "+7 495 788 78 78",
        "Москва, Большая Никитская, д. 43, кв. 290",
    ),
)

@Composable
fun CircleTopRight() {
    Box(contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .size(240.dp, 120.dp)
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = "Оранжевый круг"
        )
    }
}

@Composable
fun CircleStretched() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(240.dp, 120.dp)
            .background(Color.Blue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.Magenta)
        )
    }
}

@Composable
fun ContactCard(contact: Contact) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text("Имя: ${contact.firstName}")
        Text("Отчество: ${contact.middleName}")
        Text("Фамилия: ${contact.lastName}")
        Text("Телефон: ${contact.phone}")
        Text("Адрес: ${contact.address}")
    }
}

@Composable
fun ContactListColumn(contacts: List<Contact>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)
    ) {
        for (contact in contacts) {
            ContactCard(contact)
        }
    }
}

@Composable
fun ContactRow(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text("Имя: ${contact.firstName}")
            Text("Отчество: ${contact.middleName}")
            Text("Фамилия: ${contact.lastName}")
            Text("Телефон: ${contact.phone}")
            Text("Адрес: ${contact.address}")
        }
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun WaterTracker() {
    var waterCount by remember { mutableStateOf(100) }
    var streakDays by remember { mutableStateOf(0) }

    val primaryColor = Color(0xFF3F51B5)
    val secondaryColor = Color.Gray
    val buttonTextColor = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            colors = ButtonDefaults.buttonColors(primaryColor),
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Button(
            colors = ButtonDefaults.buttonColors(primaryColor),
            onClick = {
                if (waterCount >= 1500) streakDays++
                else streakDays = 0; waterCount = 0
                waterCount = 0
            }
        ) {
            Text("Завершить день")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$streakDays дней подряд",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )
    }
}

// ---------------
// --- PREVIEW ---
// ---------------

@Preview(showBackground = true)
@Composable
fun CircleTopRightPreview() {
    CircleTopRight()
}

@Preview(showBackground = true)
@Composable
fun CircleStretchedPreview() {
    CircleStretched()
}

@Preview(showSystemUi = true)
@Composable
fun ContactListColumnPreview() {
    ContactListColumn(contacts)
}

@Preview(showSystemUi = true)
@Composable
fun ContactRowPreview() {
    Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 32.dp)) {
        ContactRow(contacts[0])
    }
}

@Preview(showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}
