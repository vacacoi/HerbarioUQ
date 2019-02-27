package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import co.edu.uniquindio.vc.jq.herbariouq.Fragmentos.ListaPlantasFragment
import co.edu.uniquindio.vc.jq.herbariouq.Fragmentos.ListaPlantasPendFragment
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas

import kotlinx.android.synthetic.main.activity_plantas_pend.*

class PlantasPendActivity : AppCompatActivity(), ListaPlantasPendFragment.OnPlantaPendSeleccionadoListener {
    override fun onPlantaSeleccionado(pos: Int) {

    }

    var listaPlantas: ArrayList<ListaPlantas> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plantas_pend)
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        listaPlantas = ArrayList()
        listaPlantas.add(ListaPlantas("Flor","Flor","Flor","Flor","Flor","Flor","Flor","Flor","Estadp","","",""))
        listaPlantas.add(ListaPlantas("Flor","Flor","Flor","Flor","Flor","Flor","Flor","Flor","Estadp","","",""))
        listaPlantas.add(ListaPlantas("Flor","Flor","Flor","Flor","Flor","Flor","Flor","Flor","Estadp","","",""))


        val fragmentLista = supportFragmentManager.findFragmentById(R.id.fragmentoListaPlantasPend) as ListaPlantasPendFragment
        fragmentLista.listaPlantas = listaPlantas

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}
