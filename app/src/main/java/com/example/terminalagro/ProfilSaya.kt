package com.example.terminalagro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telecom.Call
import android.widget.Toast
import com.example.terminalagro.API.Api
import com.example.terminalagro.API.RetrofitClient
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.Pengguna
import kotlinx.android.synthetic.main.activity_daftar.*
import kotlinx.android.synthetic.main.activity_profil.*


class ProfilSaya : AppCompatActivity() {

    var path: String = null.toString()
    var parameter: String = null.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val nama = SharedPrefManager.getInstance(applicationContext).user.nama
        val username = SharedPrefManager.getInstance(applicationContext).user.username
        val email = SharedPrefManager.getInstance(applicationContext).user.email
        val no_telp = SharedPrefManager.getInstance(applicationContext).user.no_telp

        Toast.makeText(applicationContext, nama, Toast.LENGTH_LONG).show()

        et_nama.setText(nama)
        e_username.setText(username)
        e_email.setText(email)
        et_nomorTelepon.setText(no_telp)

        btn_ubahProfil.setOnClickListener{
            val int = Intent(applicationContext, EditProfil::class.java)
            startActivity(int)

        }
    }

    fun getProfil(){

    }
}
