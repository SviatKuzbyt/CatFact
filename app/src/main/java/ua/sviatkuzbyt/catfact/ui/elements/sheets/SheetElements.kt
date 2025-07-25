package ua.sviatkuzbyt.catfact.ui.elements.sheets

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun SheetDrag(){
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.drag_ic),
        contentDescription = stringResource(R.string.drag),
        tint = Theme.colors.drag,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
fun SheetTitle(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(textRes),
        style = Theme.types.bigBold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(color = Theme.colors.background)
    )
}