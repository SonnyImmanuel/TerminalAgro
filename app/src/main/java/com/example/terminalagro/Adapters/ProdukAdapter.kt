package com.example.terminalagro.Adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.terminalagro.DetailProduk
import com.example.terminalagro.Model.Produk
import com.example.terminalagro.R
import kotlinx.android.synthetic.main.card_item_home.view.*
import java.util.ArrayList

class ProdukAdapter (var context: Context, var list: ArrayList<Produk>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*Connect Recycler View to layout main activity*/

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var my_view= LayoutInflater.from(context).inflate(R.layout.card_item_home,p0,false)
        return ItemHolder(my_view)
    }

    /*Get data from layout main activity */
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ItemHolder).bind(list[p1].nama,list[p1].harga,list[p1].id)
    }


    /*Get size of list */
    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun bind(n:String,p:Int, item_id:Int)
        {
//            itemView.nPenerima.text=n
            itemView.namaProduk.text=n
            itemView.hargaProduk.text="Rp. "+p.toString()
//            var web:String="http://192.168.43.116/kotlin/images/"+u
//            web=web.replace(" ","%20")//this helps to concate photo name which can't be called  eg volvic water.jpeg
//            Picasso.with(itemView.context).load(web).into(itemView.item_photo)
//
//            itemView.produk.setOnClickListener {
//
//                var int = Intent(activity!!., DetailProduk::class.java)
//                int.putExtra("idBarang",item_id)
//
//
//            }

        }

    }
}