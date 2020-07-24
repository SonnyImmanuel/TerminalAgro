//package com.example.myapplication.Adapters
//
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import com.example.myapplication.R
//import com.example.myapplication.model.Notification
//import java.util.*
//
//class ListNotificationAdapter(private val listNotification: ArrayList<Notification>) :
//    RecyclerView.Adapter<ListNotificationAdapter.ListViewHolder>() {
//
//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListNotificationAdapter.ListViewHolder {
//        val view =
//            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_notification, viewGroup, false)
//        return ListViewHolder(view)
//    }
//
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
////        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_notification,parent,false))
////    }
//
//    class Holder(val view: View) : RecyclerView.ViewHolder(view)
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val notfication = listNotification[position]
//        holder.judul.text = listNotification.get(position).judul
//        holder.isi.text = listNotification.get(position).isi
//
//    }
//
//    override fun getItemCount(): Int {
//        return listNotification.size
//    }
//
//    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        internal var judul: TextView
//        internal var isi: TextView
//
//        init {
//            judul = itemView.findViewById(R.id.judul)
//            isi = itemView.findViewById(R.id.isi)
//        }
//    }
//}
