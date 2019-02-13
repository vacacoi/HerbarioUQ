package co.edu.uniquindio.vc.jq.herbariouq.util

import android.content.Context
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import kotlinx.android.synthetic.main.resumen_plantas_pendientes.view.*

class AdaptadorListaPlanPend (fragment: Fragment, var listaPlantas:ArrayList<ListaPlantas>) : RecyclerView.Adapter<AdaptadorListaPlanPend.ListaPlantasPenViewHolder>() {


    private lateinit var listener:OnClickAdaptadorListaPlanPen



    init{
        try {
            listener= fragment as OnClickAdaptadorListaPlanPen
        }catch (e:ClassCastException){

            Log.v("AdaptadorDeListaPlantapend", "Error, implemente la interfaz...")
        }

    }

    inner class ListaPlantasPenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{


        val nombre: TextView = itemView.txtNombrePen
        val autor: TextView = itemView.txtAutor
        val estado: TextView = itemView.txtEstado


        init {
            itemView.setOnClickListener(this)
        }

        /**
         * Función que recibe objeto tipo planta y asigna valor a text View para ser visto en la intefaz
         */

        fun bindListaPlantasPen(listaPlantas: ListaPlantas){
            nombre.text = listaPlantas.nombre
            autor.text = listaPlantas.autor
            estado.text = listaPlantas.estado


        }
        override fun onClick(v: View?) {
            Log.d("El valor es,","+"+v!!.id)
            Log.d("Planta", "Elemento $adapterPosition clickeado ${nombre.text}")
            listener.onClickPosition(adapterPosition)



        }


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListaPlantasPenViewHolder {
        val v = LayoutInflater.from(p0.context)
                .inflate(R.layout.resumen_plantas_pendientes, p0, false)


        return ListaPlantasPenViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listaPlantas.size
    }

    override fun onBindViewHolder(p0: ListaPlantasPenViewHolder, p1: Int) {
        p0.bindListaPlantasPen(listaPlantas.get(p1))
    }

    interface OnClickAdaptadorListaPlanPen {
        fun onClickPosition(pos: Int)
    }



}