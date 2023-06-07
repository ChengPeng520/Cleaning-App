package com.example.cleaningapp.share

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64

object CleanerPreferencesUtils {
    private const val PREF_NAME = "CleanerPhoto"
    lateinit var preferences: SharedPreferences

    fun saveCleaningPhotoFromPreferences(context: Context, photos: List<Bitmap?>) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        photos[0]?.let {
            preferences.edit().putString(
                "cleaningPhoto1",
                Base64.encodeToString(ImageUtils.bitmapToBytes(it), Base64.DEFAULT)
            ).apply()
        }
        photos[1]?.let {
            preferences.edit().putString(
                "cleaningPhoto2",
                Base64.encodeToString(ImageUtils.bitmapToBytes(it), Base64.DEFAULT)
            ).apply()
        }
        photos[2]?.let {
            preferences.edit().putString(
                "cleaningPhoto2",
                Base64.encodeToString(ImageUtils.bitmapToBytes(it), Base64.DEFAULT)
            ).apply()
        }
    }

    fun fetchCleaningPhotoFromPreferences(context: Context): List<Bitmap?> {
        val cleaningPhoto: MutableList<Bitmap?> = mutableListOf()
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        cleaningPhoto.add(preferences.getString("cleaningPhoto1", null)?.let {
            ImageUtils.bytesToBitmap(
                Base64.decode(
                    it,
                    Base64.DEFAULT
                )
            )
        })
        cleaningPhoto.add(preferences.getString("cleaningPhoto2", null)?.let {
            ImageUtils.bytesToBitmap(
                Base64.decode(
                    it,
                    Base64.DEFAULT
                )
            )
        })
        cleaningPhoto.add(preferences.getString("cleaningPhoto3", null)?.let {
            ImageUtils.bytesToBitmap(
                Base64.decode(
                    it,
                    Base64.DEFAULT
                )
            )
        })
        return cleaningPhoto
    }

    fun bitmapPhotosToByteArray(photos: List<Bitmap?>): List<ByteArray?> {
        val bitmapPhotos: MutableList<ByteArray?> = MutableList(3) { null }
        photos[0]?.let {
            bitmapPhotos[0] = ImageUtils.bitmapToBytes(it)
        }
        photos[1]?.let {
            bitmapPhotos[1] = ImageUtils.bitmapToBytes(it)
        }
        photos[2]?.let {
            bitmapPhotos[2] = ImageUtils.bitmapToBytes(it)
        }
        return bitmapPhotos
    }

    fun cleanPreferences(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }
}