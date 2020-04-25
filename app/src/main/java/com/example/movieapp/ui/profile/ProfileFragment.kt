package com.example.movieapp.ui.profile

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.model.data.AccountInfo
import com.example.movieapp.model.network.RetrofitService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


class ProfileFragment : Fragment() {

    private lateinit var pref: SharedPreferences
    private lateinit var profileName: TextView
    private lateinit var profileUsername: TextView
    private var sessionId: String? = null

    private val job = Job()
    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pref = activity?.getSharedPreferences("prefSessionId", MODE_PRIVATE)!!
        sessionId = pref.getString("session_id", "empty")
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        getAccountDetails()
    }

    private fun bindView(view: View) = with(view) {
        profileName = view.findViewById(R.id.profileNmae)
        profileUsername = view.findViewById(R.id.profileUsername)
    }

    private fun getAccountDetails() {
        uiScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val response = sessionId?.let { RetrofitService.getMovieApi().getAccountId(it) }
                    if (response?.isSuccessful!!) {
                        val result = response.body()
                        if (result != null) {
                            Log.d("Done", "Successfully got Account id")
                            profileName.text = "Name: " + result.name
                            profileUsername.text = "Username: " + result?.username
                        } else {
                            Log.e("Error", "Cannot get Account id")
                        }
                    } else {
                        Log.e("Error", "Cannot get Account id")
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", "Cannot get Account id")
            }
        }
    }
}