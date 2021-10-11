package com.example.scientificresearch.Server.ApiService;

import com.example.scientificresearch.Model.Class.ResponseModelClass;
import com.example.scientificresearch.Model.Login.ResponseModelLogin;
import com.example.scientificresearch.Model.Message.MessageReceive;
import com.example.scientificresearch.Model.Message.ResponseModelMessage;
import com.example.scientificresearch.Model.Schedule.ResponseModalSchedule;
import com.example.scientificresearch.Model.Subject.ResponseModelSubject;
import com.example.scientificresearch.Model.Test;
import com.example.scientificresearch.Server.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
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
    @POST("/mobile/login")
    Call<ResponseModelLogin> Login(@Field("email") String email,@Field("password") String pass);

    @FormUrlEncoded
    @POST("/mobile/get_all_subject")
    Call<ResponseModelSubject> getAllSubject(@Field("_id") String id);

    @FormUrlEncoded
    @POST("/mobile/get_all_class")
    Call<ResponseModelClass> getAllClass(@Field("_id") String id);

    @FormUrlEncoded
    @POST("/mobile/get_all_schedule")
    Call<ResponseModalSchedule> getAllSchedule(@Field("_id") String id);

//    @POST("/chat/write-message")
//    Call<JSONObject> chatToClass(@Field("users") List<String> users, @Field("message") JSONObject message);

    @POST("/chat/write-message")
    Call<MessageReceive> chatToClass2(@Body RequestBody body);

    @FormUrlEncoded
    @POST("/chat/queryAll")
    Call<ResponseModelMessage> queryAllMessage(@Field("users") List<String> users);
}
