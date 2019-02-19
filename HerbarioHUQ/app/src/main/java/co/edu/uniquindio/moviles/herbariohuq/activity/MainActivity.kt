package co.edu.uniquindio.moviles.herbariohuq.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import co.edu.uniquindio.moviles.herbariohuq.R
import co.edu.uniquindio.moviles.herbariohuq.fragments.AjustesFragment
import co.edu.uniquindio.moviles.herbariohuq.fragments.PlantaFragment
import co.edu.uniquindio.moviles.herbariohuq.fragments.PlantsFragment
import co.edu.uniquindio.moviles.herbariohuq.fragments.SendPlantsFragment
import co.edu.uniquindio.moviles.herbariohuq.helper.BuscarDialogHelper
import co.edu.uniquindio.moviles.herbariohuq.vo.Planta
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity :  AppCompatActivity(), PlantsFragment.onPlantsSelected, NavigationView.OnNavigationItemSelectedListener, SendPlantsFragment.onPlantsSendSelected, AjustesFragment.onClickDialog {



    /**
     * State user status
     */
    var status = "NoLog"

    /**
     * Variable que me permite darle un manejo a los fragmentos
     */
    val manager = supportFragmentManager

    lateinit var ajustes: AjustesFragment

    /**
     * Variable que apunta al popup o dialogo buscar
     */
    private var buscarDialog: AlertDialog? = null


    /**
     * Metodo que se ejecuta cuando se crea la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ajustes = AjustesFragment.managerInstance
        ajustes.listener = this

        val bundle = intent.extras

        if(bundle != null) {

            status = bundle.getString("status")

        }
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.open_drawer_content_desc, R.string.close_drawer_content_desc)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)



        //Variable que permite asignarle el menu navigation a una variable
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        //se le setea un listener al bottomNavigation
        if(status.equals( "NoLog")) {



            //Llamado al metodo que crea los fragmentos

            toolbar.title = getString(R.string.title_toolbar)
            toolbar.subtitle = getString(R.string.subtitle_toolbar)
            createFragment(PlantsFragment())


            /**
             * Events the bottom tabs
             */
            bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {


                    R.id.navigation_plants -> {

                        toolbar.title = getString(R.string.title_toolbar)
                        toolbar.subtitle = getString(R.string.subtitle_toolbar)
                        createFragment(PlantsFragment())
                        true

                    }
                    R.id.navigation_ajustes -> {

                        toolbar.title = getString(R.string.Ajustes)
                        toolbar.subtitle = " "
                        createFragment(AjustesFragment())
                        true
                    }
                    else -> {
                        false

                    }

                }


            }
        }else{


            //Llamado al metodo que crea los fragmentos

            toolbar.title = getString(R.string.title_toolbar)
            toolbar.subtitle = getString(R.string.subtitle_log)
            createFragment(SendPlantsFragment())

            bottomNavigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {


                    R.id.navigation_plants -> {

                        toolbar.title = getString(R.string.title_toolbar)
                        toolbar.subtitle = getString(R.string.subtitle_log)
                        createFragment(SendPlantsFragment())
                        true

                    }
                    R.id.navigation_ajustes -> {

                        toolbar.title = getString(R.string.Ajustes)
                        toolbar.subtitle = " "
                        createFragment(AjustesFragment())
                        true
                    }
                    else -> {
                        false

                    }

                }


            }

        }
        //get the event that change some items the Menu
        setVisibleItemsMenu()

    }


    /**
     * Method where changed items the menu depends the status
     */
    fun setVisibleItemsMenu(){
        if(status.equals("NoLog")){
            nav_view.menu.findItem(R.id.nav_inicio_sesion).setVisible(true)
            nav_view.menu.findItem(R.id.nav_registro).setVisible(true)
            nav_view.menu.findItem(R.id.nav_log_out).setVisible(false)
            nav_view.menu.findItem(R.id.nav_mis_plantas).setVisible(false)
            nav_view.menu.findItem(R.id.nav_todas_las_plantas).setVisible(false)
        }else{
            nav_view.menu.findItem(R.id.nav_inicio_sesion).setVisible(false)
            nav_view.menu.findItem(R.id.nav_registro).setVisible(false)
            nav_view.menu.findItem(R.id.nav_log_out).setVisible(true)
            nav_view.menu.findItem(R.id.nav_mis_plantas).setVisible(true)
            nav_view.menu.findItem(R.id.nav_todas_las_plantas).setVisible(true)
        }
    }

    /**
     * Metodo para devolverse del menu
     */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    /**
     * Se crea el menu superior izquierdo donde estará la opcion de busqueda
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top_left, menu)

        return true
    }

    /**
     * In this method execute item buscar of the Top Menu
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId){
            R.id.option_search -> {

                showDialog()

            }
        }
    return super.onOptionsItemSelected(item);
    }


    /**
     * Metodo que permite crear el fragmento ajustes
     */
    fun createFragment(fragment: Fragment) {

        val transactionAjustes = manager.beginTransaction()
        transactionAjustes.replace(R.id.contenido, fragment)
        transactionAjustes.commit()

    }

    /**
     * Buscar Dialog
     */
    inline fun Activity.showBuscarAlertDialog(func: BuscarDialogHelper.() -> Unit): AlertDialog =
            BuscarDialogHelper(this).apply {
                func()
            }.create()

    fun showDialog(){

        if (buscarDialog == null)

            buscarDialog = showBuscarAlertDialog {

                cancelable = false

                closeIconClickListener {
                    Log.d("Tag", "Sirve el onclick")
                }
            }
        buscarDialog?.show()
    }

    /**
     * Metodo sobreescrito de la interfaz onPlantsSelected
     */
    override fun onPlantsSelected(planta: Planta) {



        toolbar.title = planta.nombre
        toolbar.subtitle = planta.cantidadFotos
        val fragmentoDetalle = PlantaFragment()


        val transactionAjustes = manager.beginTransaction()
        transactionAjustes.replace(R.id.contenido, fragmentoDetalle)
        transactionAjustes.commit()
        fragmentoDetalle.darDetalle(planta)

    }

    /**
     * Method override from interface onPlantSendSelcted
     */
    override fun onPlantsSendSelected(planta: Planta){
        toolbar.title = planta.nombre
        toolbar.subtitle = planta.cantidadFotos
        val fragmentoDetalle = PlantaFragment()


        val transactionAjustes = manager.beginTransaction()
        transactionAjustes.replace(R.id.contenido, fragmentoDetalle)
        transactionAjustes.commit()
        fragmentoDetalle.darDetalle(planta)
    }

    /**
     * Escuchador del navigation Drawer
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_inicio_sesion -> {

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)


            }
            R.id.nav_registro -> {

                val intent = Intent(this, RegistroActivity::class.java)
                startActivity(intent)

            }
            R.id.nav_log_out ->{

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()

            }

            R.id.nav_mis_plantas ->{


                toolbar.title = getString(R.string.title_toolbar)
                toolbar.subtitle = getString(R.string.subtitle_log)
                createFragment(SendPlantsFragment())
                true


            }

            R.id.nav_todas_las_plantas ->{

                toolbar.title = getString(R.string.title_toolbar)
                toolbar.subtitle = getString(R.string.subtitle_toolbar)
                createFragment(PlantsFragment())
                true



            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun clickDialog() {

        showDialog()

    }

}
