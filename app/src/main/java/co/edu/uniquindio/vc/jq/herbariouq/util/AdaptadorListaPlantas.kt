package co.edu.uniquindio.vc.jq.herbariouq.util

import android.support.v4.app.Fragment
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import kotlinx.android.synthetic.main.resumen_lista_plantas.view.*

class AdaptadorListaPlantas(fragment: Fragment,var listaPlantas:ArrayList<ListaPlantas>) {



    inner class ListaPlantasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{


        val nombre: TextView = itemView.txtNombre
        val genero: TextView = itemView.txtGenero

        init {
            itemView.setOnClickListener(this)
        }

        fun bindListaPlantas(listaPlantas: ListaPlantas){
            
        }
        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }


}