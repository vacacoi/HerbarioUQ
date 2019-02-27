package co.edu.uniquindio.vc.jq.herbariouq.Fragmentos


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.widget.LinearLayout


import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.util.AdaptadorListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.util.AdaptadorListaRecol
import co.edu.uniquindio.vc.jq.herbariouq.util.ManagerFireBase
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.vo.Usuarios
import kotlinx.android.synthetic.main.fragment_lista_plantas.*
import kotlinx.android.synthetic.main.resumen_lista_plantas.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListaPlantasFragment : Fragment(),AdaptadorListaPlantas.OnClickAdaptadorListaPlantas,ManagerFireBase.onActualizarAdaptador {

    override fun cedredenciales(usuarios: Usuarios) {

    }


    override fun actualizarAdaptador(listaPlantas: ListaPlantas) {
        agregarPlantas(listaPlantas)
    }


    private lateinit var listener: OnPlantaSeleccionadoListener
    var listaPlantas: ArrayList<ListaPlantas> = ArrayList()
    var adaptador: AdaptadorListaPlantas? = null
    lateinit var managerFireBase: ManagerFireBase


    interface OnPlantaSeleccionadoListener {
        fun onPlantaSeleccionado(pos: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("Oprime Lista plantas", "=");


        return inflater.inflate(R.layout.fragment_lista_plantas, container, false)
    }

    override fun onPause() {
        super.onPause()
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Activity) {

            try {
                listener = context as OnPlantaSeleccionadoListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnListaSeleccionadoListener")
                // Log.v("ListaDePokemonFragment", "Error: ${e.message}")

            }


        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        managerFireBase = ManagerFireBase.managerInstance
        managerFireBase.listener = this
        managerFireBase.escucharEventoFireBase(1,context!!)//Llama al método escucharEventoFirebase, dónde se envía por parámetro 1 los cual indica mostrar lista de plantas

        adaptador = AdaptadorListaPlantas(this,listaPlantas,activity!!.baseContext)
        listaPlantas_view.adapter = this.adaptador
        listaPlantas_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        setHasOptionsMenu(true)
    }



    override fun onClickPosition(pos: Int) {
        listener!!.onPlantaSeleccionado(pos)
    }

    fun agregarPlantas(listaPlanta: ListaPlantas) {
        listaPlantas.add(listaPlanta)
        adaptador!!.notifyItemChanged(0)
    }




}
