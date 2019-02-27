package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import co.edu.uniquindio.vc.jq.herbariouq.Fragmentos.ListaRecolFragment
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.util.ManagerFireBase
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaRecolectores

import kotlinx.android.synthetic.main.activity_gestion_user.*
import java.text.SimpleDateFormat

class ActivityGestionUser : AppCompatActivity(),ListaRecolFragment.OnRecolSeleccionadoListener {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_user)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);




    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    override fun onPlantaSeleccionado(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}