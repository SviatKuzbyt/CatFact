package ua.sviatkuzbyt.catfact.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun ButtonNext(
    isLoading: Boolean,
    onClick: () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Theme.colors.primary,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = null,
                enabled = !isLoading,
                indication = ripple(
                    color = Theme.colors.background
                )
            )
    ){
        val text = if (isLoading) {
            R.string.loading
        } else {
            R.string.next
        }
        Text(
            text = stringResource(text),
            style = Theme.types.white
        )
    }
}