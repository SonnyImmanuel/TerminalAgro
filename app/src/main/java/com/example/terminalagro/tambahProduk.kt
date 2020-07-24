package com.example.terminalagro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.terminalagro.API.RetrofitClient
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.ResponseBody
import kotlinx.android.synthetic.main.activity_tambah_produk.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class tambahProduk : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_produk)
        var idToko:String =intent.getStringExtra("idToko")
        
        tambah_produk.setOnClickListener {
            val nama = nama_produk.text.toString().trim()
            val deskripsi = deskripsi_produk.text.toString().trim()
            val harga = harga_produk.text.toString().trim()
            val berat = berat_produk.text.toString().trim()
            val jumlah = jumlah_produk.text.toString().trim()
            val id:String = idToko

//            uploadImage()

            if (nama.isEmpty()) {
                nama_produk.error = "Nama Wajib"
                nama_produk.requestFocus()
                return@setOnClickListener
            }

            if (harga.isEmpty()) {
                harga_produk.error = "Harga wajib"
                harga_produk.requestFocus()
                return@setOnClickListener
            }

            if (deskripsi.isEmpty()) {
                deskripsi_produk.error = "Deskripsi wajib"
                deskripsi_produk.requestFocus()
                return@setOnClickListener
            }

            if (berat.isEmpty()) {
                berat_produk.error = "Berat wajib"
                deskripsi_produk.requestFocus()
                return@setOnClickListener
            }

            if (jumlah.isEmpty()) {
                jumlah_produk.error = "Jumlah wajib"
                deskripsi_produk.requestFocus()
                return@setOnClickListener
            }

//                val toast = Toast.makeText(applicationContext, id, Toast.LENGTH_LONG)
//                toast.show()

            RetrofitClient.instance.tambahProdukRequest(nama, harga.toInt(), deskripsi, berat.toInt(), jumlah.toInt(), idToko.toInt())
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        Toast.makeText(applicationContext, "Berhasil mendaftarkan produk", Toast.LENGTH_LONG).show()

                        val intent = Intent(applicationContext, Toko::class.java)

                        startActivity(intent)
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(applicationContext, "Gagal mendaftar", Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}
