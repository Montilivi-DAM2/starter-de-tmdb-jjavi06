package cat.montilivi.theaudiodb.ui.pantallas.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.montilivi.theaudiodb.data.network.TheAudioDBHelperImpl
import cat.montilivi.theaudiodb.data.network.theAudioDBClient
import cat.montilivi.theaudiodb.model.Album
import cat.montilivi.theaudiodb.model.toAlbum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LlistaAlbumsViewModel : ViewModel() {

    private val _estat = MutableStateFlow(LlistaAlbumsEstat())
    val estat = _estat.asStateFlow()

    private val apiHelper = TheAudioDBHelperImpl(theAudioDBClient.servei)

    fun obtenAlbums(artista: String) {
        viewModelScope.launch {
            apiHelper.buscarAlbumFlow(artista, "")
                .flowOn(Dispatchers.IO)
                .onStart { _estat.update { it.copy(estaCarregant = true) } }
                .catch { error ->
                    _estat.update { it.copy(estaCarregant = false, esErroni = true, missatge = error.message ?: "Error") }
                }
                .collect { respuesta ->
                    val listaLimpia = respuesta.albums?.map { it.toAlbum() } ?: emptyList()
                    _estat.update { it.copy(
                        estaCarregant = false,
                        albums = listaLimpia,
                        esErroni = listaLimpia.isEmpty(),
                        missatge = if (listaLimpia.isEmpty()) "No se encontraron álbumes" else ""
                    ) }
                }
        }
    }

    data class LlistaAlbumsEstat(
        val estaCarregant: Boolean = false,
        val albums: List<Album> = emptyList(),
        val esErroni: Boolean = false,
        val missatge: String = ""
    )
}