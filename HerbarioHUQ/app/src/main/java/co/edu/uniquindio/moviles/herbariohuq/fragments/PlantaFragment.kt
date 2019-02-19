package co.edu.uniquindio.moviles.herbariohuq.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.vo.Planta
import kotlinx.android.synthetic.main.fragment_planta.*


class PlantaFragment : Fragment() {

    /**
     * Variable para detalle planta
     */
    var planta: Planta? = null
    val imagen_planta: ImageView? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imagen_fragment_planta.setImageResource(planta!!.nombreImagen!!)
        edit_genero.text = planta!!.genero
        edit_especie.text = planta!!.categoria
        //val imagen_planta = fin
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_planta, container, false)

        return view
    }



    override fun onAttach(context: Context?) {
        super.onAttach(context)



    }

    fun darDetalle(planta: Planta){
        this.planta = planta

    }


}// Required empty public constructor
