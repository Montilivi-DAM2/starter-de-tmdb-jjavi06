package cat.montilivi.theaudiodb.model

import com.google.gson.annotations.SerializedName

data class ApiRespostaCanciones(
    @SerializedName("track") val canciones: List<ApiCancion>?
)

data class ApiCancion(
    @SerializedName("idTrack") val id: String,
    @SerializedName("strTrack") val nombre: String,
    @SerializedName("intTrackNumber") val numero: String,
    @SerializedName("intDuration") val duracionMs: String?,
    @SerializedName("strGenre") val genero: String?
)