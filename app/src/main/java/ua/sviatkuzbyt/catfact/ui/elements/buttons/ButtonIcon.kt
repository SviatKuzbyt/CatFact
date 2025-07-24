package ua.sviatkuzbyt.catfact.ui.elements.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun ButtonIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes description: Int,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = stringResource(description),
        tint = Theme.colors.primary,

        modifier = modifier
            .size(48.dp)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = null,
                indication = ripple(
                    color = Theme.colors.primary,
                    radius = 24.dp
                )
            )
    )
}