package co.edu.uniquindio.vc.jq.herbariouq.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import co.edu.uniquindio.vc.jq.herbariouq.Actividades.RevisarPlantasActivity
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import kotlinx.android.synthetic.main.activity_revisar_plantas.view.*
import kotlinx.android.synthetic.main.resumen_plantas_pendientes.view.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class AdaptadorListaPlanPend (fragment: Fragment, var listaPlantas:ArrayList<ListaPlantas>,context: Context) : RecyclerView.Adapter<AdaptadorListaPlanPend.ListaPlantasPenViewHolder>() {


    private lateinit var listener:OnClickAdaptadorListaPlanPen
    var context = context


    init{
        try {
            listener= fragment as OnClickAdaptadorListaPlanPen
        }catch (e:ClassCastException){

            Log.v("AdaptadorDeListaPlantapend", "Error, implemente la interfaz..."+e.message)
        }

    }

    inner class ListaPlantasPenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{


        val nombre: TextView = itemView.txtNombrePen
        val autor: TextView = itemView.txtAutor
        val estado: TextView = itemView.txtEstado
        val btnRevisar : Button = itemView.btn_detalle_rev
        private var fotoPlanta: ImageView = itemView.imgPlanta


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
            GetImageToURL().execute(listaPlantas.urlImagen)


        }
        override fun onClick(v: View?) {
            Log.d("El valor es,","+"+v!!.id)
            Log.d("Planta", "Elemento $adapterPosition clickeado ${nombre.text}")
            listener.onClickPosition(adapterPosition)



        }

        private inner class GetImageToURL : AsyncTask<String, Void, Bitmap>() {

            override fun doInBackground(vararg params: String): Bitmap? {
                try {
                    val url = URL(params[0])
                    val connection = url.openConnection() as HttpURLConnection
                    connection.setDoInput(true)
                    connection.connect()
                    val input = connection.getInputStream()
                    return BitmapFactory.decodeStream(input)
                } catch (e: IOException) {
                    // Log exception
                    return null
                }

            }

            override fun onPostExecute(myBitMap: Bitmap) {
                fotoPlanta!!.setImageBitmap(myBitMap)
            }
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

        p0.btnRevisar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                mostrarDetallePlanta(p1)

            }

        })
    }

    interface OnClickAdaptadorListaPlanPen {
        fun onClickPosition(pos: Int)
    }

    /**
     * Función que permite lanzar actividad de detalle PALNTAS
     */

    fun mostrarDetallePlanta(p1: Int) {

        var listaPlanta: ArrayList<ListaPlantas> = ArrayList()
        listaPlanta = ArrayList()



        listaPlanta.add(
                ListaPlantas(listaPlantas[p1].nombre,listaPlantas[p1].genero,listaPlantas[p1].familia,listaPlantas[p1].subfamilia,
                        listaPlantas[p1].tribu,listaPlantas[p1].especie,listaPlantas[p1].detalle,listaPlantas[p1].autor,
                        listaPlantas[p1].estado,listaPlantas[p1].urlImagen,listaPlantas[p1].email,listaPlantas[p1].key))

        val intent = Intent(context, RevisarPlantasActivity::class.java)
        intent.putExtra("lista",listaPlanta)
        intent.putExtra("p0",0)
        context.startActivity(intent)

    }
}