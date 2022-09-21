package com.example.login_in_app;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
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
import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private final Gson gson = new Gson();
    private Boolean bPwdSwitch = false;
    private EditText etPwd;
    private EditText etAccount;
    private CheckBox cbRememberPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("启动项注册没有问题");
//        Toast.makeText(LoginActivity.this,"启动项注册没有问题",Toast.LENGTH_SHORT).show();
        Log.d("tag", "onCreate: 启动项没有问题");
        setContentView(R.layout.activity_login);
        Log.d("tag", "onCreate: 页面布局加载没有问题");

        final ImageView ivPwdSwitch= findViewById(R.id.iv_pwd_switch);
        etPwd = findViewById(R.id.et_pwd);
        Button btLogin = findViewById(R.id.bt_login);
        TextView btRegister=findViewById(R.id.tv_sign_up);
        Log.d("断点", "onCreate: 文字点击获取没有问题");
        etAccount=findViewById(R.id.et_account);
        cbRememberPwd = findViewById(R.id.cb_remember_pwd);



        String spFileName = getResources().getString(R.string.shared_preferences_file_name);
        String accountKey = getResources().getString(R.string.login_account_name);
        String passwordKey = getResources().getString(R.string.login_password);
        String rememberPasswordkey = getResources().getString(R.string.login_remember_password);


        //记住密码
        SharedPreferences spFile = getSharedPreferences(
                spFileName,
                MODE_PRIVATE);
        String account = spFile.getString(accountKey, null);
        String password = spFile.getString(passwordKey, null);
        Boolean rememberPassword = spFile.getBoolean(
                rememberPasswordkey,
                false);

        if (account != null && !TextUtils.isEmpty(account)) {
            etAccount.setText(account);
        }

        if (password != null && !TextUtils.isEmpty(password)) {
            etPwd.setText(password);
        }

        cbRememberPwd.setChecked(rememberPassword);

        //设置密码可不可见
        ivPwdSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bPwdSwitch = !bPwdSwitch;
                if(bPwdSwitch){
//                    点击的时候把，，不可见的图标换成可见的眼睛图标资源
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_24);
//                    设置文本输入不可见
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else{
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD |
                            InputType.TYPE_CLASS_TEXT);
                    etPwd.setTypeface(Typeface.DEFAULT);
                }




            }
        });

        //转到主页面
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    String spFileName = getResources()
                            .getString(R.string.shared_preferences_file_name);
                    String accountKey = getResources()
                            .getString(R.string.login_account_name);
                    String passwordKey =  getResources()
                            .getString(R.string.login_password);
                    String rememberPasswordKey = getResources()
                            .getString(R.string.login_remember_password);

                    SharedPreferences spFile = getSharedPreferences(
                            spFileName,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spFile.edit();

                    if (cbRememberPwd.isChecked()) {
                        String password = etPwd.getText().toString();
                        String account = etAccount.getText().toString();

                        editor.putString(accountKey, account);
                        editor.putString(passwordKey, password);
                        editor.putBoolean(rememberPasswordKey, true);
                        editor.apply();
                    } else {
                        editor.remove(accountKey);
                        editor.remove(passwordKey);
                        editor.remove(rememberPasswordKey);
                        editor.apply();
                    }

                    post();
//                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
////                    intent.putExtra(MESSAGE_STRING,message);
//                    startActivity(intent);

                }
            }
        });




        //转到注册界面，，
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                    intent.putExtra(MESSAGE_STRING,message);
                startActivity(intent);
            }
        });
        Log.d("断点", "onCreate: 没有问题");
    }


    private void post(){
        new Thread(() -> {

            //获取用户输入的密码还有姓名
            // url路径
            EditText admin = findViewById(R.id.et_account);
            EditText pwd = findViewById(R.id.et_pwd);
            String password=pwd.getText().toString();
            String user=admin.getText().toString();

            String url = "http://47.107.52.7:88/member/sign/user/login?password="+password+"&username="+user;
            System.out.println(url);
            Log.d("info", "onCreate: 网络请求参数构造没有问题");
            // 请求头
            Headers headers = new Headers.Builder()
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", "0209897961f94b7d94d40b6a754a7057")
                    .add("appSecret", "586652ad90d5c1de246c58715f1fd1c18ff18")
                    .add("Content-Type", "application/json")
                    .build();


            MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");


            //请求组合创建
//            Log.d("info", "onCreate: 网络请求参数构造没有问题");
            //问题
            Request request = new Request.Builder()
                    .url(url)
                    // 将请求头加至请求中
                    .headers(headers)
                    .post(RequestBody.create(MEDIA_TYPE_JSON, ""))
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
            Toast.makeText(LoginActivity.this,"密码错误或用户不存在",Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onResponse(@NonNull Call call, Response response) throws IOException {
            //TODO 请求成功处理
            Type jsonType = new TypeToken<ResponseBody<Object>>(){}.getType();
            // 获取响应体的json串
            String body = response.body().string();


            //通过分割符和索引找到，角色 id
            String roleId=body.split(",")[5].split(":")[1];
            String q=roleId.getClass().toString();
            Log.d("找到的位置",roleId);
            Log.d("类型：", q);
            Log.d("info", body);
            // 解析json串到自己封装的状态

            ResponseBody<Object> dataResponseBody = gson.fromJson(body,jsonType);
            Log.d("info", dataResponseBody.toString());
            //跳转到主页面
            //可以通过对象的属性值方法，获取返回的需要的信息
            String message=dataResponseBody.msg;

            System.out.println("返回值:"+message);
            //java的比较，==只能用来比较地址，，比较字符串变量的值要用equals()函数
            if(message.equals("登录成功"))
            {
                System.out.println("登录匹配成功");
                //通过获取到的  roleId决定跳转到老师界面还是用户界面
                String flag="0";
                if(roleId.equals("0"))
                {
                    //字符串0是学生，1是老师
                    Intent intent = new Intent(LoginActivity.this,StudentMainActivity.class);
////                    intent.putExtra(MESSAGE_STRING,message);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(LoginActivity.this,TeacherMainActivity.class);
////                    intent.putExtra(MESSAGE_STRING,message);
                    startActivity(intent);
                }
            }
            else
            {
                Toast.makeText(LoginActivity.this,"密码错误或用户不存在",Toast.LENGTH_SHORT).show();

            }


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