package com.example.cleaningapp.share

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BackstageSharedPreferencesUtils {
    private const val PREF_NAME = "AccountBackstage"
    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    data class ApiBackstageModel(
        val backstageId: Int,
        val account: String?,
        val password: String?,
        val name: String?,
        val suspend: Boolean = false,
        val token: String = ""
    ) {
//        override fun equals(other: Any?): Boolean{
//
//        }

//        override fun hashCode(): Int{
//
//        }
    }

    data class Backstage(
        val backstageId: Int,
        val account: String?,
        val password: String? = null,
        val name: String?,
        val suspend: Boolean = false,
    )

    fun saveBackstageInfoFromPreferences(any: Any): Boolean {
        return try {
            val apiBackstageModel =
                Gson().fromJson(Gson().toJson(any), ApiBackstageModel::class.java)
            sharedPreferences.edit()
                .putInt(
                    "backstageId",
                    if (apiBackstageModel.backstageId == 0) getCurrentBackstageId() else apiBackstageModel.backstageId
                )
                .putString("account", apiBackstageModel.account)
                .putString("password", apiBackstageModel.password)
                .putString("name", apiBackstageModel.name)
                .putBoolean("suspend", apiBackstageModel.suspend)
                .apply()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    inline fun <reified T> fetchBackstageInfoPreferences(): T {
        with(sharedPreferences) {
            val backstage = Backstage(
                backstageId = getInt("backstageId", 0),
                account = getString("account", ""),
                password = getString("password", ""),
                name = getString("name", ""),
                suspend = getBoolean("suspend", false)
            )
            return Gson().fromJson(Gson().toJson(backstage), object : TypeToken<T>() {}.type)
        }
    }

    fun getCurrentBackstageId(): Int {
        return sharedPreferences.getInt("backstageId", 0)
    }

    fun anyToApiBackstageModel(any: Any): ApiBackstageModel{
        val backstage = Gson().fromJson(Gson().toJson(any), Backstage::class.java)
        return ApiBackstageModel(
            backstageId = getCurrentBackstageId(),
            account = backstage.account,
            password = backstage.password,
            name = backstage.name,
            suspend = backstage.suspend
        )
    }

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}