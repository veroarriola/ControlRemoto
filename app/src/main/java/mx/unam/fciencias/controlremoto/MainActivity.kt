package mx.unam.fciencias.controlremoto

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.preference.PreferenceManager
import mx.unam.fciencias.controlremoto.ui.theme.ControlRemotoTheme


class MainActivity : ComponentActivity() {

    private val _connectionsModel: ConnectionsModel by viewModels()
    private lateinit var _prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        var urlIni = _prefs.getString("raspberryURL", getString(R.string.default_ip))!!
        _connectionsModel.piURL = urlIni

        setContent {
            ControlRemotoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Steering(_connectionsModel)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        val editPrefs: Editor = _prefs.edit()
        editPrefs.putString("raspberryURL", _connectionsModel.piURL)
        editPrefs.commit()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Steering(conModel: ConnectionsModel, modifier: Modifier = Modifier) {
    var showIpDialog = remember { mutableStateOf(false) }
    //var context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Volante")
                },
                actions = {
                    IconButton(
                        onClick = {
                            showIpDialog.value = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Opciones"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            SteeringControl(conModel = conModel)
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            WebViewScreen(link = conModel.piURLStream)
            Text(
                text = "Hello in ${conModel.piURL}",
                modifier = modifier
            )
        }
        if (showIpDialog.value) {
            IPDialogForm(
                conModel.piURL,
                onDismissRequest = {
                    showIpDialog.value = false
                },
                onConfirmation = { link : String ->
                    conModel.piURL = link
                    showIpDialog.value = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IPDialogForm(
    currentIP: String,
    onDismissRequest: () -> Unit,
    onConfirmation: (link : String) -> Unit
) {
    var ipText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(currentIP))
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card {
            Column (
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = ipText,
                    onValueChange = {
                        it.also { ipText = it }
                    }
                )
                AcceptCancelRow(
                    onDismissRequest = { onDismissRequest() },
                    onConfirmation = {
                        onConfirmation(ipText.text)
                    }
                )
            }
        }
    }
}

@Composable
fun AcceptCancelRow(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        TextButton(
            onClick = { onDismissRequest() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Cancelar")
        }
        TextButton(
            onClick = { onConfirmation() },
            modifier = Modifier.padding(8.dp),
        ) {
            Text("Aceptar")
        }
    }
}

@Composable
fun WebViewScreen(link: String) {
    // https://medium.com/@sahar.asadian90/webview-in-jetpack-compose-71f237873c2e
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
            }
        },
        update = { webView ->
            webView.loadUrl(link)
        }
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ControlRemotoTheme {
        Steering(ConnectionsModel())
    }
}
