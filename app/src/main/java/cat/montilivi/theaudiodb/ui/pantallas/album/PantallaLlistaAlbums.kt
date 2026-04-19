package cat.montilivi.theaudiodb.ui.pantallas.album

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.montilivi.theaudiodb.model.Album
import cat.montilivi.theaudiodb.ui.pantallas.common.Carregant
import coil.compose.AsyncImage

@Composable
fun PantallaLlistaAlbums(
    artista: String = "Guns N' Roses",
    viewModel: LlistaAlbumsViewModel = viewModel(),
    onAlbumClick: (String) -> Unit
) {
    val estat by viewModel.estat.collectAsStateWithLifecycle()

    LaunchedEffect(artista) {
        viewModel.obtenAlbums(artista)
    }

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (estat.estaCarregant) {
                Carregant("Cargando álbumes de $artista...")
            } else if (estat.esErroni) {
                Text("Error: ${estat.missatge}")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(estat.albums) { album ->
                        ElementAlbum(album = album, onClick = { onAlbumClick(album.id) })
                    }
                }
            }
        }
    }
}

@Composable
fun ElementAlbum(album: Album, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = album.portada,
                contentDescription = album.titulo,
                modifier = Modifier.fillMaxWidth().height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = album.titulo, style = MaterialTheme.typography.titleMedium, maxLines = 1)
                Text(text = album.anyo, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}