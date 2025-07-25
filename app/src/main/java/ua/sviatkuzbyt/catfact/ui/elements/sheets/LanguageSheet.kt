package ua.sviatkuzbyt.catfact.ui.elements.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.ui.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSheet(
    languages: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    onClose: () -> Unit
){
    ModalBottomSheet (
        onDismissRequest = onClose,
        containerColor = Theme.colors.background,
        dragHandle = {
            SheetDrag()
        },
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        ) {
            stickyHeader {
                SheetTitle(R.string.facts_language)
            }

            itemsIndexed(languages){ index, language ->

                RadioItem(
                    text = language,
                    isSelected = index == selectedIndex,
                    onClick = { onSelect(index) }
                )
            }
        }
    }
}

@Composable
fun RadioItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(
                onClick = onClick,
                role = Role.RadioButton,
                indication = null,
                interactionSource = null
            )
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Theme.colors.primary,
                unselectedColor = Theme.colors.primary
            )
        )

        Spacer(Modifier.size(16.dp))

        Text(
            text = text,
            style = Theme.types.basic
        )
    }
}