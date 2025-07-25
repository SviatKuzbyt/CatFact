package ua.sviatkuzbyt.catfact.ui.elements.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.data.structures.ErrorTexts
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun ErrorCard(errorTexts: ErrorTexts){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.info_ic),
            contentDescription = null,
            tint = Theme.colors.primary,
            modifier = Modifier.size(48.dp)
        )

        Spacer(Modifier.size(32.dp))

        Text(
            text = "${stringResource(errorTexts.messageRes)} ${errorTexts.message ?: ""}",
            style = Theme.types.basic,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}