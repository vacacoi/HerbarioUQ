package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import co.edu.uniquindio.vc.jq.herbariouq.Fragmentos.ListaRecolFragment
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaRecolectores

import kotlinx.android.synthetic.main.activity_gestion_user.*
import java.text.SimpleDateFormat

class ActivityGestionUser : AppCompatActivity(),ListaRecolFragment.OnRecolSeleccionadoListener {

    override fun onPlantaSeleccionado(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var listaRecolectores: ArrayList<ListaRecolectores> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestion_user)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);

        val date = SimpleDateFormat("MM/dd/yyyy").parse("02/13/2019")

        listaRecolectores = ArrayList()
        listaRecolectores.add(ListaRecolectores("Flor","Flor",date,"Flor"))
        listaRecolectores.add(ListaRecolectores("Flor1","Flor1",date,"Flor1"))
        listaRecolectores.add(ListaRecolectores("Flor","Flor",date,"Flor"))


        val fragmentLista = supportFragmentManager.findFragmentById(R.id.fragmentoListaRecol) as ListaRecolFragment
        fragmentLista.listaRecolectores= listaRecolectores

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}