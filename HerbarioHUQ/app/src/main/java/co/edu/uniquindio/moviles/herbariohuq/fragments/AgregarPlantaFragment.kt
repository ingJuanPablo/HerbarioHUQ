package co.edu.uniquindio.moviles.herbariohuq.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.edu.uniquindio.moviles.herbariohuq.R

class AgregarPlantaFragment : DialogFragment() {


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    public override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                     savedInstanceState: Bundle?): View? {

        dialog.setTitle(getString(R.string.agregar_planta))

        return inflater.inflate(R.layout.fragment_agregar_planta, container, false)
    }

    public override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

}// Required empty public constructor
