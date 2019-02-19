package co.edu.uniquindio.moviles.herbariohuq.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.activity.RegistroPlantaActivity
import co.edu.uniquindio.moviles.herbariohuq.util.AdaptadorPlantaEnviada
import co.edu.uniquindio.moviles.herbariohuq.util.ManagerFireBase
import co.edu.uniquindio.moviles.herbariohuq.vo.Planta
import kotlinx.android.synthetic.main.fragment_fragment_plantas_enviadas.*


/**
 * This class is a fragment that content the list of plants that collector send.
 * The Admin checking Later
 */

class SendPlantsFragment : Fragment(), AdaptadorPlantaEnviada.OnClickAdaptadorDePlantas {

    //Object to access FireBase
    lateinit var managerFireBase: ManagerFireBase

    //List for save plants
    val plantas: ArrayList<Planta> = ArrayList()

    //listener for events plant onClick
    private lateinit var listener: onPlantsSendSelected

    //adapter for rendering Plants on RecyclerView
    var adaptadorPlanta: AdaptadorPlantaEnviada? = null

    /**
     * Override lifecycle method for execute after create activity
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Init Plant Adapter
        adaptadorPlanta = AdaptadorPlantaEnviada(plantas, this)

        // Manager events for connect to Cloud DataBase
        managerFireBase = ManagerFireBase.managerInstance

        // Loads plants into the ArrayList
        addPlants()

        // Assign Layout Manager
        my_recycler_view_send_plantas.layoutManager = LinearLayoutManager(context)

        // Assign adapter
        my_recycler_view_send_plantas.adapter = context?.let { adaptadorPlanta }

        /**
         * Create Event for Navegation. This -> RegistroPlanta
         */
        add_planta.setOnClickListener({
            go_to_planta_register()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is onPlantsSendSelected) {
            listener = context
        } else {
            throw ClassCastException(context.toString() + " must implement OnRageComicSelected.")
        }
    }

    /**
     * Event onClickFloatingActionButton
     */
    private fun go_to_planta_register() {

        val intent = Intent(this.activity, RegistroPlantaActivity::class.java)
        startActivity(intent)

    }


    /**
     * Temporary method for addPlants to List And to Cloud DataBase
     */
    private fun addPlants() {

        // get Instance managerFireBase for get Functions
        managerFireBase = ManagerFireBase.managerInstance

        /**
         * Addings Plants Manually
         */
        val pepinilloDelDiablo = Planta(
                nombre = "Agave coloratha",
                categoria = "Cacti",
                genero = "Agave Colratha",
                cantidadFotos = "50 fotos",
                nombreImagen = R.drawable.agave
        )

        /**
         * Addings Plants Manually
         */
        val magnolia = Planta(

                nombre = "Magnolia",
                categoria = "Flor",
                genero = "Magnoliáceas",
                cantidadFotos = "20 fotos",
                nombreImagen = R.drawable.imagen_magnolia

        )

        //Adding plants to list plantas
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)
        plantas.add(magnolia)
        plantas.add(pepinilloDelDiablo)


        // Event to add Plants to cloud DataBase
        managerFireBase.insertar(magnolia)

    }

    /**
     * Lifecycle Method that execute when The View is created
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_plantas_enviadas, container, false)
    }

    /**
     * Method for get instance this fragment
     */
    fun newInstance(): SendPlantsFragment {
        return SendPlantsFragment()
    }


    /**
     * Interface that execute when once plant is selected
     */
    interface onPlantsSendSelected{

        fun onPlantsSendSelected (planta: Planta)

    }


    /**
     * Event Method that execute when plant is selected
     */
    override fun onClickPosition(planta: Planta) {
        //El listener de aquí es diferente
      listener.onPlantsSendSelected(planta)


    }
}
