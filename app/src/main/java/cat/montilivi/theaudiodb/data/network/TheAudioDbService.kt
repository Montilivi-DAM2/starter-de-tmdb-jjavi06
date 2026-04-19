package cat.montilivi.theaudiodb.data.network


import cat.montilivi.theaudiodb.model.ApiRespostaArtistas
import cat.montilivi.theaudiodb.model.ApiRespostaBuscaAlbum
import cat.montilivi.theaudiodb.model.ApiRespostaCanciones
import retrofit2.http.GET
import retrofit2.http.Query

interface TheAudioDbService {

    @GET("searchalbum.php")
    suspend fun buscarAlbum(
        @Query("s") artista: String,
        @Query("a") album: String
    ): ApiRespostaBuscaAlbum

    @GET("track.php")
    suspend fun getCancionesAlbum(
        @Query("m") idAlbum: String
    ): ApiRespostaCanciones

    @GET("search.php")
    suspend fun buscarArtista(
        @Query("s") nombre: String
    ): ApiRespostaArtistas
}