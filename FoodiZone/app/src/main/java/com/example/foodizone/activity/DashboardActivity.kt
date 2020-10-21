package com.example.foodizone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.eatit.fragment.Dashboard_fragment

import com.example.eatit.fragment.FrequentlyAskFragment
import com.example.eatit.fragment.ProfileFragment
import com.example.foodizone.R
import com.example.foodizone.fragment.FavouritesFragment
import com.example.foodizone.fragment.LogoutFragment
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {

    lateinit var drawerLayout : DrawerLayout
    lateinit var coordinateLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView : NavigationView

   // lateinit var navigationtxtHeader : TextView

    var previousMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
       /*
        //get data from intent


        //textview
        val resultTv = findViewById<TextView>(R.id.tv_dashboard)
        //setText

    }*/
        /*val intent = intent
        val phone = intent.getStringExtra("Phone")
        val pswrd = intent.getStringExtra("Password")*/

        drawerLayout = findViewById(R.id.drawerlayout)
        coordinateLayout = findViewById(R.id.coordinatorlayouttwo)
        toolbar = findViewById(R.id.toolbartwo)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationViewone)
        //navigationtxtHeader = findViewById<TextView>(R.id.navigation_txt_header)


        setUpToolbar()

        openDashboard()



        val actionBarDrawerToggle = ActionBarDrawerToggle(this@DashboardActivity,
            drawerLayout,
            R.string.open_Drawer,
            R.string.close_Drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {


           // navigationtxtHeader.text = "Phone: "+phone+"\nPassword: "+pswrd
            if(previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.home -> {
                    openDashboard()
                    drawerLayout.closeDrawers()
                }
                R.id.myprofile -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        ProfileFragment()
                    )
                        .commit()

                    supportActionBar?.title ="My Profile"

                    drawerLayout.closeDrawers()
                }
                R.id.favourites -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FavouritesFragment()
                    )
                        .commit()

                    supportActionBar?.title ="Favorite Restaurants"
                    drawerLayout.closeDrawers()
                }
                R.id.faqs -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        FrequentlyAskFragment()
                    )
                        .commit()

                    supportActionBar?.title ="Frequently Asked Questions"
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.frameLayout,
                        LogoutFragment()
                    )
                        .commit()

                    supportActionBar?.title ="Logout"
                    drawerLayout.closeDrawers()
                }

            }

            return@setNavigationItemSelectedListener true
        }
    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = "All Restaurants"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(id == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){

        val fragment = Dashboard_fragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
        supportActionBar?.title = "All Restaurants"
        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frameLayout)

        when(frag){
            !is Dashboard_fragment -> openDashboard()

            else -> super.onBackPressed()
        }
    }
}




