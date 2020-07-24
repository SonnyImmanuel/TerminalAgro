package com.example.terminalagro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Adapters.ProdukAdapter
import com.example.terminalagro.Model.Produk
import kotlinx.android.synthetic.main.tambah_toko_dashboard.*
import kotlinx.android.synthetic.main.toko_dashboard.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class Toko : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uid: Int = SharedPrefManager.getInstance(applicationContext).user.role

        if (uid == 3) {
            setContentView(R.layout.toko_dashboard)
            var cat:String = SharedPrefManager.getInstance(applicationContext).user.id.toString()

            var urls="http://192.168.43.24/Terminal/getStore.php?id="+cat

            val mQueue = Volley.newRequestQueue(this)
            val request =
                JsonObjectRequest(
                    Request.Method.GET, urls, null,
                    Response.Listener { response ->
                        try {
                            val toko: JSONObject = response.getJSONObject("toko")
//                            val jsonArray = response.getJSONArray("toko")
//                            Log.e("toko", jsonArray.toString())
//                            for (i in 0 until jsonArray.length()) {
//                                var toko = JSONObject()
//                                toko = jsonArray.getJSONObject(i)
//
                                Log.e("toko", toko.getString("nama"))
                                val firstName = toko.getString("nama")
                                val age = toko.getString("alamat")
                                val mail = toko.getString("deskripsi")
                                val id_toko = toko.getInt("id")
                                nama_toko.setText(firstName)
                                keterangan_toko.setText(mail)
                                addProduct.setOnClickListener{
                                    val int = Intent(this, tambahProduk::class.java)
                                    int.putExtra("idToko",id_toko.toString())
                                    startActivity(int)
                                }
//                                Toast.makeText(this, firstName, Toast.LENGTH_LONG).show()
//                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    Response.ErrorListener { error -> error.printStackTrace() })

            mQueue.add(request)




            //CHANGE THE NAV BAR TITLE
            supportActionBar?.title = cat

            var url="http://192.168.43.24/Terminal/getProductToko.php?id="+cat
            var list=ArrayList<Produk>()

            var rq:RequestQueue=Volley.newRequestQueue(this)
            var jar=JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->

                for (x in 0..response.length()-1)
                    list.add(Produk(response.getJSONObject(x).getInt("id"),
                        response.getJSONObject(x).getString("gambar"),
                        response.getJSONObject(x).getString("nama"),
                        response.getJSONObject(x).getString("deskripsi"),
                        response.getJSONObject(x).getInt("berat"),
                        response.getJSONObject(x).getInt("harga"),
                        response.getJSONObject(x).getInt("kategori"))
                    )


                var adp=ProdukAdapter(this,list)
                RVbarang.layoutManager = GridLayoutManager(this,3)
                RVbarang.adapter=adp

            },Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            })

            rq.add(jar)


        }else if(uid == 4){
            setContentView(R.layout.verifikasi_toko)
        }else{
            setContentView(R.layout.tambah_toko_dashboard)

            daftar.setOnClickListener{
                val int = Intent(applicationContext, DaftarToko::class.java)
                startActivity(int)
            }
        }
    }
}