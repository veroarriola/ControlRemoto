package mx.unam.fciencias.controlremoto

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import mx.unam.fciencias.controlremoto.ui.theme.ControlRemotoTheme
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.NoRouteToHostException
import java.net.ProtocolException
import java.net.URL


@Composable
fun SteeringControl(conModel: ConnectionsModel, modifier: Modifier = Modifier) {
    ConstraintLayout (modifier.fillMaxWidth()) {
        // Referencias para los componentes a restringir
        val (buttonForward, buttonBackward, buttonLeft, buttonRight,
            buttonNW, buttonNE, buttonSE, buttonSW, buttonStop,
            buttonAccelerate, buttonBrake,
            buttonTurnLeft, buttonTurnRight) = createRefs()
        val imageVector = ImageVector.vectorResource(id = R.drawable.flecha)
        val bottomGuideline = createGuidelineFromBottom(4.dp)
        val endGuideline = createGuidelineFromEnd(4.dp)
        val startGuideline = createGuidelineFromStart(4.dp)

        var toastText = remember { mutableStateOf("") }

        ComposeImageButton(
            onClick = { /* Do something */
                postCommand(conModel = conModel, command = "SE", toastText)
            },
            buttonModifier = Modifier.constrainAs(buttonSE) {
                bottom.linkTo(bottomGuideline)
                end.linkTo(endGuideline)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.SE),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = 135f
                }
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "backward", toastText) },
            buttonModifier = Modifier.constrainAs(buttonBackward) {
                end.linkTo(buttonSE.start)
                bottom.linkTo(bottomGuideline)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.backward),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = 180f
                }
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "SW", toastText) },
            buttonModifier = Modifier.constrainAs(buttonSW) {
                end.linkTo(buttonBackward.start)
                bottom.linkTo(bottomGuideline)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.SW),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = -135f
                }
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "right", toastText) },
            buttonModifier = Modifier.constrainAs(buttonRight) {
                bottom.linkTo(buttonSE.top)
                end.linkTo(endGuideline)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.right),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = 90f
                }
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "stop", toastText) },
            buttonModifier = Modifier.constrainAs(buttonStop) {
                bottom.linkTo(buttonBackward.top)
                end.linkTo(buttonRight.start)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.alto),
            contentDescription = stringResource(id = R.string.stop),
            imageModifier = Modifier
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "left", toastText) },
            buttonModifier = Modifier.constrainAs(buttonLeft) {
                bottom.linkTo(buttonSW.top)
                end.linkTo(buttonStop.start)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.left),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = -90f
                }
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "NE", toastText) },
            buttonModifier = Modifier.constrainAs(buttonNE) {
                bottom.linkTo(buttonRight.top)
                end.linkTo(endGuideline)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.NE),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = 45f
                }
        )
        ComposeImageButton(
            onClick =
            { /* Do something */
                postCommand(conModel = conModel, command = "forward", toastText)
            },
            buttonModifier = Modifier.constrainAs(buttonForward) {
                end.linkTo(buttonNE.start)
                bottom.linkTo(buttonStop.top)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.forward)
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "NW", toastText) },
            buttonModifier = Modifier.constrainAs(buttonNW) {
                end.linkTo(buttonForward.start)
                bottom.linkTo(buttonStop.top)
            },
            imageVector = imageVector,
            contentDescription = stringResource(id = R.string.NW),
            imageModifier = Modifier
                .graphicsLayer {
                    rotationZ = -45f
                }
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "turn_right", toastText) },
            buttonModifier = Modifier.constrainAs(buttonTurnRight) {
                bottom.linkTo(bottomGuideline)
                start.linkTo(startGuideline)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_rotate_right_24),
            contentDescription = stringResource(id = R.string.turn_right)
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "turn_left", toastText) },
            buttonModifier = Modifier.constrainAs(buttonTurnLeft) {
                bottom.linkTo(buttonTurnRight.top)
                start.linkTo(startGuideline)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_rotate_left_24),
            contentDescription = stringResource(id = R.string.turn_left)
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "brake", toastText) },
            buttonModifier = Modifier.constrainAs(buttonBrake) {
                bottom.linkTo(buttonTurnLeft.top)
                start.linkTo(startGuideline)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.frenar),
            contentDescription = stringResource(id = R.string.brake)
        )
        ComposeImageButton(
            onClick = { postCommand(conModel = conModel, command = "accelerate", toastText) },
            buttonModifier = Modifier.constrainAs(buttonAccelerate) {
                bottom.linkTo(buttonBrake.top)
                start.linkTo(startGuideline)
            },
            imageVector = ImageVector.vectorResource(id = R.drawable.acelerar),
            contentDescription = stringResource(id = R.string.accelerate)
        )
        if(toastText.value.isNotEmpty()) {
            Toast.makeText(LocalContext.current,
                toastText.value,
                Toast.LENGTH_SHORT).show()
            toastText.value = ""
        }
    }
}

fun postCommand(conModel: ConnectionsModel, command: String, toastText: MutableState<String>) {
    // https://developer.android.com/reference/java/net/HttpURLConnection
    toastText.value = "Enviando comando ${command}"
    val thread = Thread {
        val url = URL(conModel.piURLCommand)
        val urlConnection = url.openConnection() as HttpURLConnection
        var respuesta: String
        try {
            urlConnection.requestMethod = "POST"
            urlConnection.addRequestProperty("command", command)
            urlConnection.doOutput = true

            //urlConnection.setChunkedStreamingMode(0)
            val bytesToSend = command.toByteArray()
            urlConnection.setFixedLengthStreamingMode(bytesToSend.size)

            val out: OutputStream = BufferedOutputStream(urlConnection.outputStream)
            //out.write(command.toByteArray())
            out.write(bytesToSend)
            out.flush()
            out.close()

            if(urlConnection.responseCode == 200) {
                val `in`: InputStream = BufferedInputStream(urlConnection.inputStream)
                //readStream(`in`)
                //respuesta = `in`.toString()
                //toastText.value = `in`.toString()
            } else {
                val err: InputStream = BufferedInputStream(urlConnection.errorStream)
                //toastText.value = "Conexión != 200"
            }
        } catch (e: NoRouteToHostException) {
            // ¿Cómo aviso que se conecte?
            //toastText.value = "Sin ruta al huesped."
        } catch (e: ProtocolException) {
            //toastText.value = "Error $e."
        } catch (e: IOException) {
            //toastText.value = "Flujo roto: $e."
        } finally {
            urlConnection.disconnect()
        }
    }
    thread.start()
}


@Preview(showBackground = true)
@Composable
fun ControlPreview() {
    ControlRemotoTheme {
        SteeringControl(ConnectionsModel())
    }
}