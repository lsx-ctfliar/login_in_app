package com.example.util;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyPost {
    public static Request post(Object T, String url){
        Gson gson = new Gson();
        String json = gson.toJson(T);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        System.out.println("12313"+json);

        return MyUtil.post(requestBody,url);
    }


}
