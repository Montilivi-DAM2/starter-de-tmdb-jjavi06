package cat.montilivi.theaudiodb.model

import com.google.gson.annotations.SerializedName

data class ApiRespostaBuscaAlbum(
    @SerializedName("album") val albums: List<ApiAlbum>?
)

data class ApiAlbum(
    @SerializedName("idAlbum") val id: String,
    @SerializedName("strAlbum") val titulo: String,
    @SerializedName("strArtist") val artista: String,
    @SerializedName("strAlbumThumb") val portada: String?,
    @SerializedName("intYearReleased") val anyo: String?
)