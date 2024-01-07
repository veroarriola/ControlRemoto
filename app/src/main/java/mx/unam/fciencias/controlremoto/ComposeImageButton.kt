package mx.unam.fciencias.controlremoto

import androidx.compose.foundation.Image
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun ComposeImageButton(onClick: () -> Unit,
                       imageVector: ImageVector,
                       buttonModifier: Modifier,
                       contentDescription: String?,
                       imageModifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick = onClick,
        modifier = buttonModifier
    ) {
        //Spacer(modifier = Modifier.width(8.dp)) // Adjust spacing
        Image(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = imageModifier
        )
    }
}