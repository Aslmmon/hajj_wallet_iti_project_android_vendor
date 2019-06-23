package com.example.finalproject.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.finalproject.Login.SignInActivity.SignInActivity
import com.example.finalproject.Model.SignOut.SignOutResponse
import com.example.finalproject.R
import com.example.finalproject.Retrofit.service
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var nav: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        nav = Navigation.findNavController(this, R.id.nav_host_fragment);
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val intent = intent
        val token = intent.getStringExtra("token")
        val code = intent.getStringExtra("code")
        val usr = intent.getStringExtra("firstName")
        val email = intent.getStringExtra("email")

        navView.getHeaderView(0).theUserName.text = usr
        nav_view.getHeaderView(0).emailUser.text = email


        // Log.i(TAG, "user ${user}")
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                nav!!.navigate(R.id.homeFragment)
            }
            R.id.nav_payments -> {
                nav!!.navigate(R.id.payments2)

            }

            R.id.nav_cash_out -> {
                nav!!.navigate(R.id.cashOutFragment)
            }
            R.id.signOut -> {
                val intent = intent
                val tokenTaken = intent.getStringExtra("token")
                service.retrofitService.LogOut(token = tokenTaken).enqueue(object : Callback<SignOutResponse>{
                    override fun onFailure(call: Call<SignOutResponse>, t: Throwable) {
                        Toast.makeText(this@HomeActivity,"Error ${t.message.toString()}",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<SignOutResponse>, response: Response<SignOutResponse>) {
                        Toast.makeText(this@HomeActivity,"${response.body()!!.detail}",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@HomeActivity,SignInActivity::class.java))
                        finish()
                    }

                })
            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
