package com.rashan.photoapplication.utility

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File

object DownloadUtility {
    private const val DIR_NAME = "picsum"
    fun downloadImage(context: Context, downloadUrlOfImage: String) {
        val filename = System.nanoTime().toString() + "_image.jpg"
        val direct = File(
            Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .absolutePath + "/" + DIR_NAME + "/"
        )
        if (!direct.exists()) {
            direct.mkdir()
        }
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadUri = Uri.parse(downloadUrlOfImage)
        val request = DownloadManager.Request(downloadUri)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setAllowedOverRoaming(false)
            .setTitle(filename)
            .setMimeType("image/jpeg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_PICTURES,
                File.separator + DIR_NAME + File.separator + filename
            )
        downloadManager.enqueue(request)
    }
}