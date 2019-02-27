package co.edu.uniquindio.vc.jq.herbariouq.util

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import co.edu.uniquindio.vc.jq.herbariouq.vo.ListaPlantas
import co.edu.uniquindio.vc.jq.herbariouq.vo.Sesion
import co.edu.uniquindio.vc.jq.herbariouq.vo.Usuarios
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ManagerFireBase private constructor() {
    private var database: FirebaseDatabase? = null
    private var dataRef: DatabaseReference? = null
    private var dataStore: StorageReference? = null
    private var dataAuth: FirebaseAuth? = null
    lateinit var listener: onActualizarAdaptador
    var sesion: Sesion? = null
    //hace referencia a la bd


    init {
        //para inicializar antes de que el constructor secundario se inicialice
        database = FirebaseDatabase.getInstance()
        dataRef = database!!.reference
        dataStore = FirebaseStorage.getInstance().reference
        dataAuth = FirebaseAuth.getInstance()

        //reference es la rama principal
    }

    companion object {
        // para que este codigo de adentro sea visible desde otras clases
        //el object es parecido al singleton


        private var instancia: ManagerFireBase? = null
        val managerInstance: ManagerFireBase
            get() {
                if (instancia == null) {
                    instancia = ManagerFireBase()
                }
                return instancia!!
            }
    }

    fun insertar(listaPlantas: ListaPlantas) {
        //AUTOGENERA LLAVE PRIMARIA
        dataRef!!.push().setValue(listaPlantas)

    }

    fun insetarConLLavePlanta(listaPlantas: ListaPlantas): String {

        val llave:String = llave()
        dataRef!!.child(llave).child("plantas").setValue(listaPlantas)
        dataRef!!.child(llave).child("plantas").child("key").setValue(llave)
        return llave
    }

    fun insetarConLLaveUsuario(usuarios: Usuarios): String {

        val llave:String = llave()
        dataRef!!.child(llave).child("usuarios").setValue(usuarios)
        dataRef!!.child(llave).child("usuarios").child("key").setValue(llave)
        return llave
    }

    /**
     * Genera clave de acuerdo a la fecha y hora.
     */
    fun llave(): String {
        val date = Date()
        val hourdateFormat = SimpleDateFormat("HHmmssddMMyyyy")
        return hourdateFormat.format(date)
    }


    /**
     * Permite cargar una imagen a FireBase y actualizar la url en donde queda la imagen guardada.
     */
    fun uploadImage(file: String, rutaBD: String, llave: String, tipo: Int): String? {
        val file = Uri.fromFile(File(file))
        val riversRef = dataStore!!.child(rutaBD)
        var downloadUri: String? = null

        riversRef.putFile(file).addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> {
            riversRef.getDownloadUrl().addOnSuccessListener(
                    OnSuccessListener<Uri> { uri ->
                        Log.d("Holaa", "onSuccess: uri= $uri")
                        if (tipo == 1) {
                            dataRef!!.database.reference.child(llave).child("plantas").child("urlImagen")
                                    .setValue(uri.toString())
                        } else if (tipo == 2) {
                            dataRef!!.database.reference.child(llave).child("usuarios").child("urlImagenPerfil")
                                    .setValue(uri.toString())
                        }
                    })
        })

        return downloadUri
    }

    /**
     * Función que permite actualizar los datos de un usuarios
     */
    fun updateUser(nombre:String,apellido:String,telefono:String,profesion:String,key:String){

        dataRef!!.database.reference.child(key).child("usuarios").child("nombre").setValue(nombre)
        dataRef!!.database.reference.child(key).child("usuarios").child("apellido").setValue(apellido)
        dataRef!!.database.reference.child(key).child("usuarios").child("telefono").setValue(telefono)
        dataRef!!.database.reference.child(key).child("usuarios").child("profesion").setValue(profesion)

    }

    /**
     * Método que permite consultar en tiempo real a firebase y traer los datos almacenados
     */
    fun escucharEventoFireBase(tipo: Int, context: Context) {

        sesion = Sesion(context)

        dataRef!!.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.v("ManagerFire", "onCancelled")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                Log.v("ManagerFire", "onChildMoved")
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                if (p0.child("plantas").exists() && tipo == 1) {
                    val listaPlantas = p0!!.child("plantas").getValue(ListaPlantas::class.java)!!
                    if (listaPlantas != null && listaPlantas!!.estado == "A") {
                        listener.actualizarAdaptador(listaPlantas)
                    }
                }else if(p0.child("usuarios").exists() && tipo == 2){
                    Log.d("sE HA CAM","=")
                    /*val listaPlantas = p0.child("usuarios").getValue(Usuarios::class.java)!!
                    if (sesion!!.getusename().equals(Usuarios.email)) {
                        listener.actualizarAdaptador(listaPlantas)
                    }*/
                }else if(p0.child("plantas").exists() && tipo == 4){
                    val listaPlantas = p0.child("plantas").getValue(ListaPlantas::class.java)!!
                    if (listaPlantas.estado == "I") {
                        listener.actualizarAdaptador(listaPlantas)
                    }
                }
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                if (p0.child("plantas").exists() && tipo == 1) {
                    val listaPlantas = p0.child("plantas").getValue(ListaPlantas::class.java)!!
                    if (listaPlantas.estado == "A") {
                        listener.actualizarAdaptador(listaPlantas)
                    }

                } else if (p0.child("usuarios").exists() && tipo == 2) {
                    val listaUsuario = p0.child("usuarios").getValue(Usuarios::class.java)!!
                    listener.cedredenciales(listaUsuario)

                } else if (p0.child("plantas").exists() && tipo == 3) {
                    val listaPlantas = p0.child("plantas").getValue(ListaPlantas::class.java)!!
                    if (sesion!!.getusename().equals(listaPlantas.email)) {
                        listener.actualizarAdaptador(listaPlantas)
                    }
                } else if(p0.child("plantas").exists() && tipo == 4){
                    val listaPlantas = p0.child("plantas").getValue(ListaPlantas::class.java)!!
                    if (listaPlantas.estado == "I") {
                        listener.actualizarAdaptador(listaPlantas)
                    }
                }else if (p0.child("usuarios").exists() && tipo == 5) {
                    Log.d("Llega hasta aqui","=")
                    val listaUsuario = p0.child("usuarios").getValue(Usuarios::class.java)!!
                    if(listaUsuario.estado == "P"){
                        listener.cedredenciales(listaUsuario)
                    }

                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }
        })
    }



    interface onActualizarAdaptador {
        fun actualizarAdaptador(listaPlantas: ListaPlantas)
        fun cedredenciales(usuarios: Usuarios)
    }


    /**
     * Pwrmite cambiar el estado de una planta, ya se a rechazada o aprobada
     */
    fun respuestaPlanta(estado:String?,key:String?,context: Context){
        dataRef!!.database.reference.child(key!!).child("plantas").child("estado").setValue(estado!!)
        alertDialog(context)
    }

    /**
     * Permite cambiar el estado de un usuario a aprobado o rechazado
     */
    fun respuestaUsuario(estado:String?,key:String?,context: Context){
        dataRef!!.database.reference.child(key!!).child("usuarios").child("estado").setValue(estado!!)
        alertDialog(context)
    }


    /**
     * Registrar email y contraseña
     */

    fun registroUsuario(
            email: String,
            pass: String,
            progressDialog: ProgressDialog,
            context: Context,
            ruta: String,
            rutaImagenDb: String,
            usuarios: Usuarios
    ) {


        dataAuth!!.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = dataAuth!!.getCurrentUser()
                        var llaveImagen = insetarConLLaveUsuario(usuarios)
                        uploadImage(ruta, rutaImagenDb, llaveImagen, 2)
                        progressDialog.dismiss()
                        alertDialog(context)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Hola", "createUserWithEmail:failure", task.exception)
                        progressDialog.dismiss()
                        Toast.makeText(
                                context,
                                "No se pudo registrar el correo electrónico o contraseña (mayor a 6 digitos)",
                                Toast.LENGTH_LONG
                        ).show()
                        return@OnCompleteListener
                    }
                })


    }


    /**
     * Función que permite abrir un dialogo donde se confirma la que la planta ha
     * sido agregada con exito
     */
    fun alertDialog(context: Context) {
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        builder.setTitle("Información")

        // Display a message on alert dialog
        builder.setMessage(
                "Se ha registrado correctamente!!"
        )

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Aceptar") { dialog, which ->
            // Do something when user press the positive button
            // Change the app background colo
        }

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}