package mx.unam.fciencias.controlremoto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import mx.unam.fciencias.controlremoto.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 * https://developer.android.com/guide/topics/media/exoplayer/hello-world?hl=es-419
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var _player: ExoPlayer? = null
    private var _playWhenReady = true
    private var _currentWindow = 0
    private var _playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun initializePlayer() {
        _player = ExoPlayer.Builder(this.requireContext())
            .build()
            .also {
                exoPlayer: ExoPlayer ->
                    binding.videoView.player = exoPlayer
                exoPlayer.playWhenReady = _playWhenReady
                exoPlayer.seekTo(_currentWindow, _playbackPosition)
                exoPlayer.prepare()
                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp4));
                exoPlayer.setMediaItem(mediaItem)
            }
    }

    /*@SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        viewBinding.videoView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }*/

    @UnstableApi private fun releasePlayer() {
        _player?.run {
            _playbackPosition = this.currentPosition
            _currentWindow = this.currentWindowIndex
            _playWhenReady = this.playWhenReady
            release()
        }
        _player = null
    }

    @UnstableApi override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
        _binding = null
    }

    @UnstableApi override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    @UnstableApi override fun onResume() {
        super.onResume()
        //hideSystemUi()
        if ((Util.SDK_INT < 24 || _player == null)) {
            initializePlayer()
        }
    }

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
}