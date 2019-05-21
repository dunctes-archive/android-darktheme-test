package me.duncte123.themeswitch

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val SETTINGS_ACTION = 1
        const val RESULT_CODE_THEME_UPDATED = 1
        var themeId = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = PreferenceManager
            .getDefaultSharedPreferences(this)

        if (prefs.getBoolean("theme_switch", false)) {
            themeId = R.style.AppTheme_Dark
            setTheme(R.style.AppTheme_Dark_NoActionBar)
        } else {
            themeId = R.style.AppTheme
            setTheme(R.style.AppTheme_NoActionBar)
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
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
            R.id.action_settings -> {
                startActivityForResult(Intent(this, SettingsActivity::class.java), SETTINGS_ACTION)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SETTINGS_ACTION) {
            if (resultCode == RESULT_CODE_THEME_UPDATED) {
                finish()
                startActivity(intent)
                return
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
