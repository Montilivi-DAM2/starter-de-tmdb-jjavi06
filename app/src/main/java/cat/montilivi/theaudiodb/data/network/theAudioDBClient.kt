package cat.montilivi.theaudiodb.data.network

import kotlin.jvm.java


object theAudioDBClient {
    const val API_KEY = "123"
    const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/$API_KEY/"

    private val gsonConverter = com.google.gson.GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create(gsonConverter))
        .build()

    val servei: TheAudioDbService = retrofit.create(TheAudioDbService::class.java)
}