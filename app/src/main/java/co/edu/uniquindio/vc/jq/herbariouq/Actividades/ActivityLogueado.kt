package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.content.Intent
import android.os.Bundle
import android.support.design.internal.NavigationMenuView
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import co.edu.uniquindio.vc.jq.herbariouq.R
import kotlinx.android.synthetic.main.activity_logueado.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_activity_logueado.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.widget.TextView


class ActivityLogueado : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var nombre: String? = null
    var apellido: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logueado)
        setSupportActionBar(toolbar_logueado)

        nombre = intent.extras.getString("nombre")
        apellido = intent.extras.getString("apellido")

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout_logueado, toolbar_logueado, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_logueado.addDrawerListener(toggle)
        toggle.syncState()

        textSubNombre.text = nombre + " " + apellido
        val navigationView = findViewById<View>(R.id.nav_view_logueados) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        val navNombre = headerView.findViewById(R.id.textView_nombre) as TextView
        navNombre.text = nombre + " " + apellido

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
        Log.d("Llega hasta aqui","asdasd")
        when (item.itemId) {
            R.id.lista_plantas_logueado -> {
                val intent = Intent(this, ListaPlantasActivity::class.java)
                intent.putExtra("Lista plantas", "1")
                startActivity(intent)
            }
            R.id.listar_plantas_pendientes -> {
                val intent = Intent(this,PlantasPendActivity::class.java)
                startActivity(intent)
            }
            R.id.gestion_recolectores -> {
                val intent = Intent(this, ActivityGestionUser::class.java)
                intent.putExtra("Gestion Recolectores", "1")
                startActivity(intent)
            }
            R.id.datos_cuenta -> {
                val intent = Intent(this, DatosUsuarioActivity::class.java)
                startActivity(intent)


            }
            R.id.salir_login -> {
                finish()
            }

        }

        drawer_layout_logueado.closeDrawer(GravityCompat.START)
        return true
    }
}
