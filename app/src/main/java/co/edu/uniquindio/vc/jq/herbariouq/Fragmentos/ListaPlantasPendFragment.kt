package co.edu.uniquindio.vc.jq.herbariouq.Fragmentos

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.util.AdaptadorListaPlanPend
import co.edu.uniquindio.vc.jq.herbariouq.util.AdaptadorListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.util.ManagerFireBase
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.vo.Usuarios
import kotlinx.android.synthetic.main.fragment_lista_plantas_pend.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListaPlantasPendFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListaPlantasPendFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListaPlantasPendFragment : Fragment(), AdaptadorListaPlanPend.OnClickAdaptadorListaPlanPen,ManagerFireBase.onActualizarAdaptador {



    private lateinit var listener: OnPlantaPendSeleccionadoListener
    var listaPlantas: ArrayList<ListaPlantas> = ArrayList()
    var adaptador: AdaptadorListaPlanPend? = null
    lateinit var managerFireBase: ManagerFireBase

    interface OnPlantaPendSeleccionadoListener {
        fun onPlantaSeleccionado(pos: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("Oprime Lista plantas", "=");


        return inflater.inflate(R.layout.fragment_lista_plantas_pend, container, false)
    }

    override fun onPause() {
        super.onPause()
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is Activity) {

            try {
                listener = context as OnPlantaPendSeleccionadoListener
            } catch (e: ClassCastException) {
                throw ClassCastException("${activity.toString()} debe implementar la interfaz OnListaSeleccionadoListener")
                 Log.v("ListaDePokemonFragment", "Error: ${e.message}")

            }


        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        managerFireBase = ManagerFireBase.managerInstance
        managerFireBase.listener = this
        managerFireBase.escucharEventoFireBase(4,context!!)

        adaptador = AdaptadorListaPlanPend(this, listaPlantas,activity!!.baseContext)
       listaPlanPen_view.adapter = this.adaptador
        listaPlanPen_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        setHasOptionsMenu(true)
    }

    override fun onClickPosition(pos: Int) {
        listener!!.onPlantaSeleccionado(pos)
    }

    fun agregarPlantas(listaPlanta: ListaPlantas) {
        //listaPlantas.remove(listaPlanta)
        listaPlantas.add(listaPlanta)
        adaptador!!.notifyItemChanged(0)
    }

    override fun actualizarAdaptador(listaPlantas: ListaPlantas) {
        agregarPlantas(listaPlantas)
    }

    override fun cedredenciales(usuarios: Usuarios) {

    }

}

