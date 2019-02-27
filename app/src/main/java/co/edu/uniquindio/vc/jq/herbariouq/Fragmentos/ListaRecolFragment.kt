package co.edu.uniquindio.vc.jq.herbariouq.Fragmentos

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.util.AdaptadorListaRecol
import co.edu.uniquindio.vc.jq.herbariouq.util.ManagerFireBase
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaRecolectores
import co.edu.uniquindio.vc.jq.herbariouq.vo.Usuarios
import kotlinx.android.synthetic.main.fragment_lista_recol.*
import android.R.attr.data




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListaRecolFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListaRecolFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListaRecolFragment : Fragment(),AdaptadorListaRecol.OnClickAdaptadorListaRecol,ManagerFireBase.onActualizarAdaptador {



    var listaRecolectores: ArrayList<ListaRecolectores> = ArrayList()
    var adaptador: AdaptadorListaRecol? = null
    lateinit var managerFireBase: ManagerFireBase
    private lateinit var listener: OnRecolSeleccionadoListener
    var listaUsuarios: java.util.ArrayList<Usuarios> = java.util.ArrayList()

    interface OnRecolSeleccionadoListener {
        fun onPlantaSeleccionado(pos: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_recol, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity) {

            try {
                listener = context as OnRecolSeleccionadoListener
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
        managerFireBase.escucharEventoFireBase(5,context!!)

        adaptador = AdaptadorListaRecol(this, listaUsuarios,context!!)
        listaRecolectores_view.adapter = this.adaptador
        listaRecolectores_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        setHasOptionsMenu(true)
    }

    override fun onClickPosition(pos: Int) {

    }

    override fun actualizarAdaptador(listaPlantas: ListaPlantas) {

    }

    override fun cedredenciales(usuarios: Usuarios) {
        listaUsuarios(usuarios)
    }

    fun listaUsuarios(usuarios: Usuarios) {

        listaUsuarios.add(usuarios)
        adaptador!!.notifyItemChanged(0)

    }


}
