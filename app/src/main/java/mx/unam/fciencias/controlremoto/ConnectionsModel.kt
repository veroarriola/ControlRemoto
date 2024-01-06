package mx.unam.fciencias.controlremoto

import androidx.lifecycle.ViewModel
class ConnectionsModel : ViewModel() {
    private var _piURL : String = "http://192.168.16.107:8000/stream.mjpg"
    var piURL: String
        get() = _piURL
        set(value) {
            _piURL = value
        }
}