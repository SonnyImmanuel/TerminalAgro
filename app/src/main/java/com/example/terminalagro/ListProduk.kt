package com.example.terminalagro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.terminalagro.Adapters.ProdukAdapter
import com.example.terminalagro.Model.Produk
import kotlinx.android.synthetic.main.activity_list_produk.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import java.util.ArrayList

class ListProduk : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_produk)

        var cat:String =intent.getStringExtra("cat")

        if(cat == "semua"){
            var url="http://192.168.43.24/Terminal/dashboardProduct.php"
            var list= ArrayList<Produk>()

            var rq: RequestQueue = Volley.newRequestQueue(this)
            var jar= JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->

                for (x in 0..response.length()-1)
                    list.add(
                        Produk(response.getJSONObject(x).getInt("id"),
                            response.getJSONObject(x).getString("gambar"),
                            response.getJSONObject(x).getString("nama"),
                            response.getJSONObject(x).getString("deskripsi"),
                            response.getJSONObject(x).getInt("berat"),
                            response.getJSONObject(x).getInt("harga"),
                            response.getJSONObject(x).getInt("kategori"))
                    )


                var adp= ProdukAdapter(this, list)
//            newProduct.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                newProducts.layoutManager = GridLayoutManager(this,3)
                newProducts.adapter=adp

            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            })

            rq.add(jar)
        }else{
            var url="http://192.168.43.24/Terminal/getProdukByKategori.php?id="+cat
            var list= ArrayList<Produk>()

            var rq: RequestQueue = Volley.newRequestQueue(this)
            var jar= JsonArrayRequest(Request.Method.GET,url,null, Response.Listener { response ->

                for (x in 0..response.length()-1)
                    list.add(
                        Produk(response.getJSONObject(x).getInt("id"),
                            response.getJSONObject(x).getString("gambar"),
                            response.getJSONObject(x).getString("nama"),
                            response.getJSONObject(x).getString("deskripsi"),
                            response.getJSONObject(x).getInt("berat"),
                            response.getJSONObject(x).getInt("harga"),
                            response.getJSONObject(x).getInt("kategori"))
                    )


                var adp= ProdukAdapter(this, list)
//            newProduct.layoutManager = LinearLayoutManager(activity!!.applicationContext)
                newProducts.layoutManager = GridLayoutManager(this,3)
                newProducts.adapter=adp

            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            })

            rq.add(jar)
        }

    }
}