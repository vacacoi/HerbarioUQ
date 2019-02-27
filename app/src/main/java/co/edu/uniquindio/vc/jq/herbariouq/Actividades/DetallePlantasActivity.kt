package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView
import co.edu.uniquindio.vc.jq.herbariouq.R
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas

import kotlinx.android.synthetic.main.activity_detalle_plantas.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class DetallePlantasActivity : AppCompatActivity() {

    private var mPager: ViewPager? = null
    private var currentPage = 0
    private var plantaImg: ImageView?=null
    private var NUM_PAGES = 0
    private var p0:Int?=0
    //private lateinit var imageModelArrayList: ArrayList<ImagenSlider>
    lateinit var listaPlantas: ArrayList<ListaPlantas> //Recibe la lista de plantas

    private val myImageList = intArrayOf(
            R.drawable.silueta_planta,
            R.drawable.silueta_planta,
            R.drawable.silueta_planta,
            R.drawable.silueta_planta,
            R.drawable.silueta_planta,
            R.drawable.silueta_planta

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_plantas)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        listaPlantas =intent.getSerializableExtra("lista") as ArrayList<ListaPlantas> //Recibe la lista de obtetos de lista de plantas
        p0 = intent.extras.getInt("p0")//Recibe la posición en la cual se oprimio el botón para ver detallado de plantas

        plantaImg = planta_imagen

        GetImageToURL().execute(listaPlantas.get(p0!!).urlImagen)

        textNombreDet.text = " "+listaPlantas.get(p0!!).nombre
        textGeneroDet.text = " "+listaPlantas.get(p0!!).genero
        textFamiliaDet.text = " "+listaPlantas.get(p0!!).familia
        textSubFamiliaDet.text = " "+listaPlantas.get(p0!!).subfamilia
        textTribuDet.text = " "+listaPlantas.get(p0!!).tribu
        textEspecieDet.text = " "+listaPlantas.get(p0!!).especie
        textDetalleDet.text = " "+listaPlantas.get(p0!!).detalle
        textAutorDet.text = " "+listaPlantas.get(p0!!).autor


        //imageModelArrayList = ArrayList()
        //imageModelArrayList = populateList()

        //init()

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
            plantaImg!!.setImageBitmap(myBitMap)
        }
    }
    /**
     * Permite agregar la fecha de presionar atrás
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

    /*private fun populateList(): ArrayList<ImagenSlider> {

        val list : ArrayList<ImagenSlider> = ArrayList()

        for (i in 0..1) {
            val imageModel = ImagenSlider()
            imageModel.setImage_drawable(myImageList[i])
            list.add(imageModel)
        }

        return list
    }

    private fun init() {

        mPager = findViewById(R.id.pager) as ViewPager
        mPager!!.adapter = AdaptadorImagen(this@DetallePlantasActivity, imageModelArrayList)

        val indicator = findViewById(R.id.indicator) as CirclePageIndicator

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.radius = 5 * density

        NUM_PAGES = imageModelArrayList.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

    }*/
}
