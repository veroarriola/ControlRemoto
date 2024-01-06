package mx.unam.fciencias.controlremoto

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import mx.unam.fciencias.controlremoto.databinding.ActivityMainBinding


class MainActivityOld : ComponentActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
        }
        /*
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        */
        /*binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        /*
        val botónAdelante = findViewById<ImageButton>(R.id.botónAdelante)
        botónAdelante.setOnClickListener {
            val text = "Adelante"
            val duration = Toast.LENGTH_SHORT
            Toast.makeText(this, text, duration).show()
        }

        val botónAtrás = findViewById<ImageButton>(R.id.botónAtrás)
        botónAtrás.setOnClickListener {
            Toast.makeText(this, "Atrás", Toast.LENGTH_SHORT).show()
        }
        */
    }
/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val temp = supportFragmentManager.findFragmentByTag(ConnectionsDialogFragment.TAG)
                var diálogo : ConnectionsDialogFragment
                if(temp == null) {
                    diálogo = ConnectionsDialogFragment()
                } else {
                    diálogo = temp as ConnectionsDialogFragment
                }
                diálogo.show(supportFragmentManager, ConnectionsDialogFragment.TAG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}
