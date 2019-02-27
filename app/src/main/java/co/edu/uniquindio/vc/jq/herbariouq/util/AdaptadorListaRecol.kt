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
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.widget.Button
import android.widget.ImageView
import co.edu.uniquindio.vc.jq.herbariouq.Actividades.ActivityGestionUser
import co.edu.uniquindio.vc.jq.herbariouq.Actividades.ActivityLogueado
import co.edu.uniquindio.vc.jq.herbariouq.vo.Usuarios
import kotlinx.android.synthetic.main.resumen_lista_plantas.view.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class AdaptadorListaRecol (fragment: Fragment,var listaUsuarios:ArrayList<Usuarios>,context: Context) : RecyclerView.Adapter<AdaptadorListaRecol.ListaRecolectoresViewHolder>(){

    private lateinit var listener:OnClickAdaptadorListaRecol
    var context = context

    lateinit var managerFireBase: ManagerFireBase

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
        val btnAprobar: Button = itemView.btn_aprobar
        val btnRechazar: Button = itemView.btn_rechazar
        val btnCancelar: Button = itemView.btn_cancelar
        val fotoPerfil : ImageView = itemView.imgPerfil

        init {
            itemView.setOnClickListener(this)
        }


        fun bindListaRecolectores(listaUsuarios: Usuarios){

            correo.text = listaUsuarios.correo
            nombre.text = listaUsuarios.nombre
            rol.text = "RECOLECTOR"
            fechaSol.text = listaUsuarios.estado
            GetImageToURL().execute(listaUsuarios.urlImagenPerfil)


        }

        override fun onClick(v: View?) {
            Log.d("Recolector", "Elemento $adapterPosition clickeado ${nombre.text}")
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
                fotoPerfil!!.setImageBitmap(myBitMap)
            }
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListaRecolectoresViewHolder {
        val v = LayoutInflater.from(p0.context)
                .inflate(R.layout.resumen_lista_recolectores, p0, false)



        return ListaRecolectoresViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    override fun onBindViewHolder(p0: ListaRecolectoresViewHolder, p1: Int) {
        p0.bindListaRecolectores(listaUsuarios.get(p1))
        managerFireBase = ManagerFireBase.managerInstance
        p0.btnAprobar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                managerFireBase.respuestaUsuario("A",listaUsuarios[p1].key,context)
                lanzarActividad()


            }

        })

        p0.btnRechazar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                managerFireBase.respuestaUsuario("P",listaUsuarios[p1].key,context)

            }

        })

        p0.btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {


            }

        })
    }

    interface OnClickAdaptadorListaRecol {
        fun onClickPosition(pos: Int)
    }


    /**
     * Permite lanzar la actividad
     */
    fun lanzarActividad() {
        val intent = Intent(context, ActivityLogueado::class.java)
        context.startActivity(intent)



    }










     //   detallePlanta?.show(fragment, "DetallePlanta")
    }



