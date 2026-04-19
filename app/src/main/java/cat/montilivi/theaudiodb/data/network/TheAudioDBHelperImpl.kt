package cat.montilivi.theaudiodb.data.network

import cat.montilivi.theaudiodb.model.ApiRespostaArtistas
import cat.montilivi.theaudiodb.model.ApiRespostaBuscaAlbum
import cat.montilivi.theaudiodb.model.ApiRespostaCanciones
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TheAudioDBHelperImpl(val apiService: TheAudioDbService) : TheAudioDBHelper {

    override fun buscarArtistaFlow(nombre: String): Flow<ApiRespostaArtistas> = flow {
        emit(apiService.buscarArtista(nombre))
    }

    override fun buscarAlbumFlow(artista: String, album: String) = flow {
        emit(apiService.buscarAlbum(artista, album))
    }

    override fun getCancionesAlbumFlow(idAlbum: String) = flow {
        emit(apiService.getCancionesAlbum(idAlbum))
    }
}