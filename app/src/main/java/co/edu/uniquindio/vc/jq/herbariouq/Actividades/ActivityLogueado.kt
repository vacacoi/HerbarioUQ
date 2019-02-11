package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.edu.uniquindio.vc.jq.herbariouq.R
import kotlinx.android.synthetic.main.activity_logueado.*
import kotlinx.android.synthetic.main.app_bar_activity_logueado.*
import kotlinx.android.synthetic.main.app_bar_main.*

class ActivityLogueado : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logueado)
        setSupportActionBar(toolbar_logueado)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_logueado, toolbar_logueado, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_logueado.addDrawerListener(toggle)
        toggle.syncState()

        nav_view_logueados.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout_logueado.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_logueado.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_logueado, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings_logueado -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.lista_plantas_logueado -> {
                // Handle the camera action
            }
            R.id.listar_plantas_pendientes -> {

            }
            R.id.gestion_recolectores -> {

            }
            R.id.datos_cuenta -> {

            }
            R.id.salir_login -> {

            }

        }

        drawer_layout_logueado.closeDrawer(GravityCompat.START)
        return true
    }
}
