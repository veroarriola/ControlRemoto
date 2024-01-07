package mx.unam.fciencias.controlremoto

import androidx.lifecycle.ViewModel
class ConnectionsModel : ViewModel() {
    private var _piURL : String = "http://192.168.16.104:8000"
    var piURL: String
        get() = _piURL
        set(value) {
            _piURL = value
        }

    val piURLStream: String
        get() = "$_piURL/stream.mjpg"

    val piURLCommand: String
        get() = "$_piURL/command/"
}