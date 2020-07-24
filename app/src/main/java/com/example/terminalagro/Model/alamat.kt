package com.example.terminalagro.Model

class alamat{
    var id:Int
    var nama:String
    var no_hp:String
    var jalan:String
    var kecamatan:String
    var kabupaten:String
    var provinsi:String

    constructor(
        id: Int,
        nama: String,
        no_hp: String,
        jalan: String,
        kecamatan: String,
        kabupaten: String,
        provinsi: String
    ) {
        this.id = id
        this.nama = nama
        this.no_hp = no_hp
        this.jalan = jalan
        this.kecamatan = kecamatan
        this.kabupaten = kabupaten
        this.provinsi = provinsi
    }
}