package com.example.cleaningapp.backstage.shop.controller

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.net.URI

class ShowPictureDialog(private val activity: Activity, private val imageView: ImageView) {
    private val REQUEST_CAMERA_PERMISSION = 1001
    private val REQUEST_CAMERA = 1002
    private val REQUEST_GALLERY = 1003
    fun showPictureDialog() {
        val option = arrayOf("開啟相機", "相簿選取")
        val build = AlertDialog.Builder(activity)
        build.setTitle("選擇照片來源")
        build.setItems(option) { _, which ->
            when (which) {
                0 -> checkPermission()
                1 -> openGallery()
            }
        }
        build.create().show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        activity.startActivityForResult(intent, REQUEST_CAMERA)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(intent, REQUEST_GALLERY)
    }

    private fun checkPermission() {
        // 檢查權限並請求相機權限
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // 如果尚未授予相機權限，則請求權限
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            openCamera()
        }
    }

    fun handleCameraResult(resultCode: Int, requestCode: Int, data: Intent?): Bitmap? {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CAMERA) {
            val image = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(image)
            return image
        }
        return null
    }
}