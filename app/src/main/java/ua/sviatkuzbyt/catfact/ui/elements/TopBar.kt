package ua.sviatkuzbyt.catfact.ui.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun TopBar(
    onLanguageClick: () -> Unit,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 8.dp)
    ) {
        //Logo
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.logo_ic),
            contentDescription = stringResource(R.string.logo),
            tint = Theme.colors.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.size(16.dp))

        Text(
            text = stringResource(R.string.app_name),
            style = Theme.types.bigBold,
            modifier = Modifier.weight(1f)
        )

        //Buttons
        ButtonIcon(
            icon = R.drawable.lang_ic,
            description = R.string.facts_language,
            onClick = onLanguageClick
        )
    }
}