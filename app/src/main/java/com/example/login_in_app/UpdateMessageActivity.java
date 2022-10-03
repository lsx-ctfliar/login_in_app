package com.example.login_in_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.TimedText;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import androidx.appcompat.app.AppCompatActivity;

public class UpdateMessageActivity extends AppCompatActivity {

    private final Gson gson = new Gson();
    private AlertDialog dlg;
    //显示右侧内容控件
    TextView update_save_btn;
    TextView update_account;
    TextView update_school;
    TextView update_type;
    TextView update_studentNumber;
    TextView update_studentRealname;
    TextView update_intime;
    TextView update_phone;


    //点击编辑按钮孔控件
    ImageView update_image_btn;
    ImageView update_image_kuang;
    ImageView update_btn1;
    ImageView update_btn2;
    ImageView update_btn3;
    ImageView update_btn4;
    ImageView update_btn5;
    ImageView update_btn6;
    ImageView update_btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这里写用户对应的布局
        setContentView(R.layout.activity_update_message);

        //找到昵称控件
        update_account=findViewById(R.id.UpdateUser);
        update_btn1=findViewById(R.id.btn1);
        //找到学校控件
        update_btn2=findViewById(R.id.btn2);
        update_school=findViewById(R.id.UpdateCollege);
        //找到身份类型
        update_btn3=findViewById(R.id.btn3);
        update_type=findViewById(R.id.UpdateType);
        //找到学号
        update_btn4=findViewById(R.id.btn4);
        update_studentNumber=findViewById(R.id.UpdateIdNumber);
        //找到真实姓名
        update_btn5=findViewById(R.id.btn5);
        update_studentRealname=findViewById(R.id.UpdateRealName);
        //找到入学时间
        update_btn6=findViewById(R.id.btn6);
        update_intime=findViewById(R.id.UpdatePIntime);
        //找到手机号码
        update_btn7=findViewById(R.id.btn7);
        update_phone=findViewById(R.id.UpdatePhone);

        //保存修改按钮
        update_save_btn=findViewById(R.id.update_save);
        //头像
        update_image_btn=findViewById(R.id.update_image_btn);
        update_image_kuang=findViewById(R.id.UpdateAvatar);


        //设置事件监听，将控件添加进入事件监听器
        Listener listener=new Listener();
        update_btn1.setOnClickListener(listener);
        update_btn2.setOnClickListener(listener);
        update_btn3.setOnClickListener(listener);
        update_btn4.setOnClickListener(listener);
        update_btn5.setOnClickListener(listener);
        update_btn6.setOnClickListener(listener);
        update_btn7.setOnClickListener(listener);
        update_btn4.setOnClickListener(listener);
        //头像
        update_image_btn.setOnClickListener(listener);
        //保存所有修改并提交到后台
        update_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交现在修改的数据
                post();
            }
        });


        }
        //通过事件监听器，监听每一个修改图片按钮，之后通过弹窗的形式将修改的内容直接修改到原来的xml布局
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("R.id的值是："+view.getId());
            switch(view.getId()){
                case R.id.update_image_btn:
                    final EditText inputServer0 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder0 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder0.setTitle("填入头像(网络图片链接)").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer0)
                            .setNegativeButton("Cancel", null);
                    builder0.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //修改图片的src,现在按就接口获取的是一个网络图片网址
//                            update_image_kuang.setText(inputServer0.getText().toString());

                        }});

                    builder0.show();

                    break;
                case R.id.btn1:
//                    System.out.println("点击事件没有问题");
//                    View cut_view = LayoutInflater.from(UpdateMessageActivity.this).inflate(R.layout.activity_change_one_message, null);
//                    System.out.println("打开编辑界面没问题");
//                    EditText editor = cut_view.findViewById(R.id.update_account);
//                    Button bt_eridotr = cut_view.findViewById(R.id.update_save);
//                    bt_eridotr.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            update_account.setText(editor.getText().toString());
//                            System.out.println(update_account.getText().toString());
//                            dlg.dismiss();
//                        }
//                    });
//                    dlg = new AlertDialog.Builder(UpdateMessageActivity.this)
//                            .setView(cut_view)
//                            .create();
//                    dlg.show();
                    final EditText inputServer = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder.setTitle("修改昵称").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                            .setNegativeButton("Cancel", null);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    update_account.setText(inputServer.getText().toString());

                                }});

                    builder.show();

                    break;
                case R.id.btn2:
                    final EditText inputServer2 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder2.setTitle("修改学校").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer2)
                            .setNegativeButton("Cancel", null);
                    builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            update_school.setText(inputServer2.getText().toString());

                        }});

                    builder2.show();
                    break;
                case R.id.btn3:
                    final EditText inputServer3 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder3.setTitle("修改身份类型(学生则设0老师设为1)").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer3)
                            .setNegativeButton("Cancel", null);
                    builder3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            String flag=inputServer3.getText().toString();
                            if(flag.equals("0"))
                            {
                                update_type.setText("学生");

                            }
                            else if(flag.equals("1")){
                                update_type.setText("老师");
                            }
                            else
                            {
                                update_type.setText("null");
                            }

                        }});

                    builder3.show();

                    break;
                case R.id.btn4:
                    final EditText inputServer4 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder4 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder4.setTitle("修改学号").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer4)
                            .setNegativeButton("Cancel", null);
                    builder4.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            update_studentNumber.setText(inputServer4.getText().toString());

                        }});

                    builder4.show();

                    break;
                case R.id.btn5:
                    final EditText inputServer5 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder5 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder5.setTitle("修改真实姓名").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer5)
                            .setNegativeButton("Cancel", null);
                    builder5.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            update_studentRealname.setText(inputServer5.getText().toString());

                        }});

                    builder5.show();

                    break;
                case R.id.btn6:
                    final EditText inputServer6 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder6 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder6.setTitle("修改入学时间").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer6)
                            .setNegativeButton("Cancel", null);
                    builder6.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            update_intime.setText(inputServer6.getText().toString());

                        }});

                    builder6.show();

                    break;
                case R.id.btn7:
                    final EditText inputServer7 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder7 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder7.setTitle("修改手机号码").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer7)
                            .setNegativeButton("Cancel", null);
                    builder7.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            update_phone.setText(inputServer7.getText().toString());

                        }});

                    builder7.show();

                    break;
            }
        }

