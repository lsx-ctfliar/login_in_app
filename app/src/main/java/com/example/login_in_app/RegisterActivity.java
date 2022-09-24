package com.example.login_in_app;


import static java.lang.Character.getType;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity{

    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这里写用户对应的布局
        setContentView(R.layout.activity_register);
        Button register_button = findViewById(R.id.bt_register);
        TextView back =findViewById(R.id.back);
        register_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            //在这里进行两次密码是否相同的验证
            EditText password1 = findViewById(R.id.register_password1);
            EditText password2 = findViewById(R.id.register_password2);

            String str1 = password1.getText().toString();
            String str2=  password2.getText().toString();


            if(str1.equals(str2))
            {
                post();
                //当注册结束之后，跳转到登陆界面
                Log.d("网络获取", "onClick: 没有问题");
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//                    intent.putExtra(MESSAGE_STRING,message);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(RegisterActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
            }
            }

        });

        //返回登录界面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//                    intent.putExtra(MESSAGE_STRING,message);
                startActivity(intent);
            }
        });
    }

    private void post(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/register";

            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", "0209897961f94b7d94d40b6a754a7057")
                    .add("appSecret", "586652ad90d5c1de246c58715f1fd1c18ff18")
                    .add("Content-Type", "application/json")
                    .build();

            // 请求体，在这里获取账户名密码还有身份，身份用数字零和一表示
            // PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();
            EditText password = findViewById(R.id.register_password1);
            EditText user=findViewById(R.id.register_account);
            String pwd="";
            String admin="";
            pwd=password.getText().toString();
            admin=user.getText().toString();
            System.out.println(pwd+"和"+admin);
            Log.d("填写请求头", "post: 没有问题");


            RadioGroup groupSelect = findViewById(R.id.radioGroup1);
            int flag=0;//只有零和一
            for(int i = 0 ;i < groupSelect.getChildCount();i++){
                RadioButton rb = (RadioButton)groupSelect.getChildAt(i);
                if(rb.isChecked()){
                    //循环判断是哪个单选框被选择了
                    System.out.println("被选取的按钮是："+rb);
                    flag=i;  //职业选择就是零和一
                    System.out.println(getType(flag));
                    break;
                }
            }
            //将获取到的参数存入map
            bodyMap.put("password", pwd);

            bodyMap.put("roleId",flag);
            bodyMap.put("userName", admin);
            System.out.println("传入参数没有问题");
            System.out.println(bodyMap.get("roleId"));
            // 将Map转换为字符串类型加入请求体中
            String body = gson.toJson(bodyMap);

            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

            //请求组合创建
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, body))
                    .build();
            try {
                OkHttpClient client = new OkHttpClient();
                //发起请求，传入callback进行回调
                client.newCall(request).enqueue(callback);
            }catch (NetworkOnMainThreadException ex){
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * 回调
     */
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(@NonNull Call call, IOException e) {
            //TODO 请求失败处理
            e.printStackTrace();
        }
        @Override
        public void onResponse(@NonNull Call call, Response response) throws IOException {
            //TODO 请求成功处理
            Type jsonType = new TypeToken<ResponseBody<Object>>(){}.getType();
            // 获取响应体的json串
            String body = response.body().string();
            Log.d("info", body);
            // 解析json串到自己封装的状态
            ResponseBody<Object> dataResponseBody = gson.fromJson(body,jsonType);
            Log.d("info", dataResponseBody.toString());
            System.out.println(body);
            String code=body.split(",")[1].split(":")[1];
            String data=body.split(",")[1].split(":")[1];
            System.out.println(code);
        }
    };

    /**
     * http响应体的封装协议
     * @param <T> 泛型
     */
    public static class ResponseBody <T> {

        /**
         * 业务响应码
         */
        private int code;
        /**
         * 响应提示信息
         */
        private String msg;
        /**
         * 响应数据
         */
        private T data;

        public ResponseBody(){}

        public int getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
        public T getData() {
            return data;
        }

        @NonNull
        @Override
        public String toString() {
            return "ResponseBody{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }


}
