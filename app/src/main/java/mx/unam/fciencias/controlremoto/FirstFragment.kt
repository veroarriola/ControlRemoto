package mx.unam.fciencias.controlremoto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.media3.common.util.UnstableApi
//import androidx.media3.common.util.Util
import androidx.navigation.fragment.findNavController
import mx.unam.fciencias.controlremoto.databinding.FragmentFirstBinding
/*import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
*/

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 * https://developer.android.com/guide/topics/media/exoplayer/hello-world?hl=es-419
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /* Too fancy, it seems.
    private var _player: ExoPlayer? = null
    private var _playWhenReady = true
    //private var _currentWindow = 0
    private var _playbackPosition = 0L

    private var _mediaURI: String? = null
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        //_mediaURI = getString(R.string.media_url_mpeg)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    //@UnstableApi
    private fun initializePlayer() {
        // https://developer.android.com/guide/topics/media/exoplayer/live-streaming?hl=es-419
        // Global settings.
        /*
        var context = this.requireContext()
        _player = ExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(context).setLiveTargetOffsetMs(5000))
            .build()
            .also {
                exoPlayer: ExoPlayer ->
                    binding.videoView.player = exoPlayer
                    exoPlayer.playWhenReady = _playWhenReady
                    //exoPlayer.seekTo(_currentWindow, _playbackPosition)
                    //exoPlayer.prepare()
                    //val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4))
                //val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mpeg))
                // Per MediaItem settings.
                val mediaItem =
                    MediaItem.Builder()
                        .setUri("http://192.168.16.104:8000/stream.mjpg")
                        .setLiveConfiguration(
                            MediaItem.LiveConfiguration.Builder().setMaxPlaybackSpeed(1.02f).build()
                        )
                        .build()
                exoPlayer.setMediaItem(mediaItem)
            }
         */

        /* Funcionaría, pero hay que analizar todo el multipart a mano.
        val thread = Thread {
            var urlConnection : HttpURLConnection? = null
            try {
                val link = "http://192.168.16.104:8000/stream.mjpg"
                val url = URL(link)
                urlConnection = url.openConnection() as HttpURLConnection
                val `in`: BufferedInputStream = BufferedInputStream(urlConnection.inputStream)
                readStream(`in`)
            } finally {
                urlConnection?.disconnect()
            }
        }

        thread.start()
         */
        val link = "http://192.168.16.107:8000/stream.mjpg"
        val videoView = binding.videoView
        videoView.loadUrl(link)
    }
    /*
    private fun readStream(bis: BufferedInputStream) {
        while(bis.)
        binding.textviewFirst.text = bis.toString()
        //Bitmap bitmap = BitmapFactory.decodeStream(bufHttpEntity.getContent())
        //binding.videoView.setImageBitmap()
    }
    */

    /*@SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        viewBinding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }*/

    /*
    @UnstableApi private fun releasePlayer() {
        _player?.run {
            _playbackPosition = this.currentPosition
            //currentWindow = this.currentWindowIndex
            _playWhenReady = this.playWhenReady
            release()
        }
        _player = null
    }

    @UnstableApi override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    */
    /*
    @UnstableApi override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    @UnstableApi override fun onResume() {
        super.onResume()
        //hideSystemUi()
        //if ((Util.SDK_INT < 24 || _player == null)) {
            initializePlayer()
        //}
    }*/
    /*
    @UnstableApi override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    @UnstableApi override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }
     */
}