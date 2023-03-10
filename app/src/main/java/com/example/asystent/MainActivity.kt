package com.example.asystent

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.asystent.fragments.StronaGlownaFragment
import com.example.asystent.fragments.uczen.lista_wszystkich_uczniow.ListaUczniowiowFragment
import com.example.asystent.fragments.zajecia.lista_wszystkich_zajec.ListaZajecFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(StronaGlownaFragment(), "Strona główna")

        
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_glowna -> replaceFragment(StronaGlownaFragment(),it.title.toString())
                R.id.nav_zajecia -> replaceFragment(ListaZajecFragment(),it.title.toString())
                R.id.nav_uczniowie -> replaceFragment(ListaUczniowiowFragment(),it.title.toString())
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment, title:String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}