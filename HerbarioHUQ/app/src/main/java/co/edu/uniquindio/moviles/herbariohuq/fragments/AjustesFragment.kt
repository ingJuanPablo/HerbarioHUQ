package co.edu.uniquindio.moviles.herbariohuq.fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar

import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.helper.BuscarDialogHelper
import kotlinx.android.synthetic.main.content_informacion.*
import kotlinx.android.synthetic.main.toolbar.*


class AjustesFragment : Fragment() {

    /**
     * Variable que apunta al popup o dialogo buscar
     */
    private var buscarDialog: AlertDialog? = null

    open var cancelable: Boolean = true

    public lateinit var listener: onClickDialog


    companion object {
        private var instancia: AjustesFragment? = null
        val managerInstance: AjustesFragment
            get() {
                if (instancia == null) {
                    instancia = AjustesFragment()
                }
                return instancia!!
            }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun go_to_change_idiom() {
        listener.clickDialog()
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ajustes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        change_idiom.setOnClickListener({
            go_to_change_idiom()
        })

    }

    interface onClickDialog{
        fun clickDialog()
    }

}// Required empty public constructor
