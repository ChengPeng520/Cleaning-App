package com.example.cleaningapp.share

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CleanerSharedPreferencesUtils {
    private const val PREF_NAME = "AccountCleaner"
    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    data class ApiCleanerModel(
        val cleanerId: Int,
        val email: String?,
        val password: String?,
        val name: String?,
        val photo: ByteArray?,
        val phone: String?,
        val gender: Int,
        val introduction: String?,
        val identifyNumber: String?,
        val idCardFront: ByteArray?,
        val idCardBack: ByteArray?,
        val crc: ByteArray?,
        val certification: String? = null,
        val suspend: Boolean = false,
        val verify: Boolean = false,
        val token: String = ""
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ApiCleanerModel

            if (photo != null) {
                if (other.photo == null) return false
                if (!photo.contentEquals(other.photo)) return false
            } else if (other.photo != null) return false
            if (idCardFront != null) {
                if (other.idCardFront == null) return false
                if (!idCardFront.contentEquals(other.idCardFront)) return false
            } else if (other.idCardFront != null) return false
            if (idCardBack != null) {
                if (other.idCardBack == null) return false
                if (!idCardBack.contentEquals(other.idCardBack)) return false
            } else if (other.idCardBack != null) return false

            return true
        }

        override fun hashCode(): Int {
            var result = photo?.contentHashCode() ?: 0
            result = 31 * result + (idCardFront?.contentHashCode() ?: 0)
            result = 31 * result + (idCardBack?.contentHashCode() ?: 0)
            return result
        }
    }

    data class Cleaner(
        val cleanerId: Int,
        val email: String?,
        val password: String? = null,
        val name: String?,
        val photo: Bitmap?,
        val phone: String?,
        val gender: Int,
        val introduction: String?,
        val identifyNumber: String?,
        val idCardFront: Bitmap?,
        val idCardBack: Bitmap?,
        val crc: Bitmap?
    )

    fun saveCleanerInfoFromPreferences(any: Any): Boolean {
        return try {
            val apiCleanerModel = Gson().fromJson(Gson().toJson(any), ApiCleanerModel::class.java)
            sharedPreferences.edit()
                .putInt("cleanerId", getCurrentCleanerId())
                .putString("email", apiCleanerModel.email)
                .putString("name", apiCleanerModel.name)
                .putString(
                    "photo",
                    apiCleanerModel.photo?.let {
                        Base64.encodeToString(
                            apiCleanerModel.photo,
                            Base64.DEFAULT
                        )
                    })
                .putInt("gender", apiCleanerModel.gender)
                .putString("phone", apiCleanerModel.phone)
                .putString("introduction", apiCleanerModel.introduction)
                .putString("identifyNumber", apiCleanerModel.identifyNumber)
                .putString(
                    "idCardFront",
                    apiCleanerModel.idCardFront?.let {
                        Base64.encodeToString(
                            apiCleanerModel.idCardFront,
                            Base64.DEFAULT
                        )
                    })
                .putString(
                    "idCardBack",
                    apiCleanerModel.idCardBack?.let {
                        Base64.encodeToString(
                            apiCleanerModel.idCardBack,
                            Base64.DEFAULT
                        )
                    })
                .putString(
                    "crc",
                    apiCleanerModel.crc?.let {
                        Base64.encodeToString(
                            apiCleanerModel.crc,
                            Base64.DEFAULT
                        )
                    })
                .apply()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    inline fun <reified T> fetchCleanerInfoFromPreferences(): T {
        with(sharedPreferences) {
            val cleaner =
                Cleaner(
                    cleanerId = getInt("cleanerId", 0),
                    email = getString("email", ""),
                    name = getString("name", ""),
                    photo = getString("photo", null)?.let {
                        ImageUtils.bytesToBitmap(
                            Base64.decode(
                                it,
                                Base64.DEFAULT
                            )
                        )
                    },
                    phone = getString("phone", ""),
                    gender = getInt("gender", 0),
                    introduction = getString("introduction", ""),
                    identifyNumber = getString("identifyNumber", ""),
                    idCardFront = getString("idCardFront", null)?.let {
                        ImageUtils.bytesToBitmap(
                            Base64.decode(
                                it,
                                Base64.DEFAULT
                            )
                        )
                    },
                    idCardBack = getString("idCardBack", null)?.let {
                        ImageUtils.bytesToBitmap(
                            Base64.decode(
                                it,
                                Base64.DEFAULT
                            )
                        )
                    },
                    crc = getString("crc", null)?.let {
                        ImageUtils.bytesToBitmap(
                            Base64.decode(it, Base64.DEFAULT)
                        )
                    })
            return Gson().fromJson(Gson().toJson(cleaner), object : TypeToken<T>() {}.type)
        }
    }

    fun getCurrentCleanerId(): Int {
        return sharedPreferences.getInt("cleanerId", 0)
    }

    fun getCurrentCleanerPhoto(): Bitmap? {
        return sharedPreferences.getString("photo", null)
            ?.let { ImageUtils.bytesToBitmap(Base64.decode(it, Base64.DEFAULT)) }
    }

    fun anyToApiCleanerModel(any: Any): ApiCleanerModel {
        val cleaner = Gson().fromJson(Gson().toJson(any), Cleaner::class.java)
        return ApiCleanerModel(
            cleanerId = getCurrentCleanerId(),
            email = cleaner.email,
            password = cleaner.password,
            name = cleaner.name,
            photo = cleaner.photo?.let { ImageUtils.bitmapToBytes(it) },
            phone = cleaner.phone,
            gender = cleaner.gender,
            introduction = cleaner.introduction,
            identifyNumber = cleaner.identifyNumber,
            idCardFront = cleaner.idCardFront?.let { ImageUtils.bitmapToBytes(it) },
            idCardBack = cleaner.idCardBack?.let { ImageUtils.bitmapToBytes(it) },
            crc = cleaner.crc?.let { ImageUtils.bitmapToBytes(it) }
        )
    }
}