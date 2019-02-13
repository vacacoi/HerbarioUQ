package co.edu.uniquindio.vc.jq.herbariouq.util


import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.vc.jq.herbariouq.Fragmentos.DetallePlantasFragment
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaRecolectores
import kotlinx.android.synthetic.main.resumen_lista_recolectores.view.*
import org.w3c.dom.Text
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.resumen_lista_plantas.view.*


class AdaptadorListaRecol (fragment: Fragment,var listaRecol:ArrayList<ListaRecolectores>) : RecyclerView.Adapter<AdaptadorListaRecol.ListaRecolectoresViewHolder>(){

    private lateinit var listener:OnClickAdaptadorListaRecol



    init{
        try {
            listener= fragment as OnClickAdaptadorListaRecol
        }catch (e:ClassCastException){
            Log.v("AdaptadorDeListaPlanta", "Error, implemente la interfaz...")
        }

    }

    inner class ListaRecolectoresViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{

        val correo: TextView = itemView.textCorreo
        val nombre : TextView = itemView.txtNombreRecol
        val rol : TextView = itemView.txtRol
        val fechaSol : TextView = itemView.txtFechaSolicitud

        init {
            itemView.setOnClickListener(this)
        }


        fun bindListaRecolectores(listaRecol: ListaRecolectores){

            correo.text = listaRecol.correo
            nombre.text = listaRecol.nombre
            rol.text = listaRecol.rol
            fechaSol.text = listaRecol.fechaSolicitud.toString()

        }

        override fun onClick(v: View?) {
            Log.d("Recolector", "Elemento $adapterPosition clickeado ${nombre.text}")
            listener.onClickPosition(adapterPosition)
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListaRecolectoresViewHolder {
        val v = LayoutInflater.from(p0.context)
                .inflate(R.layout.resumen_lista_recolectores, p0, false)



        return ListaRecolectoresViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listaRecol.size
    }

    override fun onBindViewHolder(p0: ListaRecolectoresViewHolder, p1: Int) {
        p0.bindListaRecolectores(listaRecol.get(p1))
    }

    interface OnClickAdaptadorListaRecol {
        fun onClickPosition(pos: Int)
    }









     //   detallePlanta?.show(fragment, "DetallePlanta")
    }



