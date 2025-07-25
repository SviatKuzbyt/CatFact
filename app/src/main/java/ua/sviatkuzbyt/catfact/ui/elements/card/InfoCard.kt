package ua.sviatkuzbyt.catfact.ui.elements.card

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.data.structures.Fact
import ua.sviatkuzbyt.catfact.ui.Theme
import ua.sviatkuzbyt.catfact.ui.elements.buttons.ButtonIcon
import java.io.File
import java.io.FileOutputStream

@Composable
fun InfoCard(
    fact: Fact
){
    val context = LocalContext.current
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(
                color = Theme.colors.surface,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Image
        Image(
            imageUrl = fact.imageUrl,
            context = context,
            onBitmap = { imageBitmap = it }
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
            onClick = {
                shareFact(context, imageBitmap, fact.text)
            }
        )
    }
}

@Composable
private fun Image(
    imageUrl: String,
    context: Context,
    onBitmap: (Bitmap) -> Unit
){
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .diskCachePolicy(CachePolicy.DISABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        onSuccess = {
            onBitmap(it.result.image.toBitmap())
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Theme.colors.background,
                shape = RoundedCornerShape(8.dp)
            )
            .paint(
                painterResource(id = R.drawable.img_plug),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Theme.colors.primary)
            )
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f)
    )
}

private fun shareFact(
    context: Context,
    bitmap: Bitmap?,
    caption: String
){
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val imageUri = bitmap?.let {
                val cachePath = File(context.cacheDir, "shared_images")
                cachePath.mkdirs()

                val file = File(cachePath, "shared_image.png")
                FileOutputStream(file).use { stream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                }

                FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    file
                )
            }

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                if (imageUri != null) {
                    type = "image/png"
                    putExtra(Intent.EXTRA_STREAM, imageUri)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                } else {
                    type = "text/plain"
                }
                putExtra(Intent.EXTRA_TEXT, caption)
            }

            withContext(Dispatchers.Main) {
                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        context.getString(R.string.share)
                    )
                )
            }
        } catch (_: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    R.string.cant_share,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}