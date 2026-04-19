package cat.montilivi.theaudiodb.ui.pantallas.cancion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.montilivi.theaudiodb.model.Cancion
import cat.montilivi.theaudiodb.ui.pantallas.common.Carregant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLlistaCanciones(
    idAlbum: String,
    viewModel: LlistaCancionsViewModel = viewModel()
) {
    val estat by viewModel.estat.collectAsStateWithLifecycle()

    LaunchedEffect(idAlbum) {
        viewModel.obtenCancions(idAlbum)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Canciones") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (estat.estaCarregant) {
                Carregant("Cargando pistas...")
            } else if (estat.esErroni) {
                Text("No se han podido cargar las canciones: ${estat.missatge}")
            } else {
                LazyColumn {
                    items(estat.canciones) { cancion ->
                        ElementCancion(cancion = cancion)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun ElementCancion(cancion: Cancion) {
    ListItem(
        headlineContent = { Text(cancion.nombre) },
        supportingContent = { Text("Pista ${cancion.numero}") },
        leadingContent = {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
        },
        trailingContent = {
            Text(cancion.duracion)
        }
    )
}