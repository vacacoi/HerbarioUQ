package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.Fragmentos.ListaPlantasFragment
import co.edu.uniquindio.vc.jq.herbariouq.R.id.fab
import co.edu.uniquindio.vc.jq.herbariouq.R.id.toolbar

import kotlinx.android.synthetic.main.activity_lista_plantas.*
import kotlinx.android.synthetic.main.resumen_lista_plantas.view.*

class ListaPlantasActivity : AppCompatActivity(),ListaPlantasFragment.OnPlantaSeleccionadoListener {


    override fun onPlantaSeleccionado(pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    var listaPlantas: ArrayList<ListaPlantas> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_plantas)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}
