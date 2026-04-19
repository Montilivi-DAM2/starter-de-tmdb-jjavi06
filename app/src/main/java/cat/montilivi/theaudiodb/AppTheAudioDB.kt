package cat.montilivi.theaudiodb

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cat.montilivi.theaudiodb.navegacion.GrafDeNavegacio
import cat.montilivi.theaudiodb.navegacion.etiquetesDelDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTheAudioDB(modifier: Modifier = Modifier) {
    val controladorDeNavegacio = rememberNavController()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                etiquetesDelDrawer.forEach { etiqueta ->
                    NavigationDrawerItem(
                        label = { Text(etiqueta.titol) },
                        selected = false,
                        onClick = {
                            controladorDeNavegacio.navigate(etiqueta.ruta)
                            scope.launch { drawerState.close() }
                        },
                        icon = { Icon(etiqueta.iconaNoSeleccionada, null) }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("AudioDB Explorer") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            GrafDeNavegacio(controladorDeNavegacio, Modifier.padding(padding))
        }
    }
}