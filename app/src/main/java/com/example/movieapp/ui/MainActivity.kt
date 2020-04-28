package com.example.movieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
=======
import com.example.movieapp.R
import com.example.movieapp.login.LoginData
import com.example.movieapp.login.TokenResponse
import com.example.movieapp.model.network.MovieApi
import com.example.movieapp.model.network.RetrofitService
import com.example.movieapp.ui.favourites.RequestSession
import com.example.movieapp.ui.favourites.SessionId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
>>>>>>> master


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
=======
        setContentView(R.layout.registration_page)

        val name: EditText? = findViewById(R.id.editText1)
        val surname: EditText? = findViewById(R.id.editText2)
        login = findViewById(R.id.editText3)
        password = findViewById(R.id.editText4)
        buttonReg = findViewById(R.id.buttonReg)

        var myPrefs: SharedPreferences

        buttonReg.setOnClickListener(View.OnClickListener {
            if(name?.text.toString().isNotEmpty()
            && surname?.text.toString().isNotEmpty()
            && login?.text.toString().isNotEmpty()
            && password?.text.toString().isNotEmpty()){

                myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                val editor: SharedPreferences.Editor = myPrefs.edit()
                editor.putString("name", name?.text.toString())
                editor.putString("surname", surname?.text.toString())
                editor.putString("username", login?.text.toString())
                editor.apply()
                getToken()

            }
            else Toast.makeText(applicationContext,"Please fill each field!" ,Toast.LENGTH_SHORT).show();
        })

    }

    private fun getToken(){
        try {

            val api: MovieApi? = RetrofitService.getClient()?.create(
                MovieApi::class.java)
            api?.getRequestToken("88f972ac2b5f07d969202c8ffbaaaffa")
                ?.enqueue(object : Callback<TokenResponse?> {
                    override fun onFailure(call: Call<TokenResponse?>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG)
                    }

                    override fun onResponse(
                        call: Call<TokenResponse?>,
                        response: Response<TokenResponse?>
                    ) {
                        if (response.body()?.success == true) {
                            requestedToken = response.body()?.request_token
                            Toast.makeText(this@MainActivity, "Accessed ", Toast.LENGTH_LONG).show()
                            val intent = Intent( baseContext, SecondActivity::class.java)
                            startActivity(intent)
                            getSessionId(requestedToken)
                            login()
                        }

                    }

                })
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)
>>>>>>> master

        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.navView)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.favouritesFragment,
                R.id.moviesFragment,
                R.id.profileFragment,
                R.id.detailsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
<<<<<<< HEAD
=======
    fun login() {
        try {

            val api: MovieApi? = RetrofitService.getClient()?.create(
                MovieApi::class.java)
            api?.login(LoginData(login.text.toString(), password.text.toString(), requestedToken))
                ?.enqueue(object : Callback<TokenResponse?> {
                    override fun onFailure(call: Call<TokenResponse?>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Incorrect data", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<TokenResponse?>,
                        response: Response<TokenResponse?>
                    ) {
                        if (response.body()?.success == true) {
                            Log.d("login", "good")
                            val intent = Intent( baseContext, SecondActivity::class.java)
                            startActivity(intent)
                            getSessionId(requestedToken)


                        }
                    }

                })
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)

        }
    }
    fun getSessionId(token: String?) {
        Log.d("start", token)
        try {

            val api: MovieApi? = RetrofitService.getClient()?.create(
                MovieApi::class.java)
            api?.getSession(SessionId(token))
                ?.enqueue(object : Callback<RequestSession?> {
                    override fun onFailure(call: Call<RequestSession?>, t: Throwable) {
                        Log.d("start", "failure occurred")

                    }

                    override fun onResponse(
                        call: Call<RequestSession?>,
                        response: Response<RequestSession?>
                    ) {
                        if (response.body()?.success == true) {
                            Toast.makeText(
                                this@MainActivity,
                                "getSessionIdResponse",
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("start", response.body()?.session_id)
                            val myPrefs: SharedPreferences = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                            val edt = myPrefs.edit()
                            edt.putString("sessionID", response.body()?.session_id)
                            edt.apply()

                        } else
                            Log.d("start", response.body().toString())

                    }

                })
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
>>>>>>> master

    override fun onBackPressed() {
        super.onBackPressed()
        return
    }
}