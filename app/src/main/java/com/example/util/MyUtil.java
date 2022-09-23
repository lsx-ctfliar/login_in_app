package com.example.util;

import com.google.gson.Gson;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyUtil {
//    public static String appId = "97b903276b31414ba827016e4fe3a17f";
//    public static String appSecret = "2150364e09744b553466c86705a55163bd5b0";
    public static String appId = "3d13c1e01ec24e77b2f8312e0389ad20";
    public static String appSecret = "59916589b88acc9894431adcc4e37de378ad2";
    public static String appUrl = "http://47.107.52.7:88/member";
    public static int time = 200000;

    public static OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(MyUtil.time, TimeUnit.MICROSECONDS)
                .build();
    }

    public static FormBody.Builder getBuilder(){
        return new FormBody.Builder();
    }

//    public static Request getPostRequest(RequestBody requestBody,String url){
//        return new Request.Builder()
//                .url(MyUtil.appUrl + url)
//                .addHeader("Accept", "application/json, text/plain, */*")
//                .addHeader("appId", MyUtil.appId)
//                .addHeader("appSecret", MyUtil.appSecret)
//                .addHeader("Content-Type", "application/json")
//                .post(requestBody)
//                .build();
//    }

    public static Request post(RequestBody requestBody,String url){
        Headers headers = new Headers.Builder()
                .add("Accept", "application/json, text/plain, */*")
                .add("Content-Type","application/json")
                .add("appId", MyUtil.appId)
                .add("appSecret", MyUtil.appSecret)
                .build();
        return new Request.Builder()
                .url(MyUtil.appUrl + url)
                .headers(headers)
                .post(requestBody)
                .build();
    }


    public static Request get(Map<String,String> map,String url){
        return new Request.Builder()
                .url(MyUtil.appUrl + MyUtil.getParam(map, url))
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Type","application/json")
                .addHeader("appId", MyUtil.appId)
                .addHeader("appSecret", MyUtil.appSecret)
                .get()
                .build();

    }
    public static Request get(String param){
        return new Request.Builder()
                .url(MyUtil.appUrl + param)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Type","application/json")
                .addHeader("appId", MyUtil.appId)
                .addHeader("appSecret", MyUtil.appSecret)
                .get()
                .build();

    }

    private static String getParam(Map<String,String> map, String url) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> next = iterator.next();
            stringBuilder.append(next.getKey());
            stringBuilder.append("=");
            stringBuilder.append(next.getValue());
            if(iterator.hasNext()){
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }


    public static Call getCall(Request request){
        return MyUtil.getClient().newCall(request);
    }


    public static String cutJsonFirst(String jsonString){
        System.out.println(jsonString);
        int index1 = jsonString.indexOf("data");
        if(index1 <= 0){
            index1 = 0;
        }
        else {
            index1 += 6;
        }
        int index2 = jsonString.length()-1;
//        int index2 = jsonString.indexOf("}}")+1;
        System.out.println("index1:"+index1);
        System.out.println("index2:"+index2);
        System.out.println("剪切后的json为："+jsonString.substring(index1,index2));
        return jsonString.substring(index1,index2);
    }



    public static <T> T getJsonObject(String jsonString,Class<T> classOfT){
        Gson gson = new Gson();
        String jsonData = MyUtil.cutJsonFirst(jsonString);
        return gson.fromJson(jsonData,classOfT);
    }

    public static String getNowTime(){
        return "";
    }

}
