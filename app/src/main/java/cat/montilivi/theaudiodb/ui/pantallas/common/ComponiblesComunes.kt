package cat.montilivi.theaudiodb.ui.pantallas.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Carregant(missatge: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .weight(7F))
        {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Text(
            text = missatge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(3F))
    }
}