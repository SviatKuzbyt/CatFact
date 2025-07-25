package ua.sviatkuzbyt.catfact.ui.other

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.sviatkuzbyt.catfact.R
import java.io.File
import java.io.FileOutputStream

fun shareFact(
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