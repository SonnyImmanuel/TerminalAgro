package com.example.terminalagro

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.terminalagro.API.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.terminalagro.Model.LoginResponse
import com.example.terminalagro.API.RetrofitClient
import kotlinx.android.synthetic.main.login_activity.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        btn_masuk.setOnClickListener{
            val email = et_nama.text.toString().trim()
            val password = et_password.text.toString().trim()

            if(email.isEmpty()){
                et_nama.error = "Email required"
                et_nama.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                et_password.error = "Password required"
                et_password.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(email, password)
            .enqueue(object: Callback<LoginResponse>{
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "gagal", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(!response.body()?.error!!){

                    SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                    Log.e("tes", response.body().toString())
//                    val progressDialog = ProgressDialog(this@Login,
//                        R.style.Theme_MaterialComponents_Light_Dialog)
//                    progressDialog.isIndeterminate = true
//                    progressDialog.setMessage("Loading...")
//                    progressDialog.show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)

                }else{
//                    val progressDialog = ProgressDialog( this@Login,
//                        R.style.Theme_MaterialComponents_Light_Dialog)
//                    progressDialog.isIndeterminate = true
//                    progressDialog.setMessage("Loading...")
//                    progressDialog.show()
                    Toast.makeText(applicationContext, "email atau password anda salah", Toast.LENGTH_LONG).show()
                }

            }
        })

        }

        btn_daftar.setOnClickListener{
            val int = Intent(applicationContext, Daftar::class.java)
            startActivity(int)
        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}