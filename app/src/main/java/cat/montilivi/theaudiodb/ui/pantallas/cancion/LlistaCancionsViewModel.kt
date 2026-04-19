package cat.montilivi.theaudiodb.ui.pantallas.cancion

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import cat.montilivi.theaudiodb.navegacion.DestCancionesDisco
import cat.montilivi.theaudiodb.data.network.TheAudioDBHelperImpl
import cat.montilivi.theaudiodb.data.network.theAudioDBClient
import cat.montilivi.theaudiodb.model.Cancion
import cat.montilivi.theaudiodb.model.toCancion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LlistaCancionsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _estat = MutableStateFlow(LlistaCancionsEstat())
    val estat = _estat.asStateFlow()

    private val apiHelper = TheAudioDBHelperImpl(theAudioDBClient.servei)

    init {
        val ruta = savedStateHandle.toRoute<DestCancionesDisco>()
        obtenCancions(ruta.idAlbum)
    }

    fun obtenCancions(idAlbum: String) {
        viewModelScope.launch {
            apiHelper.getCancionesAlbumFlow(idAlbum)
                .flowOn(Dispatchers.IO)
                .onStart { _estat.update { it.copy(estaCarregant = true) } }
                .catch { error ->
                    _estat.update { it.copy(estaCarregant = false, esErroni = true, missatge = error.message ?: "Error") }
                }
                .collect { respuesta ->
                    val cancionesLimpas = respuesta.canciones?.map { it.toCancion() } ?: emptyList()
                    _estat.update { it.copy(
                        estaCarregant = false,
                        canciones = cancionesLimpas,
                        esErroni = cancionesLimpas.isEmpty()
                    ) }
                }
        }
    }

    data class LlistaCancionsEstat(
        val estaCarregant: Boolean = false,
        val canciones: List<Cancion> = emptyList(),
        val esErroni: Boolean = false,
        val missatge: String = ""
    )
}