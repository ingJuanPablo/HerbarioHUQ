package co.edu.uniquindio.moviles.herbariohuq.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.GridView
import android.widget.ImageView
import co.edu.uniquindio.moviles.herbariohuq.R
import kotlinx.android.synthetic.main.activity_registro_planta.*

class RegistroPlantaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_planta)
        back_registro_planta_to_list_send_plantas.setOnClickListener({
            onBackPressed()
            Log.d("TAG", "Llega al evento")
        })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}
