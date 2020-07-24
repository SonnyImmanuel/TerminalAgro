package com.example.terminalagro.API

import com.example.terminalagro.Model.LoginResponse
import com.example.terminalagro.Model.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface Api{
    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
//    @FormUrlEncoded
//    @POST("login.php")
//    fun loginRequest(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register.php")
    abstract fun registerRequest(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nama") nama: String,
        @Field("no_telp") no_telp: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("updateUser.php")
    abstract fun editProfilRequest(
        @Field("id") id: Int,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("nama") nama: String,
        @Field("no_telp") no_telp: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("tambahToko.php")
    abstract fun tambahTokoRequest(
//        @Field("gambar") gambar: String,
        @Field("nama") nama: String,
        @Field("alamat") alamat: String,
        @Field("deskripsi") deskripsi: String,
        @Field("user") id: Int
//        @Field("ktp") ktp: String,
//        @Field("selfi") selfi: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("TambahProduk.php")
    abstract fun tambahProdukRequest(
//        @Field("gambar") gambar: String,
        @Field("nama") nama: String,
        @Field("harga") harga: Int,
        @Field("deskripsi") deskripsi: String,
        @Field("berat") berat: Int,
        @Field("jumlah") jumlah: Int,
        @Field("id") id: Int
//        @Field("ktp") ktp: String,
//        @Field("selfi") selfi: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @POST("addAddress.php")
    abstract fun tambahAlamatRequest(
        @Field("nama") nama: String,
        @Field("no_telp") no_telp: String,
        @Field("jalan") jalan: String,
        @Field("kecamatan") kecamatan: String,
        @Field("kabupaten") kabupaten: String,
        @Field("provinsi") provinsi: String,
        @Field("user") id: Int
    ): Call<ResponseBody>

    @GET("getData.php/{id_user}")
    fun getProfilData(
        @Path("id_user") path: String?,
        @Query("id") id: String?
    ): Call<ResponseBody>
}