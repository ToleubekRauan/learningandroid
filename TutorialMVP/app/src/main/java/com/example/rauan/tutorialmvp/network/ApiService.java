package com.example.rauan.tutorialmvp.network;

import com.google.gson.JsonObject;

import org.json.JSONObject;



import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by rauan on 10.07.17.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("login/")
    Observable<JsonObject> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register/")
    Observable<JsonObject> signUp(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("fullname") String fullName);


}
