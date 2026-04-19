package cat.montilivi.theaudiodb.data.network

import cat.montilivi.theaudiodb.model.ApiRespostaArtistas
import cat.montilivi.theaudiodb.model.ApiRespostaBuscaAlbum
import cat.montilivi.theaudiodb.model.ApiRespostaCanciones
import kotlinx.coroutines.flow.Flow

interface TheAudioDBHelper {
    fun buscarArtistaFlow(nombre: String): Flow<ApiRespostaArtistas>

    fun buscarAlbumFlow(artista: String, album: String): Flow<ApiRespostaBuscaAlbum>

    fun getCancionesAlbumFlow(idAlbum: String): Flow<ApiRespostaCanciones>
}