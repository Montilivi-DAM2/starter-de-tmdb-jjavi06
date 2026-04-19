package cat.montilivi.theaudiodb.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.montilivi.theaudiodb.ui.pantallas.PantallaPortada
import cat.montilivi.theaudiodb.ui.pantallas.album.PantallaLlistaAlbums
import cat.montilivi.theaudiodb.ui.pantallas.artista.PantallaBuscaArtista
import cat.montilivi.theaudiodb.ui.pantallas.cancion.PantallaLlistaCanciones

@Composable
fun GrafDeNavegacio(
    controladorDeNavegacio: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = controladorDeNavegacio,
        startDestination = DestPortada,
        modifier = modifier
    ) {
        composable<DestPortada> { PantallaPortada() }

        composable<DestBuscaArtista> {
            PantallaBuscaArtista(
                onArtistaClick = { controladorDeNavegacio.navigate(DestLlistaAlbums) }
            )
        }

        composable<DestLlistaAlbums> {
            PantallaLlistaAlbums(
                onAlbumClick = { id -> controladorDeNavegacio.navigate(DestCancionesDisco(id)) }
            )
        }

        composable<DestCancionesDisco> { backStackEntry ->
            val destino: DestCancionesDisco = backStackEntry.toRoute()
            PantallaLlistaCanciones(idAlbum = destino.idAlbum)
        }
    }
}