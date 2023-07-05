package com.example.cleaningapp.share

import com.google.gson.*
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.sql.Time
import java.text.SimpleDateFormat
import kotlin.system.measureTimeMillis

inline fun <reified T> requestTask(
    path: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? = runBlocking {
    var result: T? = null
    val elapsedTime = measureTimeMillis {
        val deferred: Deferred<T?> = async(Dispatchers.IO) {
            request(path, method, reqBody, respBodyType)
        }
        deferred.join()
        result = deferred.await()
    }
    println("执行时间: $elapsedTime 毫秒")
    result
//    val deferred: Deferred<T?> = coroutineScope {
//        async(Dispatchers.IO) {
//            if (CookieHandler.getDefault() == null) {
//                CookieHandler.setDefault(CookieManager())
//            }
//            request<T>(url, method, reqBody, respBodyType)
//        }
//    }
//    deferred.await()
}

suspend inline fun <reified T> request(
    path: String,
    method: String = "GET",
    reqBody: Any? = null,
    respBodyType: Type = T::class.java,
): T? = coroutineScope {
    val client = OkHttp.getClient()
    val request = Request.Builder()
        .url("${Constants.BASE_URL}$path")
        .apply {
            reqBody?.let {
                val requestBody =
                    RequestBody.create(
                        MediaType.parse("application/json"),
                        GSON.toJson(reqBody)
                    )
                method(method, requestBody)
            } ?: run {
                method(method, null)
            }
        }
        .build()
    val response = client.newCall(request).execute()
    response.body()?.byteStream().use { inputStream ->
        val reader = InputStreamReader(inputStream)
        GSON.fromJson(reader, respBodyType)
    }
//    var conn: HttpURLConnection? = null
//    var result: T? = null
//    try {
//        conn = URL(url).openConnection() as HttpURLConnection
//        with(conn) {
//            requestMethod = method
//            setRequestProperty("Content-Type", "application/json; charset=utf-8")
//            useCaches = false
//            doOutput = reqBody != null
//            reqBody?.run {
//                outputStream.use {
//                    val writer = outputStream.writer()
//                    writer.write(GSON.toJson(reqBody))
//                    writer.flush()
//                }
//            }
//            println("狀態碼: $responseCode")
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                inputStream.use {
//                    result = GSON.fromJson<T>(inputStream.reader(), respBodyType)
//                }
//            }
//        }
//    } finally {
//        conn?.disconnect()
//    }
//    return result
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