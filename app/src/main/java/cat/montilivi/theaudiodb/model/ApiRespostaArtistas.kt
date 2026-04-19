package cat.montilivi.theaudiodb.model

import com.google.gson.annotations.SerializedName

data class ApiRespostaArtistas(
    @SerializedName("artists") val artistas: List<ApiArtista>?
)

data class ApiArtista(
    @SerializedName("idArtist") val id: String,
    @SerializedName("strArtist") val nombre: String,
    @SerializedName("strGenre") val genero: String?,
    @SerializedName("strBiographyES") val biografia: String?,
    @SerializedName("strArtistThumb") val foto: String?
)