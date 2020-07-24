package com.example.terminalagro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Adapters.AlamatAdapter
import com.example.terminalagro.Model.alamat
import kotlinx.android.synthetic.main.activity_alamat_saya.*

class AlamatSaya : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alamat_saya)
        var cat:String = SharedPrefManager.getInstance(applicationContext).user.id.toString() //category variable cat is received from HomeActivity

        //CHANGE THE NAV BAR TITLE
        supportActionBar?.title = cat

        var url="http://192.168.43.24/Terminal/myAddress.php?id="+cat
        var list=ArrayList<alamat>()

        var rq:RequestQueue=Volley.newRequestQueue(this)
        var jar=JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->

            for (x in 0..response.length()-1)
                list.add(alamat(response.getJSONObject(x).getInt("id"),
                    response.getJSONObject(x).getString("nama"),
                    response.getJSONObject(x).getString("no_telp"),
                    response.getJSONObject(x).getString("jalan"),
                    response.getJSONObject(x).getString("kecamatan"),
                    response.getJSONObject(x).getString("kabupaten"),
                    response.getJSONObject(x).getString("provinsi")))


            var adp=AlamatAdapter(this,list)
            RVAlamat.layoutManager = LinearLayoutManager(this)
            RVAlamat.adapter=adp

        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
        })

        rq.add(jar)

    }

}
