package com.neo.parking.feature.parking.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.neo.core.domain.model.ParkingResponse
import com.neo.parking.R
import com.neo.parking.feature.parking.vm.ParkingViewModel
import com.neo.parking.feature.parking.vm.UiIntent

@Composable
fun ParkingsScrenRoute(
    parkingViewModel: ParkingViewModel = hiltViewModel()
) {
    val state = parkingViewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        parkingViewModel.sendIntent(UiIntent.GETPARKINGSINTENT)
    }
    ParkingScreen(
        parkings = state.value.parkings
    )
}

@Composable
fun ParkingScreen(parkings: List<ParkingResponse>) {
    Scaffold(
        topBar = {
            topBar()
        }, modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 12.dp)

    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
        {
            OutlinedTextField(
                "",
                onValueChange = {},
                placeholder = { Text("Esto es un texto de prueba") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                leadingIcon = {
                    Icon(
                        painterResource(R.drawable.name_icon),
                        contentDescription = "Icon buscador parking"
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.LightGray,
                    focusedContainerColor = Color(0xFFF6F7FB),
                    unfocusedContainerColor = Color(0xFFF6F7FB),
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {

                IconButton(
                    {
                        //todo cambiar view
                    },

                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.blue_theme))
                ) {
                    Icon(
                        painterResource(R.drawable.mail_icon), contentDescription = "List_button",
                        tint = colorResource(R.color.white)
                    )
                }

                Spacer(Modifier.width(12.dp))

                IconButton(
                    {
                        //todo cambiar view
                    },
                    modifier = Modifier
                        .weight(1f)
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .background(colorResource(R.color.grey_dark))
                ) {
                    Icon(
                        painterResource(R.drawable.mail_icon), contentDescription = "List_button",
                        tint = colorResource(R.color.black)
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            ParkingBody(parkings)
        }
    }
}

@Composable
fun ParkingBody(parkings: List<ParkingResponse>) {
    if (parkings.isEmpty()) {
    } else {
        LazyColumn {
            itemsIndexed(parkings) { id, parking ->
                Card(
                    border = BorderStroke(2.dp, colorResource(R.color.grey)),
                    colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
                ) {

                    Row(
                        Modifier
                            .fillMaxWidth().padding(end = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            parking.image,
                            "ImageParking${id}",
                            placeholder = painterResource(R.drawable.ic_launcher_background),
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        )

                        Column {
                            Spacer(Modifier.weight(1f))
                            Text(
                                parking.name,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                parking.direction,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(Modifier.weight(1f))

                        }
                        Spacer(Modifier.weight(1f))

                        Icon(
                            painterResource(if (parking.available) R.drawable.outline_event_available_24 else R.drawable.baseline_cancel_24),
                            "Icon of diposibility",
                            modifier = Modifier.size(30.dp),
                            tint = Color.Unspecified
                        )

                    }
                }
            }
        }

    }

}

@Composable
fun topBar() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(colorResource(R.color.blue_theme))
        ) {
            Icon(
                painterResource(R.drawable.name_icon),
                contentDescription = "PakingIcon",
                tint = colorResource(R.color.blue_theme),
                modifier = Modifier
                    .padding(8.dp)
                    .background(colorResource(R.color.white), shape = RoundedCornerShape(2.dp))
            )
        }
        Text(
            stringResource(R.string.neopark_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 12.dp)
        )

        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(colorResource(R.color.blue_theme))
        ) {
            Icon(
                painterResource(R.drawable.name_icon),
                contentDescription = "PakingIcon",
                tint = colorResource(R.color.grey),
                modifier = Modifier
                    .padding(12.dp)
            )
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ParkingScreenPreview() {

    ParkingScreen(
        listOf(
            ParkingResponse(
                2,
                "Parking Leganes",
                "",
                "Calle de Leganes 3",
                true,
                0.0,
                0.0
            )
        )
    )
}