//               Intent intent = new Intent(UpdateMessageActivity.this,ChangeOneMessageActivity.class);
//               startActivity(intent);
//               要在同一个activity下面布局`





    }

    private void post(){
        new Thread(() -> {

            // url路径
            String url = "http://47.107.52.7:88/member/sign/user/update";

            // 请求头
            Headers headers = new Headers.Builder()
                    //密钥弄错了，，我真的是服了
                    .add("Accept", "application/json, text/plain, */*")
                    .add("appId", "0209897961f94b7d94d40b6a754a7057")
                    .add("appSecret", "586652ad90d5c1de246c58715f1fd1c18ff18")
                    .add("Content-Type", "application/json")
                    .build();

//             请求体
//             PS.用户也可以选择自定义一个实体类，然后使用类似fastjson的工具获取json串
            Map<String, Object> bodyMap = new HashMap<>();

            //名字
            String string1=update_account.getText().toString();
            System.out.println("username"+string1);
            bodyMap.put("userName", string1);
            //大学
            String string2 = update_school.getText().toString();
            System.out.println("school"+string2);
            bodyMap.put("collegeName", string2);
            //真实姓名
            String string3=update_studentRealname.getText().toString();
            System.out.println("realname"+string3);
            bodyMap.put("realName", string3);
            //电话号码
            String string4=update_phone.getText().toString();
            System.out.println("phone"+string4);
            bodyMap.put("phone", string4);
            //学工号
            String string50= update_studentNumber.getText().toString();
            int string51=Integer.parseInt(string50);
            System.out.println("idnumber"+string51);

            bodyMap.put("idNumber", string51);
            //入学时间
            bodyMap.put("inSchoolTime", 0);
            //头像链接
            bodyMap.put("avatar", "https://images3.alphacoders.com/100/1004744.jpg");

            bodyMap.put("gender", true);

            System.out.println(user.getId());
//            bodyMap.put("createTime","1663675856811");
//
//            bodyMap.put("lastUpdateTime","1663675856811");
            //主键必填
            //0是学生，1是老师

            String flag2=update_type.getText().toString();
            //将字符串id转换为数字id
            int id = Integer.parseInt(user.getId());
            if(flag2.equals("学生"))
            {
                bodyMap.put("id", id);
                System.out.println(user.getId());
                System.out.println("匹配到学生");

            }
            else if(flag2.equals("老师")){
                bodyMap.put("id", id);
                System.out.println(user.getId());
                System.out.println("匹配到老师");
            }
            else
            {
                System.out.println("用户类型错误");
            }

            bodyMap.put("email", "2305177163");

//             将Map转换为字符串类型加入请求体中

//            Map<String, Object> bodyMap = new HashMap<>();
//            bodyMap.put("collegeName", "string");
//            bodyMap.put("realName", "string");
//            bodyMap.put("gender", true);
//            bodyMap.put("phone", "string");
//            bodyMap.put("avatar", "string");
//            bodyMap.put("id", 0);
//            bodyMap.put("idNumber", 0);
//            bodyMap.put("userName", "string");
//            bodyMap.put("email", "string");
//            bodyMap.put("inSchoolTime", 0);

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
            //请求成功之后进行跳转，主页面，根据用户的角色Id决定跳转到的方向
            if(user.getId().equals("0"))
            {
                //0表是是学生
                //修改完信息之后，直接跳转到学生界面
                Intent intent = new Intent(UpdateMessageActivity.this,TeacherMainActivity.class);
////                    intent.putExtra(MESSAGE_STRING,message);
                startActivity(intent);
            }
            else
            {
                //修改完信息之后直接跳转到老师界面
                Intent intent = new Intent(UpdateMessageActivity.this,TeacherMainActivity.class);
////                    intent.putExtra(MESSAGE_STRING,message);
                startActivity(intent);
            }

            //跳转之后要注意的通过用户类的新数据，将个人信息页面的组件进行赋值

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



