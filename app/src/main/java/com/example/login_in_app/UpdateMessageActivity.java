package com.example.login_in_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
    TextView update_account;
    ImageView update_btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这里写用户对应的布局
        setContentView(R.layout.activity_update_message);
        //
        //设置点击

        //设置图片编辑

        update_account=findViewById(R.id.UpdateUser);
        //设置点击按钮，编辑昵称信息页面
       update_btn1=findViewById(R.id.btn1);
        Listener listener=new Listener();
        update_btn1.setOnClickListener(listener);



        }
    class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            System.out.println("R.id的值是："+view.getId());
            switch(view.getId()){
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

            }
        }

//               Intent intent = new Intent(UpdateMessageActivity.this,ChangeOneMessageActivity.class);
//               startActivity(intent);
//               要在同一个activity下面布局`





    }
    }



