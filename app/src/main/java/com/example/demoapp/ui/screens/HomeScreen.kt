package com.example.demoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAEBD7)),
        contentAlignment = Alignment.Center
    ) {
        MyList()
    }
}

@Composable
fun MyList() {
    val items = listOf("Facebook", "Instagram", "Steam")

    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        itemsIndexed(items) { index, item ->
            RowItem(index = index, item = item)
        }
    }
}


@Composable
fun RowItem(index: Int, item: String) {
    Row(
        modifier = Modifier
            .background(
                // Alternate background colors: #FF23CD for even indices, light blue for odd
                color = if (index % 2 == 0) Color(0xFFFF23CD) else Color(0xFFADD8E6)
            )
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp), // Padding inside the row
        horizontalArrangement = Arrangement.SpaceBetween, // Space items evenly
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Item $index",
            fontSize = 18.sp,
            color = Color.White, // White text for contrast
            modifier = Modifier.weight(1f) // Take available space
        )
        Text(
            text = item,
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}