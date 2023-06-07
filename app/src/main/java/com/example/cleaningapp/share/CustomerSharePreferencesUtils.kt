package com.example.cleaningapp.share

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CustomerSharePreferencesUtils {
    private const val PREF_NAME = "AccountCustomer"
    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    data class ApiCustomerModel(
        val customerId: Int,
        val email: String?,
        val password: String?,
        val name: String?,
        val photo: ByteArray?,
        val phone: String?,
        val gender: Int,
        val introduction: String?,
        val certification: String? = null,
        val suspend: Boolean = false,
        val verify: Boolean = false,
        val token: String = ""
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ApiCustomerModel

            if (photo != null) {
                if (other.photo == null) return false
                if (!photo.contentEquals(other.photo)) return false
            } else if (other.photo != null) return false

            return true
        }

        override fun hashCode(): Int {
            return photo?.contentHashCode() ?: 0
        }
    }

    data class Customer(
        val customerId: Int,
        val email: String?,
        val password: String? = null,
        val name: String?,
        val photo: Bitmap?,
        val phone: String?,
        val gender: Int,
        val introduction: String?
    )

    fun saveCustomerInfoFromPreferences(any: Any): Boolean {
        return try {
            val apiCustomerModel = Gson().fromJson(Gson().toJson(any), ApiCustomerModel::class.java)
            sharedPreferences.edit()
                .putInt("customerId", apiCustomerModel.customerId)
                .putString("email", apiCustomerModel.email)
                .putString("name", apiCustomerModel.name)
                .putString(
                    "photo",
                    apiCustomerModel.photo?.let {
                        Base64.encodeToString(
                            apiCustomerModel.photo,
                            Base64.DEFAULT
                        )
                    })
                .putInt("gender", apiCustomerModel.gender)
                .putString("phone", apiCustomerModel.phone)
                .putString("introduction", apiCustomerModel.introduction)
                .apply()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    inline fun <reified T> fetchCustomerInfoFromPreferences(): T {
        with(sharedPreferences) {
            val customer =
                Customer(
                    customerId = getInt("customerId", 0),
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
                    introduction = getString("introduction", "")
                )
            return Gson().fromJson(Gson().toJson(customer), object : TypeToken<T>() {}.type)
        }
    }

    fun getCurrentCustomerId(): Int {
        return sharedPreferences.getInt("customerId", 0)
    }

    fun getCurrentCustomerPhoto(): Bitmap? {
        return sharedPreferences.getString("photo", null)
            ?.let { ImageUtils.bytesToBitmap(Base64.decode(it, Base64.DEFAULT)) }
    }

    fun anyToApiCustomerModel(any: Any): ApiCustomerModel {
        val customer =
            Gson().fromJson(Gson().toJson(any), Customer::class.java)
        return ApiCustomerModel(
            customerId = getCurrentCustomerId(),
            email = customer.email,
            password = customer.password,
            name = customer.name,
            photo = customer.photo?.let { ImageUtils.bitmapToBytes(it) },
            phone = customer.phone,
            gender = customer.gender,
            introduction = customer.introduction,

        )
    }
}