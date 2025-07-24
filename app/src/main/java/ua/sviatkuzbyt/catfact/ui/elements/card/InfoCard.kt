package ua.sviatkuzbyt.catfact.ui.elements.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.data.structures.Fact
import ua.sviatkuzbyt.catfact.ui.Theme
import ua.sviatkuzbyt.catfact.ui.elements.buttons.ButtonIcon

@Composable
fun ColumnScope.InfoCard(
    fact: Fact
){
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .weight(1f)
            .background(
                color = Theme.colors.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Image
        AsyncImage(
            model = fact.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Theme.colors.background,
                    shape = RoundedCornerShape(8.dp)
                )
                .clip(RoundedCornerShape(8.dp))
                .aspectRatio(1f)
        )
        Spacer(Modifier.size(16.dp))

        // Text
        Text(
            text = fact.text,
            style = Theme.types.big,
        )

        Spacer(Modifier.weight(1f))

        // Share Button
        ButtonIcon(
            modifier = Modifier.align(Alignment.End),
            icon = R.drawable.share_ic,
            description = R.string.share,
            onClick = {}
        )
    }
}