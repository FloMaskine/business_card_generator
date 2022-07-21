package com.flomaskine.businesscardgenerator.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.flomaskine.businesscardgenerator.R
import java.io.File
import java.io.OutputStream

open class Image {
    companion object{
        fun share(context: Context, view: View) {
            val bitmap = view.drawToBitmap()
            saveMediaToGallery(context, bitmap)
        }

        private fun saveMediaToGallery(context: Context, bitmap: Bitmap) {
            val fileName = "image_${System.currentTimeMillis()}.png"

            var fos: OutputStream? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->

                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    val imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    fos = imageUri?.let {
                        shareIntent(context, imageUri)
                        resolver.openOutputStream(it)
                    } ?: return

                }
            } else {
                val imageDir = Environment.getExternalStorageDirectory().toString() + "/Pictures/"
                val image = File(imageDir, fileName)
                shareIntent(context, Uri.fromFile(image))
                fos = image.outputStream()

            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                Toast.makeText(
                    context,
                    context.getString(R.string.success_captured_image),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        private fun shareIntent(context: Context, imageUri: Uri) {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUri)
                type = "image/png"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.getString(R.string.share_image_label)
                )
            )
        }
    }
}