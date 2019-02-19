package co.edu.uniquindio.moviles.herbariohuq.vo

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.IntegerRes

/**
 * Created by Juan Pablo Rodriguez on 17/09/2018.
 */

class Planta(): Parcelable{

    /**
     * identificación de la planta
     */
    var id: String? = null

    var nombre:String? = null
    var categoria:String? = null
    var genero:String? = null
    var cantidadFotos:String? = null
    var nombreImagen:Int? = null


    /**
     * Constructor base para cargar las personas
     * en el recyclerView
     *
     * @param nombre, nombre de la planta
     * @param categoria, categoria de la planta
     * @param genero, genero de la planta
     * @param cantidadFotos, cantidad de fotos de la planta
     */



    constructor(parcel: Parcel) : this(){
        id = parcel.readString()
        nombre = parcel.readString()
        categoria = parcel.readString()
        genero = parcel.readString()
        cantidadFotos = parcel.readString()
        nombreImagen = parcel.readInt()
    }

    constructor(nombre: String?, categoria: String?, genero: String?, cantidadFotos: String?, nombreImagen: Int)
    :this(){
        this.nombre = nombre
        this.categoria = categoria
        this.genero = genero
        this.cantidadFotos = cantidadFotos
        this.nombreImagen = nombreImagen
    }



    constructor(id: String?, nombre: String?, categoria: String?, genero: String?, cantidadFotos: String?, nombreImagen: Int?) :this(){
        this.id = id
        this.nombre = nombre
        this.categoria = cantidadFotos
        this.genero = genero
        this.cantidadFotos = cantidadFotos
        this.nombreImagen = nombreImagen
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeString(categoria)
        parcel.writeString(genero)
        parcel.writeString(cantidadFotos)
        parcel.writeValue(nombreImagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Planta> {
        override fun createFromParcel(parcel: Parcel): Planta {
            return Planta(parcel)
        }

        override fun newArray(size: Int): Array<Planta?> {
            return arrayOfNulls(size)
        }
    }


}