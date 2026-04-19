package cat.montilivi.theaudiodb.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object DestPortada

@Serializable
object DestBuscaArtista

@Serializable
object DestLlistaAlbums

@Serializable
data class DestCancionesDisco(val idAlbum: String)

@Serializable
object DestPreferencies

@Serializable
object DestQuantA

data class EtiquetaDelDrawer<T:Any>(
    val ruta: T,
    val iconaNoSeleccionada: ImageVector,
    val iconaSeleccionada: ImageVector,
    val titol: String,
    val teBadge: Boolean = false
)

val etiquetesDelDrawer = listOf(
    EtiquetaDelDrawer(DestPortada, Icons.Outlined.Home, Icons.Filled.Home, "Inicio"),
    EtiquetaDelDrawer(DestBuscaArtista, Icons.Outlined.Search, Icons.Filled.Search, "Buscar artista"),
    EtiquetaDelDrawer(DestLlistaAlbums, Icons.Outlined.Album, Icons.Filled.Album, "Álbumes"),
)