package co.edu.uniquindio.vc.jq.herbariouq.vo

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class ListaRecolectores(): Parcelable {

    var nombre: String? = null
    var rol: String? = null
    var fechaSolicitud: Date? = null
    var correo: String? = null

    constructor(parcel: Parcel) : this() {

        nombre = parcel.readString()
        rol = parcel.readString()
        correo = parcel.readString()
    }

    constructor(nombre: String?,rol: String?,fechaSolicitud: Date?,correo: String?) : this() {

        this.nombre = nombre
        this.rol = rol
        this.fechaSolicitud = fechaSolicitud
        this.correo = correo

    }





    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(rol)
        parcel.writeString(correo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListaRecolectores> {
        override fun createFromParcel(parcel: Parcel): ListaRecolectores {
            return ListaRecolectores(parcel)
        }

        override fun newArray(size: Int): Array<ListaRecolectores?> {
            return arrayOfNulls(size)
        }
    }
}