package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.util.ManagerFireBase
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas

import kotlinx.android.synthetic.main.activity_revisar_plantas.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class RevisarPlantasActivity : AppCompatActivity() {

    lateinit var listaPlantas: java.util.ArrayList<ListaPlantas> //Recibe la lista de plantas
    private var plantaImg: ImageView?=null
    private var p0:Int?=0
    lateinit var managerFireBase: ManagerFireBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revisar_plantas)
        managerFireBase = ManagerFireBase.managerInstance

        listaPlantas =intent.getSerializableExtra("lista") as ArrayList<ListaPlantas> //Recibe la lista de obtetos de lista de plantas
        p0 = intent.extras.getInt("p0")//Recibe la posición en la cual se oprimio el botón para ver detallado de plantas

        plantaImg = planta_imagen

        GetImageToURL().execute(listaPlantas.get(p0!!).urlImagen)

        textNombreRev.text = " "+listaPlantas.get(p0!!).nombre
        textGeneroRev.text = " "+listaPlantas.get(p0!!).genero
        textFamiliaRev.text = " "+listaPlantas.get(p0!!).familia
        textSubFamiliaRev.text = " "+listaPlantas.get(p0!!).subfamilia
        textTribuRev.text = " "+listaPlantas.get(p0!!).tribu
        textEspecieRev.text = " "+listaPlantas.get(p0!!).especie
        textDetalleRev.text = " "+listaPlantas.get(p0!!).detalle
        textAutorRev.text = " "+listaPlantas.get(p0!!).autor

        btn_aprobar_planta.setOnClickListener {
            managerFireBase.respuestaPlanta("A",listaPlantas.get(p0!!).key,this)
            lanzarActividad()

        }

        btn_rechazar_planta.setOnClickListener {
            managerFireBase.respuestaPlanta("R",listaPlantas.get(p0!!).key,this)
            lanzarActividad()

        }


    }

    /**
     *
     */
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
            plantaImg!!.setImageBitmap(myBitMap)
        }
    }

    /**
     * Permite lanzar la actividad
     */
    fun lanzarActividad() {
        val intent = Intent(this, ActivityLogueado::class.java)
        startActivity(intent)
        finish()
    }

}
