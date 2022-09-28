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
                            update_account.setText(inputServer0.getText().toString());

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
                            update_account.setText(inputServer2.getText().toString());

                        }});

                    builder2.show();
                    break;
                case R.id.btn3:
                    final EditText inputServer3 = new EditText(UpdateMessageActivity.this);
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(UpdateMessageActivity.this);
                    builder3.setTitle("修改身份类型").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer3)
                            .setNegativeButton("Cancel", null);
                    builder3.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            update_account.setText(inputServer3.getText().toString());

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
                            update_account.setText(inputServer4.getText().toString());

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
                            update_account.setText(inputServer5.getText().toString());

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
                            update_account.setText(inputServer6.getText().toString());

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
                            update_account.setText(inputServer7.getText().toString());

                        }});

                    builder7.show();

                    break;
            }
        }

//               Intent intent = new Intent(UpdateMessageActivity.this,ChangeOneMessageActivity.class);
//               startActivity(intent);
//               要在同一个activity下面布局`





    }
    }



