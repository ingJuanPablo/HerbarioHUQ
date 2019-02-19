package co.edu.uniquindio.moviles.herbariohuq.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.util.AdaptadorPlanta
import co.edu.uniquindio.moviles.herbariohuq.util.ManagerFireBase
import co.edu.uniquindio.moviles.herbariohuq.vo.Planta
import kotlinx.android.synthetic.main.fragment_plants.*

class PlantsFragment: Fragment(), AdaptadorPlanta.OnClickAdaptadorDePlantas, ManagerFireBase.onActualizarAdaptador {




    lateinit var managerFireBase: ManagerFireBase

    val plantas: ArrayList<Planta> = ArrayList()

    private var listener: onPlantsSelected? = null

    var adaptadorPlanta: AdaptadorPlanta? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adaptadorPlanta = AdaptadorPlanta(plantas, this)


        managerFireBase = ManagerFireBase.managerInstance
        managerFireBase.listener = this


        // Loads plants into the ArrayList
        addPlants()

        my_recycler_view_plantas.layoutManager = LinearLayoutManager(context)

        my_recycler_view_plantas.adapter = context?.let { adaptadorPlanta }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    //override fun actualizarAdaptador(planta: Planta) {


    //    plantas.add(0, planta)
    //    adaptadorPlanta

    //}




    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is onPlantsSelected) {
            listener = context as onPlantsSelected


        } else {
            throw ClassCastException(context.toString() + " must implement OnRageComicSelected.")
        }
    }

    private fun addPlants() {


        managerFireBase = ManagerFireBase.managerInstance



        val pepinilloDelDiablo = Planta(
                nombre = "Pepinillo del diablo",
                categoria = "Ecballium",
                genero = "Planta venenosa",
                cantidadFotos = "12 fotos",
                nombreImagen = R.drawable.imagen_pepinillo_diablo

        )

        //plantas.add(pepinilloDelDiablo)
        val magnolia = Planta(

                nombre = "Magnolia",
                categoria = "Flor",
                genero = "Magnoliáceas",
                cantidadFotos = "20 fotos",
                nombreImagen = R.drawable.imagen_magnolia

        )
       /* plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo) */

        managerFireBase.escucharEventoFireBase()

        managerFireBase.insertar(magnolia)
        managerFireBase.obtenerPlantas()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plants, container, false)
    }

    fun newInstance(): PlantsFragment {
        return PlantsFragment()
    }



    interface onPlantsSelected{

        fun onPlantsSelected (planta: Planta)

    }


    override fun onClickPosition(planta: Planta) {
       //El listener de aquí es diferente
        listener!!.onPlantsSelected(planta)


    }


    override fun actualizarAdaptador(planta: Planta) {

        plantas.add(0, planta)
        adaptadorPlanta!!.notifyItemInserted(0)

    }

}
