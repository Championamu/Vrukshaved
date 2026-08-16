package com.vrukshaved.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VrukshavedApp()
        }
    }
}

data class Plant(
    val name: String,
    val type: String,
    val health: String,
    val water: String
)

@Composable
fun VrukshavedApp() {
    var selectedTab by remember { mutableStateOf(0) }

    val plants = remember {
        mutableStateListOf(
            Plant("Tulsi", "Medicinal", "Healthy", "Today"),
            Plant("Money Plant", "Indoor", "Healthy", "Tomorrow"),
            Plant("Rose", "Flowering", "Needs Care", "Today")
        )
    }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.LocalFlorist, null) },
                    label = { Text("Plants") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.MedicalServices, null) },
                    label = { Text("Doctor") }
                )

                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Settings") }
                )
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> HomeScreen(padding)
            1 -> PlantsScreen(padding, plants)
            2 -> DoctorScreen(padding)
            3 -> SettingsScreen(padding)
        }
    }
}

@Composable
fun HomeScreen(padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F1))
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Vrukshaved 🌱",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF245C38)
            )

            Text(
                text = "Your smart plant-care companion",
                color = Color(0xFF58705F),
                fontSize = 15.sp
            )
        }

        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFDDEFD8)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        "Good afternoon! 🌿",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        "You have 3 plants and 2 care tasks today."
                    )

                    Spacer(Modifier.height(14.dp))

                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Check today's care")
                    }
                }
            }
        }

        item {
            Text(
                "Today's care",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            CareCard(
                icon = "💧",
                title = "Water Tulsi",
                subtitle = "Due today"
            )
        }

        item {
            CareCard(
                icon = "🌹",
                title = "Check Rose",
                subtitle = "Needs attention"
            )
        }

        item {
            Text(
                "Quick actions",
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickAction("🌿", "My Plants", Modifier.weight(1f))
                QuickAction("🩺", "Plant Doctor", Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CareCard(
    icon: String,
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon, fontSize = 30.sp)

            Spacer(Modifier.size(14.dp))

            Column {
                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                Text(
                    subtitle,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun QuickAction(
    icon: String,
    title: String,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(icon, fontSize = 30.sp)
            Spacer(Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun PlantsScreen(
    padding: PaddingValues,
    plants: List<Plant>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F1))
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "My Plants",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Default.Add, null)
                    Spacer(Modifier.size(4.dp))
                    Text("Add")
                }
            }
        }

        items(plants) { plant ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp)
                ) {
                    Text(
                        plant.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(plant.type)

                    Spacer(Modifier.height(8.dp))

                    Text("Health: ${plant.health}")
                    Text("Watering: ${plant.water}")
                }
            }
        }
    }
}

@Composable
fun DoctorScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F1))
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Plant Doctor 🩺",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            "What problem are you seeing?"
        )

        listOf(
            "Yellow leaves",
            "Brown leaf tips",
            "Wilting",
            "White spots",
            "Insects",
            "Slow growth"
        ).forEach { symptom ->
            OutlinedButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(symptom)
            }
        }
    }
}

@Composable
fun SettingsScreen(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8F1))
            .padding(padding)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Settings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    "Vrukshaved",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(Modifier.height(5.dp))

                Text("Plant care made simple.")
                Text("Version 1.0")
            }
        }
    }
}
