package co.edu.uniquindio.moviles.herbariohuq.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.helper.RecuperarContrasenaHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    /**
     * Variable que apunta al popup o dialogo buscar
     */
    private var recuperarContrasena: AlertDialog? = null

    /**
     * LifyCycle Method that execute when the Activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /**
         * Method to execute when happens event register button
         */
        go_to_registro_from_login.setOnClickListener({
            go_to_registro()
        })

        //Asignacion del evento onClick a la imagen del layout
        back_login_to_main.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        /**
         * Method to execute when happens event login button
         */
        btn_ingresar.setOnClickListener ( View.OnClickListener {
            go_to_login()
        } )

        /**
         * Method to execute when happens event lost password text
         */
        txtRecuperarContrasena.setOnClickListener (
                {
                    go_to_pop_up_clave();
                }

        )
    }

    /**
     * Event Method
     */
    private fun go_to_registro() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }

    /**
     * Event Method to execute pop up
     */
    private fun go_to_pop_up_clave() {

        showDialog()

    }

    /**
     * Event Method
     */
    private fun go_to_login() {

        /**
         * status is if user logged or not
         */
        val intent = Intent(this, MainActivity::class.java)

        intent.putExtra("status", "YesLog")
        startActivity(intent)
        this.finish()

    }


    /**
     * Metodo para devolverse a la ventana principal
     */
    override fun onBackPressed() {
        super.onBackPressed()


        this.finish()

    }


    /**
     * recuperar contrasena Dialog
     */
    inline fun Activity.showRecuperarAlertDialog(func: RecuperarContrasenaHelper.() -> Unit): AlertDialog =
            RecuperarContrasenaHelper(this).apply {
                func()
            }.create()
    fun showDialog(){

        if (recuperarContrasena == null)

            recuperarContrasena = showRecuperarAlertDialog {

                cancelable = false

                closeIconClickListener {
                    Log.d("Tag", "Sirve el onclick")
                }
            }
        recuperarContrasena?.show()
    }
}
