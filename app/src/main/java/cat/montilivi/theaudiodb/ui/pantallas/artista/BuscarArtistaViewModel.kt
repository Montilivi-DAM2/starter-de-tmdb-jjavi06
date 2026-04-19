package cat.montilivi.theaudiodb.ui.pantallas.artista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.montilivi.theaudiodb.data.network.TheAudioDBHelperImpl
import cat.montilivi.theaudiodb.data.network.theAudioDBClient
import cat.montilivi.theaudiodb.model.Artista
import cat.montilivi.theaudiodb.model.toArtista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BuscaArtistaViewModel : ViewModel() {

    private val _estat = MutableStateFlow(BuscaArtistaEstat())
    val estat = _estat.asStateFlow()

    private val apiHelper = TheAudioDBHelperImpl(theAudioDBClient.servei)

    fun buscar(nombre: String) {
        viewModelScope.launch {
            apiHelper.buscarArtistaFlow(nombre)
                .flowOn(Dispatchers.IO)
                .onStart { _estat.update { it.copy(estaCarregant = true, esErroni = false) } }
                .catch { error ->
                    _estat.update { it.copy(estaCarregant = false, esErroni = true, missatge = error.message ?: "Error") }
                }
                .collect { resposta ->
                    val artistaEncontrado = resposta.artistas?.firstOrNull()?.toArtista()
                    _estat.update { it.copy(
                        estaCarregant = false,
                        artista = artistaEncontrado,
                        esErroni = artistaEncontrado == null,
                        missatge = if (artistaEncontrado == null) "No se encontró el artista" else ""
                    ) }
                }
        }
    }

    data class BuscaArtistaEstat(
        val estaCarregant: Boolean = false,
        val artista: Artista? = null,
        val esErroni: Boolean = false,
        val missatge: String = ""
    )
}