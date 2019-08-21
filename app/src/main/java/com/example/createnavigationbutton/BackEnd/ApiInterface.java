package com.example.createnavigationbutton.BackEnd;

import com.example.createnavigationbutton.Models.Mahasiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("addMahasiswa.php")
    Call<Mahasiswa> tambahMahasiswa(@Field("nama") String nama,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("updateMahasiswa.php")
    Call<Mahasiswa> updateMahasiswa(@Field("id") int id,
                                    @Field("nama") String nama,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @GET("lihatMahasiswa.php")
    Call<Mahasiswa> lihatMahasiswa();


}
