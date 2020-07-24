package com.example.terminalagro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.terminalagro.API.RetrofitClient
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.ResponseBody
import kotlinx.android.synthetic.main.activity_daftar_toko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DaftarToko : AppCompatActivity() {

    companion object {
        private const val IMAGE_PICK_CODE = 999
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid:String = SharedPrefManager.getInstance(applicationContext).user.role.toString()
        if(uid.toInt() == 3){
            setContentView(R.layout.toko_dashboard)
        }
        else{
            setContentView(R.layout.activity_daftar_toko)

            iv_profil.setOnClickListener{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, IMAGE_PICK_CODE)
            }

            btn_daftar.setOnClickListener {
                val nama = et_nama.text.toString().trim()
                val alamat = et_alamat.text.toString().trim()
                val deskripsi = et_deskripsi.text.toString().trim()
                val id:Int = SharedPrefManager.getInstance(applicationContext).user.id

//            uploadImage()

                if (nama.isEmpty()) {
                    et_nama.error = "Email Wajib"
                    et_nama.requestFocus()
                    return@setOnClickListener
                }

                if (alamat.isEmpty()) {
                    et_alamat.error = "Password wajib"
                    et_alamat.requestFocus()
                    return@setOnClickListener
                }

                if (deskripsi.isEmpty()) {
                    et_deskripsi.error = "Username wajib"
                    et_deskripsi.requestFocus()
                    return@setOnClickListener
                }

                if (cb_setuju.isChecked == false) {
                    cb_setuju.error = "Harus disetujui"
                    cb_setuju.requestFocus()
                    return@setOnClickListener
                }
//                val toast = Toast.makeText(applicationContext, id, Toast.LENGTH_LONG)
//                toast.show()

                RetrofitClient.instance.tambahTokoRequest(nama, alamat, deskripsi, id)
                    .enqueue(object: Callback<ResponseBody> {
                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                            Toast.makeText(applicationContext, "Berhasil mendaftar", Toast.LENGTH_LONG).show()

                            isRegister()
                            val intent = Intent(applicationContext, Toko::class.java)

                            startActivity(intent)
                        }
                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(applicationContext, "Gagal mendaftar", Toast.LENGTH_LONG).show()
                        }

                    })
            }
        }

//            RetrofitClient.instance.tambahTokoRequest(nama, alamat, deskripsi)
//                .enqueue(object: Callback<ResponseBody> {
//                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                        Toast.makeText(applicationContext, "Gagal mendaftar", Toast.LENGTH_LONG).show()
//                    }
//
//                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                        Toast.makeText(applicationContext, "Berhasil mendaftar", Toast.LENGTH_LONG).show()
//                        val intent = Intent(applicationContext, Login::class.java)
//
//                        startActivity(intent)
//                    }
//
//                })
    }

    fun isRegister(){
        val gameSettings = getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE)
        val prefEditor = gameSettings.edit()
        prefEditor.putInt(SharedPrefManager.getInstance(applicationContext).KEY_ROLE, 4)
        prefEditor.apply()
    }

//
//    private fun uploadImage() {
//        imageData?: return
//        val request = object : VolleyFileUploadRequest(
//            Method.POST,
//            postURL,
//            Response.Listener {
//                println("response is: $it")
//            },
//            Response.ErrorListener {
//                println("error is: $it")
//            }
//        ) {
//            override fun getByteData(): MutableMap<String, FileDataPart> {
//                var params = HashMap<String, FileDataPart>()
//                params["imageFile"] = FileDataPart("image", imageData!!, "jpeg")
//                return params
//            }
//        }
//        Volley.newRequestQueue(this).add(request)
//    }
}

