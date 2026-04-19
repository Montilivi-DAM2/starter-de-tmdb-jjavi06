package cat.montilivi.theaudiodb.ui.pantallas.artista

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.montilivi.theaudiodb.ui.pantallas.common.Carregant
import coil.compose.AsyncImage

@Composable
fun PantallaBuscaArtista(
    viewModel: BuscaArtistaViewModel = viewModel(),
    onArtistaClick: () -> Unit
) {
    val estat by viewModel.estat.collectAsStateWithLifecycle()
    var textBusca by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Buscador
        TextField(
            value = textBusca,
            onValueChange = { textBusca = it },
            label = { Text("Nombre del artista (ej: Guns N' Roses)") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { viewModel.buscar(textBusca) }) {
                    Icon(Icons.Default.Search, contentDescription = "Buscar")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (estat.estaCarregant) {
            Carregant("Buscando artista...")
        } else if (estat.esErroni) {
            Text("Error: ${estat.missatge}", color = MaterialTheme.colorScheme.error)
        } else {
            estat.artista?.let { artista ->
                FichaArtista(artista = artista, onVerAlbums = onArtistaClick)
            }
        }
    }
}

@Composable
fun FichaArtista(artista: cat.montilivi.theaudiodb.model.Artista, onVerAlbums: () -> Unit) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = artista.foto,
            contentDescription = artista.nombre,
            modifier = Modifier.fillMaxWidth().height(250.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = artista.nombre, style = MaterialTheme.typography.headlineMedium)
        Text(text = artista.genero, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = artista.biografia, style = MaterialTheme.typography.bodyMedium)

        Button(
            onClick = onVerAlbums,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Text("Ver Álbumes")
        }
    }
}