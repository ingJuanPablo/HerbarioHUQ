package co.edu.uniquindio.moviles.herbariohuq.util

import android.content.ContentResolver
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.fragments.PlantsFragment
import co.edu.uniquindio.moviles.herbariohuq.vo.Planta
import kotlinx.android.synthetic.main.preview_plant.view.*

/**
 * Created by Juan Pablo Rodriguez on 17/09/2018.
 */

class AdaptadorPlanta(val plantas: ArrayList<Planta>, fragment: Fragment) : RecyclerView.Adapter<AdaptadorPlanta.ViewHolder>() {


    lateinit var listener: OnClickAdaptadorDePlantas

    init {
        listener = fragment as OnClickAdaptadorDePlantas
    }

    /**
     * Comunica esto con el fragmento
     */
    interface OnClickAdaptadorDePlantas {
        fun onClickPosition(planta: Planta)
    }

    // Traer el numero de plantas en la lista
    override fun getItemCount(): Int {
        return plantas.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(p0.context).inflate(R.layout.preview_plant, p0, false)

        return ViewHolder(v)
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(p0: ViewHolder, position: Int) {


        p0.bind(plantas[position])

        //p0.itemView.setOnClickListener { listener.onPlantsSelected() }

        //(p0 as ViewHolder).bind(items.get(position))

    }


inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    // Holds the TextView that will add each animal to

    val categoria: TextView = itemView.txtCategoria
    val nombre: TextView = itemView.txtNombre
    val genero: TextView = itemView.txtGenero
    val cantidadDeFotos: TextView = itemView.txtFotos
    val imagen: ImageView = itemView.imagen_planta


    init {
        itemView.setOnClickListener(this)
    }

    fun bind(planta: Planta) = with(itemView) {

        categoria.text = planta.categoria
        nombre.text = planta.nombre
        genero.text = planta.genero
        cantidadDeFotos.text = planta.cantidadFotos
        imagen.setImageResource(planta.nombreImagen!!)

    }


    /**
     * Metodo que se ejecuta al seleccionar una planta
     */
    override fun onClick(v: View?) {
      listener.onClickPosition(plantas[adapterPosition])

    }


}
}


