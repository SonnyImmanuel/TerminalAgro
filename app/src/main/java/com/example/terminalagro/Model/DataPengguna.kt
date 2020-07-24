package com.example.terminalagro.Model

class DataPengguna {
    var id:Int
    var nama:String
    var no_telp:String
//    val jenis_kelamin:String,
    var role:Int

    constructor(
        id: Int,
        nama: String,
        no_telp: String,
        role: Int
    ) {
        this.id = id
        this.nama = nama
        this.no_telp = no_telp
        this.role = role
    }
}