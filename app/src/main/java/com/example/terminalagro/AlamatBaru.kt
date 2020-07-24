package com.example.terminalagro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.terminalagro.API.RetrofitClient
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.ResponseBody
import kotlinx.android.synthetic.main.activity_alamat_baru.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlamatBaru : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alamat_baru)

        btn_simpan.setOnClickListener{

            val nama = et_namaPenerima.text.toString().trim()
            val jalan = et_namaJalan.text.toString().trim()
            val no_hp = et_nomorPenerima.text.toString().trim()
            val kabupaten = et_kotaKabupaten.text.toString().trim()
            val kecamatan = et_kecamtan.text.toString().trim()
            val provinsi = et_provinsi.text.toString().trim()
            val id:Int = SharedPrefManager.getInstance(applicationContext).user.id

            if (nama.isEmpty()) {
                et_namaPenerima.error = "Nama penerima wajib diisi"
                et_namaPenerima.requestFocus()
                return@setOnClickListener
            }

            if (jalan.isEmpty()) {
                et_namaJalan.error = "Nama Jalan wajib diisi"
                et_namaJalan.requestFocus()
                return@setOnClickListener
            }

            if (no_hp.isEmpty()) {
                et_nomorPenerima.error = "Nomor HP penerima wajib diisi"
                et_nomorPenerima.requestFocus()
                return@setOnClickListener
            }

            if (kabupaten.isEmpty()) {
                et_kotaKabupaten.error = "Kabupaten wajib diisi"
                et_kotaKabupaten.requestFocus()
                return@setOnClickListener
            }

            if (kecamatan.isEmpty()) {
                et_kecamtan.error = "Kecamatan wajib diisi"
                et_kecamtan.requestFocus()
                return@setOnClickListener
            }

            if (provinsi.isEmpty()) {
                et_provinsi.error = "Provinsi wajib diisi"
                et_provinsi.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.tambahAlamatRequest(nama, no_hp, jalan, kecamatan, kabupaten, provinsi, id)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Toast.makeText(applicationContext, "Berhasil menambahkan alamat", Toast.LENGTH_LONG).show()

                        val intent = Intent(applicationContext, AlamatSaya ::class.java)

                        startActivity(intent)
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(applicationContext, "Gagal menambahkan alamat", Toast.LENGTH_LONG).show()
                    }

                })

        }

    }
}
