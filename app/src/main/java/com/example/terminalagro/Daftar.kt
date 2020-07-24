package com.example.terminalagro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.terminalagro.API.RetrofitClient
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.ResponseBody
import kotlinx.android.synthetic.main.activity_daftar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Daftar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        btn_daftar.setOnClickListener{
            val username = et_username.text.toString().trim()
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            val nama = et_name.text.toString().trim()
            val no_telp = et_no.text.toString().trim()

            if(email.isEmpty()){
                et_email.error = "Email Wajib"
                et_email.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                et_password.error = "Password wajib"
                et_password.requestFocus()
                return@setOnClickListener
            }

            if(username.isEmpty()){
                et_username.error = "Username wajib"
                et_username.requestFocus()
                return@setOnClickListener
            }

            if(nama.isEmpty()){
                et_name.error = "Username wajib"
                et_name.requestFocus()
                return@setOnClickListener
            }

            if(no_telp.isEmpty()){
                et_no.error = "Username wajib"
                et_no.requestFocus()
                return@setOnClickListener
            }

            if(checkBox.isChecked == false){
                checkBox.error = "Harus disetujui"
                checkBox.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.registerRequest(username, email, password, nama, no_telp)
            .enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, "Gagal mendaftar", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(applicationContext, "Berhasil mendaftar", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, Login::class.java)

                startActivity(intent)
            }

        })

        }
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }
    }
}
