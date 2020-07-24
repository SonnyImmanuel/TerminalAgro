package com.example.terminalagro.API

import android.content.Context
import android.util.Log
import com.example.terminalagro.Model.Pengguna

class SharedPrefManager private constructor(private val mCtx: Context) {

    val KEY_ID : String = "id"
    val KEY_EMAIL : String = "email"
    val KEY_USERNAME : String = "username"
    val KEY_PASSWORD : String = "password"
    val KEY_NAMA : String = "nama"
    val KEY_NO_TELP : String = "no_telp"
    val KEY_ROLE : String = "role"

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: Pengguna
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return Pengguna(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_NO_TELP, null),
                sharedPreferences.getInt(KEY_ROLE, -1)
            )
        }

    fun saveUser(user: Pengguna) { 

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt(KEY_ID, user.id)
        editor.putString(KEY_USERNAME, user.username)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_PASSWORD, user.password)
        editor.putString(KEY_NAMA, user.nama)
        editor.putString(KEY_NO_TELP, user.no_telp)
        editor.putInt(KEY_ROLE, user.role)



        editor.apply()

        Log.e("tes", user.id.toString())

        Log.e("tes", editor.toString())

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}