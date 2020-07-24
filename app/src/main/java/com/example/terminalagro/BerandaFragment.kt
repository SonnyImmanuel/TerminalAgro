package com.example.terminalagro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.terminalagro.Adapters.ProdukAdapter
import com.example.terminalagro.Adapters.Slider_Pager_Adapter
import com.example.terminalagro.Model.Produk
import com.example.terminalagro.Model.alamat
import kotlinx.android.synthetic.main.category_layout.*
import kotlinx.android.synthetic.main.fragment_beranda.*
import java.util.*

class BerandaFragment : Fragment() {

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_beranda, container, false)
//    }
//
//    companion object{
//        fun newInstance() : BerandaFragment{
//            val fragment = BerandaFragment()
//            val args = Bundle()
//            fragment.arguments = args
//            return fragment
//        }
//    }
//}
internal lateinit var sliderPagerAdapter: Slider_Pager_Adapter
    internal var slider_image_list = ArrayList<Int>()
    internal var page_position = 0
    internal lateinit var timer: Timer
    private var images_slider: ViewPager? = null
    private var pages_dots: LinearLayout? = null
    private var dots: Array<TextView>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.fragment_beranda, container, false)

        images_slider = rootview.findViewById(R.id.image_page_slider)
        pages_dots = rootview.findViewById(R.id.image_page_dots)

        timer = Timer()
        initSlider()
        scheduleSlider()
//        kategori()




        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product()
        var cat:String?=null
        padi.setOnClickListener{
            var int = Intent(activity!!.applicationContext, ListProduk::class.java)
            int.putExtra("cat","1")
            startActivity(int)
        }

        beras.setOnClickListener{
            var int = Intent(activity!!.applicationContext, ListProduk::class.java)
            int.putExtra("cat","2")
            startActivity(int)
        }

        data.setOnClickListener{
            var int = Intent(activity!!.applicationContext, ListProduk::class.java)
            int.putExtra("cat","semua")
            startActivity(int)
        }
    }

    fun initSlider() {
//        addBottomDots(0)

        slider_image_list = ArrayList()

        //Add few items to slider_image_list ,this should contain url of images which should be displayed in slider
        // here i am adding few sample image links from drawable, we will replace it later

        slider_image_list.add(R.drawable.c2)
        slider_image_list.add(R.drawable.c3)
        slider_image_list.add(R.drawable.c4)

        sliderPagerAdapter = Slider_Pager_Adapter(activity, slider_image_list)
        images_slider!!.adapter = sliderPagerAdapter
        images_slider!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
//                addBottomDots(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }

    fun kategori(){


    }

    fun scheduleSlider() {

        val handler = Handler()

        val update = Runnable {
            if (page_position == slider_image_list.size) {
                page_position = 0
            } else {
                page_position = page_position + 1
            }
            images_slider!!.setCurrentItem(page_position, true)
        }

        timer.schedule(object : TimerTask() {

            override fun run() {
                handler.post(update)
            }
        }, 500, 4000)
    }

//    fun addBottomDots(currentPage: Int) {
//        dots = arrayOfNulls(this.slider_image_list.size)
//
//        pages_dots!!.removeAllViews()
//        pages_dots!!.setPadding(0, 0, 0, 20)
//        for (i in dots!!.indices) {
//            dots[i] = TextView(context)
//            dots!![i].text = Html.fromHtml("&#8226;")
//            dots!![i].textSize = 35f
//            dots!![i].setTextColor(Color.parseColor("#9f9f9f")) // un selected
//            pages_dots!!.addView(dots!![i])
//        }
//
//        if (dots!!.size > 0)
//            dots!![currentPage].setTextColor(Color.parseColor("#2f383a")) // selected
//    }

    fun product(){
        var url="http://192.168.43.24/Terminal/dashboardProduct.php"
        var list=ArrayList<Produk>()

        var rq: RequestQueue = Volley.newRequestQueue(activity!!.applicationContext)
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


            var adp=ProdukAdapter(activity!!.applicationContext, list)
//            newProduct.layoutManager = LinearLayoutManager(activity!!.applicationContext)
            newProduct.adapter=adp

        }, Response.ErrorListener { error ->
            Toast.makeText(activity!!.applicationContext,error.message, Toast.LENGTH_LONG).show()
        })

        rq.add(jar)

//        newProduct.setOnItemClickListener { parent, view, position, id ->
//
//            var cat:String=list[position]
//            var obj= Intent(activity!!.applicationContext,AlamatSaya::class.java)
//            obj.putExtra("cat",cat) //The "name" passes the category variable cat to ItemActivity
//            startActivity(obj)
//
//        }
    }

    companion object{
        fun newInstance() : BerandaFragment{
            val fragment = BerandaFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}
