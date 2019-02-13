package co.edu.uniquindio.vc.jq.herbariouq.Actividades

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText
import co.edu.uniquindio.vc.jq.herbariouq.R

import kotlinx.android.synthetic.main.activity_datos_usuario.*
import kotlinx.android.synthetic.main.content_datos_usuario.*

class DatosUsuarioActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_usuario)

        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true)

        txtDatosCorreo.setText("vacruzg@uniquindio.edu.co")
        txtNombreDatos.setText("Victor")
        txtDatosApellido.setText("Cruz")
        txtDatoTelefono.setText("3122448239")
        txtDatosProfesion.setText("Estudiante")

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}
