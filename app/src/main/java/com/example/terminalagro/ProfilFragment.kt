package com.example.terminalagro

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.terminalagro.API.SharedPrefManager
import com.example.terminalagro.Model.Pengguna
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil_no_login.*


class ProfilFragment : Fragment() {

    val mypreference = "mypref"
    val Name = "nameKey"
    val Email = "emailKey"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(SharedPrefManager.getInstance(activity!!.applicationContext).isLoggedIn){
            return inflater.inflate(R.layout.fragment_profil, container, false)
        }else{
            return inflater.inflate(R.layout.fragment_profil_no_login, container, false)

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(SharedPrefManager.getInstance(activity!!.applicationContext).isLoggedIn){

//            val pengguna = SharedPrefManager.getInstance(activity!!.applicationContext).user
//
//
//            val toast = Toast.makeText(activity!!.applicationContext, pengguna, Toast.LENGTH_LONG)
//            toast.show()
            val uid:String = SharedPrefManager.getInstance(activity!!.applicationContext).user.role.toString()
            val toast = Toast.makeText(activity!!.applicationContext, uid, Toast.LENGTH_LONG)
            val user:String = SharedPrefManager.getInstance(activity!!.applicationContext).user.id.toString()
            toast.show()
            textView.setOnClickListener{
                val int = Intent(activity!!.applicationContext, ProfilSaya::class.java)
                int.putExtra("path", user)
                startActivity(int)
            }

            textStore.setOnClickListener{
                val int = Intent(activity!!.applicationContext, Toko::class.java)
                startActivity(int)
            }

            textAddress.setOnClickListener{
                val int = Intent(activity!!.applicationContext, AlamatSaya::class.java)
                startActivity(int)
            }

            textLogout.setOnClickListener{
                val builder = AlertDialog.Builder(activity!!)
                builder.setTitle("Apakah anda yakin ingin keluar")
//                builder.setMessage("Apakah anda yakin ingin keluar?")
                builder.setPositiveButton("Ya"){dialog, which ->
//                    val progressDialog = ProgressDialog(activity!!,
//                        R.style.Theme_MaterialComponents_Light_Dialog)
//                    progressDialog.isIndeterminate = true
//                    progressDialog.setMessage("Loading...")
//                    progressDialog.show()
                    SharedPrefManager.getInstance(activity!!.applicationContext).clear()
                    val int = Intent(activity!!.applicationContext, MainActivity::class.java)
                    startActivity(int)
                }


                // Display a negative button on alert dialog
                builder.setNegativeButton("Tidak"){dialog,which ->
                    Toast.makeText(activity!!.applicationContext,"Anda tidak keluar.",Toast.LENGTH_SHORT).show()
                }

                val dialog: AlertDialog = builder.create()

                dialog.show()
            }

        }else{
            textLogin.setOnClickListener{
                val int = Intent(activity!!.applicationContext, Login::class.java)
                startActivity(int)
            }

        }
    }

    companion object{
        fun newInstance() : ProfilFragment{
            val fragment = ProfilFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}

