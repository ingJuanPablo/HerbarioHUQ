package co.edu.uniquindio.moviles.herbariohuq.helper

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import co.edu.uniquindio.moviles.herbariohuq.R

class ConfirmacionRegistroHelper(context: Context):BaseDialogHelper(){

    // dialog view
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.body_confirmar_registro, null)
    }

    // notes edit text
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

    // edit text
    /*  val eText: TextInputEditText by lazy{
          dialogView.findViewById<TextInputEditText>(R.id.notes_etxt_view)
      }*/

    // done icon
     private val doneIcon: Button by lazy{
         dialogView.findViewById<Button>(R.id.btn_confirmar)
     }
    //  close icon
 //   private val closeIcon: ImageView by lazy {
  //      dialogView.findViewById<ImageView>(R.id.txtclose)
   // }


    //  closeIconClickListener with listener
 //   fun closeIconClickListener(func: (() -> Unit)? = null) =
 //           with(closeIcon) {
 //               setClickListenerToDialogIcon(func)
 //           }

    //  doneIconClickListener with listener
      fun doneIconClickListener(func: (() -> Unit)? = null) =
              with(doneIcon) {
                  setClickListenerToDialogIcon(func)
              }

    //  view click listener as extension function
    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
            setOnClickListener {
                func?.invoke()
                dialog?.dismiss()
            }

}