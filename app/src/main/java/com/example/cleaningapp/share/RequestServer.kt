package com.example.cleaningapp.share

import com.google.gson.*
import kotlinx.coroutines.*
import java.lang.reflect.Type
import java.net.CookieHandler
import java.net.CookieManager
import java.net.HttpURLConnection
import java.net.URL
import java.sql.Time
import java.text.SimpleDateFormat

inline fun <reified T> requestTask(
    url: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? = runBlocking {
    val deferred: Deferred<T?> = coroutineScope {
        async(Dispatchers.IO) {
            if (CookieHandler.getDefault() == null) {
                CookieHandler.setDefault(CookieManager())
            }
            request<T>(url, method, reqBody, respBodyType)
        }
    }
    deferred.await()

}

inline fun <reified T> request(
    url: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? {
    var conn: HttpURLConnection? = null
    var result: T? = null
    try {
        conn = URL(url).openConnection() as HttpURLConnection
        with(conn) {
            requestMethod = method
            setRequestProperty("Content-Type", "application/json; charset=utf-8")
            useCaches = false
            doOutput = reqBody != null
            reqBody?.run {
                outputStream.use {
                    val writer = outputStream.writer()
                    writer.write(GSON.toJson(reqBody))
                    writer.flush()
                }
            }
            println("狀態碼: $responseCode")
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream.use {
                    result = GSON.fromJson<T>(inputStream.reader(), respBodyType)
                }
            }
        }
    } finally {
        conn?.disconnect()
    }
    return result
}

val GSON: Gson = GsonBuilder()
    .registerTypeAdapter(Time::class.java, TimeAdapter())
    .setDateFormat("yyyy/MM/dd HH:mm:ss")
    .create()

class TimeAdapter : JsonSerializer<Time>, JsonDeserializer<Time> {
    private val SDF: SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
    override fun serialize(
        src: Time,
        typeOfSrc: Type?,
        context: JsonSerializationContext?,
    ): JsonElement {
        return JsonPrimitive(SDF.format(src))
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): Time? {
        return try {
            val date = SDF.parse(json.asString)
            Time(date?.time ?: 0)
        } catch (e: Exception) {
            null
        }
    }
}