package com.example.scientificresearch.Server.ApiService;

import com.example.scientificresearch.Model.Login.ResponseModelLogin;
import com.example.scientificresearch.Model.Test;
import com.example.scientificresearch.Server.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    StudentService studentService = new Retrofit.Builder()
            .baseUrl(Config.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(StudentService.class);


    @GET("/student/test")
    Call<Test> TestApi();
    @FormUrlEncoded
    @POST("/student/login")
    Call<ResponseModelLogin> Login(@Field("email") String email,@Field("password") String pass);
}
