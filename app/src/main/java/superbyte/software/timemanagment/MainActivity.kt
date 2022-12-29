package superbyte.software.timemanagment

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import superbyte.software.timemanagment.calendar.CalendarFragment
import superbyte.software.timemanagment.home.HomeFragment
import superbyte.software.timemanagment.settings.SettingsFragment


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment = HomeFragment()
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_calendar -> selectedFragment = CalendarFragment()
                R.id.nav_personalize -> selectedFragment = SettingsFragment()
                R.id.nav_settings -> selectedFragment = SettingsFragment()
            }
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                selectedFragment
            ).commit()
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        //initialize on the home fragment
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            HomeFragment()
        ).commit()
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}