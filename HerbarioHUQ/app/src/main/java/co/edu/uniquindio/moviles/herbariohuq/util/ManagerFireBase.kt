package co.edu.uniquindio.moviles.herbariohuq.util

import android.util.Log
import co.edu.uniquindio.moviles.herbariohuq.vo.Planta
import com.google.firebase.database.*

/**
 * Created by TecnoSystem on 28/01/2019.
 */


class ManagerFireBase private constructor() {

    private var database: FirebaseDatabase? = null
    private var dataRef: DatabaseReference? = null
    public lateinit var listener: onActualizarAdaptador

    init {
        database = FirebaseDatabase.getInstance()
        dataRef = database!!.reference
    }

    companion object {
        private var instancia: ManagerFireBase? = null
        val managerInstance: ManagerFireBase
            get() {
                if (instancia == null) {
                    instancia = ManagerFireBase()
                }
                return instancia!!
            }
    }

    fun insertar(planta: Planta) {
        dataRef!!.push().setValue(planta)
    }

    fun insertarConLlave(planta: Planta) {
        dataRef!!.child(planta.id!!).setValue(planta)
    }

    fun escucharEventoFireBase(){
        dataRef!!.addChildEventListener(object : ChildEventListener{

            override fun onCancelled(p0: DatabaseError?) {
                Log.v("ManagerFire", "onCancelled")
            }

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildMoved")
            }

            override fun onChildChanged(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChilChanged")
            }

            override fun onChildAdded(p0: DataSnapshot?, p1: String?) {
                Log.v("ManagerFire", "onChildAdded")
                val planta = p0!!.getValue(Planta::class.java)!!
                planta.id = p0!!.key
                listener.actualizarAdaptador(planta)

            }

            override fun onChildRemoved(p0: DataSnapshot?) {
                Log.v("ManagerFire", "onChildRemoved")
            }

        })
    }

    fun obtenerPlantas(){
        dataRef!!.child("LXKSejKUBQRIiAsU9fU").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {

               Log.d("Data", p0.toString())


            }

        })
    }


    fun eliminar(id: String){
        dataRef!!.child(id).removeValue()
    }


    interface onActualizarAdaptador {
        fun actualizarAdaptador(planta: Planta)
    }

}