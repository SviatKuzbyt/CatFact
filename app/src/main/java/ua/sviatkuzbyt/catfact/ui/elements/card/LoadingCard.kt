package ua.sviatkuzbyt.catfact.ui.elements.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun LoadingCard(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.width(48.dp),
            color = Theme.colors.primary,
            trackColor = Theme.colors.surface,
            strokeWidth = 3.dp
        )
    }
}