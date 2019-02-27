package co.edu.uniquindio.vc.jq.herbariouq.vo

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.*

class ListaPlantas() : Parcelable, Serializable {

    var nombre: String? = null
    var genero: String? = null
    var familia: String? = null
    var subfamilia: String? = null
    var tribu: String? = null
    var especie: String? = null
    var detalle: String? = null
    var autor: String? = null
    var estado: String? = null
    var urlImagen: String?=null
    var email: String?=null
    var key: String?=null

    constructor(parcel: Parcel) : this() {
        nombre = parcel.readString()
        genero = parcel.readString()
        familia = parcel.readString()
        subfamilia = parcel.readString()
        tribu = parcel.readString()
        especie = parcel.readString()
        detalle = parcel.readString()
        autor = parcel.readString()
        estado = parcel.readString()
        urlImagen = parcel.readString()
        email = parcel.readString()
        key = parcel.readString()

    }




    constructor(nombre: String?,genero: String?,familia: String?,subfamilia: String?, tribu: String?, especie: String?,
                detalle: String?,autor: String?, estado:String?,urlImagen: String?,email:String?,key:String?) : this() {

        this.nombre = nombre
        this.genero = genero
        this.familia = familia
        this.subfamilia = subfamilia
        this.tribu = tribu
        this.especie = especie
        this.detalle = detalle
        this.autor = autor
        this.estado = estado
        this.urlImagen = urlImagen
        this.email = email
        this.key = key


    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(nombre)
        parcel.writeString(genero)
        parcel.writeString(familia)
        parcel.writeString(subfamilia)
        parcel.writeString(tribu)
        parcel.writeString(especie)
        parcel.writeString(detalle)
        parcel.writeString(autor)
        parcel.writeString(estado)
        parcel.writeString(urlImagen)
        parcel.writeString(email)
        parcel.writeString(key)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListaPlantas> {
        override fun createFromParcel(parcel: Parcel): ListaPlantas {
            return ListaPlantas(parcel)
        }

        override fun newArray(size: Int): Array<ListaPlantas?> {
            return arrayOfNulls(size)
        }
    }


}