package com.example.terminalagro.Model

data class Pengguna (
    val id:Int,
    val username:String,
    val email:String,
    val password:String,
    val nama:String,
    val no_telp:String,
//    val jenis_kelamin:String,
    val role:Int
)