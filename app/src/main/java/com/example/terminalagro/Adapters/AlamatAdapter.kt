package com.example.terminalagro.Adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.terminalagro.Model.alamat
import com.example.terminalagro.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_alamat.view.*
import java.util.ArrayList

class AlamatAdapter(var c:Context,var list: ArrayList<alamat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*Connect Recycler View to layout main activity*/

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var my_view=LayoutInflater.from(c).inflate(R.layout.item_alamat,p0,false)
        return ItemHolder(my_view)
    }

    /*Get data from layout main activity */
    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as ItemHolder).bind(list[p1].nama,list[p1].no_hp,list[p1].jalan,list[p1].kecamatan,list[p1].kabupaten,list[p1].provinsi,list[p1].id)
    }

    /*Get size of list */
    override fun getItemCount(): Int {
        return list.size
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        fun bind(n:String,p:String,u:String,v:String,w:String,x:String, item_id:Int)
        {
            itemView.nPenerima.text=n
            itemView.tPenerima.text=p
            itemView.aPenerima.text=u+", "+v+", "+w+", "+x
//            var web:String="http://192.168.43.116/kotlin/images/"+u
//            web=web.replace(" ","%20")//this helps to concate photo name which can't be called  eg volvic water.jpeg
//            Picasso.with(itemView.context).load(web).into(itemView.item_photo)
//
//            itemView.item_add_photo.setOnClickListener {
//
//                UserInfo.itemId=item_id //we pass the item id
//
//
//                var obj=QuantityFragment()
//                var manager=(itemView.context as Activity).fragmentManager
//                obj.show(manager,"Qty")
//
//            }

        }

    }
}