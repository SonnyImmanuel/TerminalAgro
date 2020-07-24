package com.example.terminalagro

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.terminalagro.API.RetrofitClient
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.ResponseBody
import kotlinx.android.synthetic.main.activity_edit_profil.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class EditProfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)

        val nama = SharedPrefManager.getInstance(applicationContext).user.nama
        val username = SharedPrefManager.getInstance(applicationContext).user.username
        val email = SharedPrefManager.getInstance(applicationContext).user.email
        val no_telp = SharedPrefManager.getInstance(applicationContext).user.no_telp
        val password = SharedPrefManager.getInstance(applicationContext).user.password

        Toast.makeText(applicationContext, nama, Toast.LENGTH_LONG).show()

        et_nama.setText(nama)
        et_emails.setText(email)
        et_usernames.setText(username)
        et_nomorTelepon.setText(no_telp)
        et_password.setText(password)


        btn_daftar.setOnClickListener {
            val username = et_usernames.text.toString().trim()
            val email = et_emails.text.toString().trim()
            val password = et_password.text.toString().trim()
            val nama = et_nama.text.toString().trim()
            val no_telp = et_nomorTelepon.text.toString().trim()
            val id:Int = SharedPrefManager.getInstance(applicationContext).user.id

            if (email.isEmpty()) {
                et_emails.error = "Email Wajib"
                et_emails.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                et_password.error = "Password wajib"
                et_password.requestFocus()
                return@setOnClickListener
            }

            if (username.isEmpty()) {
                et_usernames.error = "Username wajib"
                et_usernames.requestFocus()
                return@setOnClickListener
            }

            if (nama.isEmpty()) {
                et_nama.error = "Nama wajib"
                et_nama.requestFocus()
                return@setOnClickListener
            }

            if (no_telp.isEmpty()) {
                et_nomorTelepon.error = "Nomor telepon wajib"
                et_nomorTelepon.requestFocus()
                return@setOnClickListener
            }


            RetrofitClient.instance.editProfilRequest(id, username, email, password, nama, no_telp)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(applicationContext, "Gagal mendaftar", Toast.LENGTH_LONG)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {

                        val gameSettings = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
                        val prefEditor = gameSettings.edit()
                        prefEditor.putString(SharedPrefManager.getInstance(applicationContext).KEY_NAMA, nama)
                        prefEditor.putString(SharedPrefManager.getInstance(applicationContext).KEY_EMAIL, email)
                        prefEditor.putString(SharedPrefManager.getInstance(applicationContext).KEY_PASSWORD, password)
                        prefEditor.putString(SharedPrefManager.getInstance(applicationContext).KEY_NO_TELP, no_telp)
                        prefEditor.putString(SharedPrefManager.getInstance(applicationContext).KEY_USERNAME, username)
                        prefEditor.apply()


                        Toast.makeText(applicationContext, "Berhasil Mengubah data", Toast.LENGTH_LONG)
                            .show()
                        val intent = Intent(applicationContext, ProfilSaya::class.java)

                        startActivity(intent)
                    }

                })


        }
    }

}
