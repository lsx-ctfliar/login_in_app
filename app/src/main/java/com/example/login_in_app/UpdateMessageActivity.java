package com.example.login_in_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这里写用户对应的布局
        setContentView(R.layout.activity_update_message);
        //
        //设置点击

        //设置图片编辑

        TextView account=findViewById(R.id.UpdateUser);
        //设置点击按钮，编辑昵称信息页面
       ImageView btn1=findViewById(R.id.btn1);
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               Intent intent = new Intent(UpdateMessageActivity.this,ChangeOneMessageActivity.class);
//               startActivity(intent);
//               要在同一个activity下面布局`
               View cut_view = LayoutInflater.from(UpdateMessageActivity.this).inflate(R.layout.activity_change_one_message,null);
               EditText editor = cut_view.findViewById(R.id.update_account);
               Button bt_eridotr = cut_view.findViewById(R.id.update_save);
               bt_eridotr.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       account.setText(editor.getText().toString());
                       dlg.dismiss();
                   }
               });
               dlg= new AlertDialog.Builder(UpdateMessageActivity.this)
                       .setView(cut_view)
                       .create();
               dlg.show();


           }
       });
    }


}
