package com.example.terminalagro.Model

class Produk {
    var id:Int
    var gambar:String
    var nama:String
    var deskripsi:String
    var berat:Int
    var harga:Int
    var kategori:Int

    constructor(
        id: Int,
        gambar:String,
        nama:String,
        deskripsi:String,
        berat:Int,
        harga:Int,
        kategori:Int
    ) {
        this.id = id
        this.gambar = gambar
        this.nama = nama
        this.deskripsi = deskripsi
        this.berat = berat
        this.harga = harga
        this.kategori = kategori
    }
}