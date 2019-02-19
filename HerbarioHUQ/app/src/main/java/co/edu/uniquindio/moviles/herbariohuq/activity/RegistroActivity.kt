package co.edu.uniquindio.moviles.herbariohuq.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.helper.ConfirmacionRegistroHelper
import co.edu.uniquindio.moviles.herbariohuq.util.MyApplication
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class RegistroActivity : AppCompatActivity() {


    /**
     * Some vars for use later
     */
    private var imageView: ImageView? = null
    private var eventRegistro: Button? = null
    private var backImage: ImageView? = null
    private val GALLERY = 1
    private val CAMERA = 2

    /**
     * Variable que apunta al popup o dialogo buscar
     */
    private var confirmar_registro: AlertDialog? = null


    /**
     * Method execute when activity created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        imageView = findViewById<View>(R.id.add_perfil_foto) as ImageView


        imageView!!.setOnClickListener({
            go_to_camera()
        })

        backImage = findViewById<View>(R.id.back_registro_to_main) as ImageView

        backImage!!.setOnClickListener({
            onBackPressed()
        })

        eventRegistro = findViewById<View>(R.id.btn_registrar) as Button

        eventRegistro!!.setOnClickListener({
            go_to_registro_event()
        })
    }

    /**
     * Method execute when user click on back
     */
    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    /**
     * Method execute when user click on register button
     */
    private fun go_to_registro_event() {

        showDialog()

    }

    /**
     * Method that execute when user need to camera
     */
    private fun go_to_camera() {

        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Selecciona un medio")
        val pictureDialogLogItems = arrayOf("Seleccionar foto desde galeria", "Capturar foto desde la camara")
        pictureDialog.setItems(pictureDialogLogItems) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallery()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    /**
     * Method that execute when the user select Photo from gallery
     */
    fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    /**
     * * Method that execute when the user select Photo from camera
     */
    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    /**
     * Lifecycle Method that execute when the activity send results
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GALLERY){
            if(data != null){
                val contentURI = data!!.data

                try {

                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImage(bitmap)
                    Toast.makeText(this@RegistroActivity, "Imagen guardada!", Toast.LENGTH_SHORT).show()
                    imageView!!.setImageBitmap(bitmap)

                }catch (e: IOException){
                    e.printStackTrace()
                    Toast.makeText(this@RegistroActivity, "Fallo!", Toast.LENGTH_SHORT)
                }
            }
        }else if(requestCode == CAMERA){
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            imageView!!.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            Toast.makeText(this@RegistroActivity, "Imagen guardada!", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Method that execute when the user choosed photo and the system saved
     */
    fun saveImage(myBitmap: Bitmap):String {

        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        Log.d("fee",wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists())
        {

            wallpaperDirectory.mkdirs()
        }

        try
        {
            Log.d("heel",wallpaperDirectory.toString())
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                    arrayOf(f.getPath()),
                    arrayOf("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
    }



    /**
     * Confirmar registro Dialog
     */
    inline fun Activity.showConfiramrAlertDialog(func: ConfirmacionRegistroHelper.() -> Unit): AlertDialog =
            ConfirmacionRegistroHelper(this).apply {
                func()
            }.create()

    fun showDialog(){

        if (confirmar_registro == null)

            confirmar_registro = showConfiramrAlertDialog {

                cancelable = false
                doneIconClickListener {
                    Log.d("Tag", "Sirve el onclick")
                    go_to_login()
                }
            }
        confirmar_registro?.show()
    }

    //evento para navegar a la ventana principal
    private fun go_to_login() {


        val intent = Intent(this, MainActivity::class.java)

        var mApp = MyApplication()
        mApp.statusUser = "YesLog"
        intent.putExtra("status", "YesLog")
        startActivity(intent)
        this.finish()

    }


}
